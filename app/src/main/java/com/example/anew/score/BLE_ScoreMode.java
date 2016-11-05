package com.example.anew.score;

/**
 * Created by new on 2016. 9. 19..
 */
import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anew.score.service.BTCTemplateService;
import com.example.anew.score.utils.Constants;

import java.util.Timer;

public class BLE_ScoreMode extends Activity implements View.OnClickListener{

    private Context mContext;
    private BTCTemplateService mService;
    private ActivityHandler mActivityHandler;

    // Refresh timer
    private Timer mRefreshTimer = null;

    // 텍스트 컴포넌트 생성
    private ImageButton btnEnd;
    private ImageButton btnScore;

    private TextView round_end;
    private TextView round_num;

    private TextView txt1;
    private TextView txt2;

    private TextView score_txt1;
    private TextView score_txt2;
    private TextView score_txt3;
    private TextView score_txt4;

    private TextView Home_txt;
    private TextView Away_txt;

    // 다이얼로그 컴포넌트 생성
    private Dialog input_dlg;

    // 취소 변수
    final static int Init = 0;
    final static int H_up = 1;
    final static int A_up = 2;
    int undo_Status = Init;

    // 라운드 변수
    int round_Status = 0;

    private Intent intent;


    // 총 스코어(왼쪽,오른쪽)
    private int sum1_num = 0;
    private int sum2_num = 0;

    private int H_win = 0;
    private int A_win = 0;

    // 스코어 정보 저장 객체
    private Game_Info RoundScore = new Game_Info();

    // 게임 목표 변수 생성
    private int score;
    private int round;

    //상태 변수
    private boolean State = false;

    // 취소 리스트
    private int undo_num = 0;
    private int[] undo_list = new int[100];

    // 사운드
    private SoundPool sound1;
    private int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //----- System, Context
        mActivityHandler = new ActivityHandler();

        setContentView(R.layout.ble_scoremode);

        setting();

        sound1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundID = sound1.load(this, R.raw.coin, 1);

        // BLE_setting 액티비티로부터 값 받아옴
        intent = getIntent();
        score = intent.getExtras().getInt("score");
        round = intent.getExtras().getInt("round");
        //score=20;
        //round = 4;
        RoundScore.setGoal_Round(round);

        // Do data initialization after service started and binded
        doStartService();

    }



    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_score:
                intent = new Intent(getBaseContext(), Game_Result.class);
                intent.putExtra("RoundScore", RoundScore);
                startActivityForResult(intent, 1);
                break;

            case R.id.btn_end:
                gameEnd();
                break;
        }
    }

    // 다이얼로그 생성 메소드
    public void onSucc() {
        ShowDialog();
    }

    public void ShowDialog() {
        input_dlg = new Dialog(this);
        input_dlg.setContentView(R.layout.done_dialog);

        round_num = (TextView)input_dlg.findViewById(R.id.round_num);

        round_num.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));

        round_num.setText(String.valueOf(round_Status+1));
        input_dlg.show();
    }

    public void gameEnd(){

        if(sum1_num>score){
            sum1_num=score;
        }
        if(sum2_num>score){
            sum2_num=score;
        }

        // 스코어 객체에 점수 저장
        RoundScore.setHomeScore(round_Status, sum1_num);
        RoundScore.setAwayScore(round_Status, sum2_num);
        RoundScore.setRound(round_Status);

        // 취소 초기화
        undo_num = 0;
        undo_Status = Init;

        // 라운드 스코어 처리
        if(sum1_num>sum2_num)
            H_win++;
        else if(sum2_num>sum1_num)
            A_win++;

        // 종합 점수 초기화
        sum1_num = 0;
        sum2_num = 0;

        // 점수 텍스트 초기화
        score_txt1.setText(String.format("%d", sum1_num/10));
        score_txt2.setText(String.format("%d", sum1_num%10));
        score_txt3.setText(String.format("%d", sum2_num/10));
        score_txt4.setText(String.format("%d", sum2_num%10));

        txt1.setText(String.valueOf(H_win));
        txt2.setText(String.valueOf(A_win));

        if(H_win>A_win){
            txt1.setTextColor(Color.MAGENTA);
            txt2.setTextColor(Color.GRAY);
        }
        else if(A_win>H_win){
            txt1.setTextColor(Color.GRAY);
            txt2.setTextColor(Color.MAGENTA);
        }
        else{
            txt1.setTextColor(Color.MAGENTA);
            txt2.setTextColor(Color.MAGENTA);
        }

        // 목표 라운드에 진입시
        if(round_Status==round-1){
            round_Status = 0;
            intent = new Intent(getBaseContext(), Game_Result.class);
            intent.putExtra("RoundScore", RoundScore);
            startActivityForResult(intent,1);
            finish();
        }
        else{

            // 다이얼로그 띄우기
            onSucc();
            round_Status++;
        }

    }

    // Show messages from remote
    public void showMessage(String message) {
        if (message != null && message.length() > 0) {

            if(message.equals("hn1")){
                sum1_num += 1;
                undo_num++;
                sound1.play(soundID,1f,1f,0,0,1f);

                if (sum1_num >= score) {
                    gameEnd();
                } else {
                    // 현재 상태 및 취소 리스트 동작 등록
                    undo_Status = H_up;
                    undo_list[undo_num] = H_up;
                    score_txt1.setText(String.format("%d", sum1_num/10));
                    score_txt2.setText(String.format("%d", sum1_num%10));
                }
            }
            else if(message.equals("an1")){
                sum2_num += 1;
                undo_num++;
                sound1.play(soundID,1f,1f,0,0,1f);

                if (sum2_num >= score) {
                    gameEnd();
                } else {
                    // 현재 상태 및 취소 리스트 동작 등록
                    undo_Status = A_up;
                    undo_list[undo_num] = A_up;
                    score_txt3.setText(String.format("%d", sum2_num/10));
                    score_txt4.setText(String.format("%d", sum2_num%10));
                }
            }
            else if(message.equals("cc")){
                switch (undo_Status) {
                    case Init:
                        break;

                    case H_up:
                        sum1_num -= 1;
                        undo_num--;

                        undo_Status = undo_list[undo_num];
                        score_txt1.setText(String.format("%d", sum1_num / 10));
                        score_txt2.setText(String.format("%d", sum1_num % 10));
                        break;

                    case A_up:
                        sum2_num -= 1;
                        undo_num--;

                        undo_Status = undo_list[undo_num];
                        score_txt3.setText(String.format("%d", sum2_num / 10));
                        score_txt4.setText(String.format("%d", sum2_num % 10));
                        break;

                }
            }


        }
    }

    /*****************************************************
     *	Private methods
     ******************************************************/

    /**
     * Service connection
     */
    private ServiceConnection mServiceConn = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder binder) {

            mService = ((BTCTemplateService.ServiceBinder) binder).getService();

            // Activity couldn't work with mService until connections are made
            // So initialize parameters and settings here. Do not initialize while running onCreate()
            initialize();
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };

    /**
     * Start service if it's not running
     */
    private void doStartService() {
        startService(new Intent(this, BTCTemplateService.class));
        bindService(new Intent(this, BTCTemplateService.class), mServiceConn, Context.BIND_AUTO_CREATE);
    }

    /**
     * Initialization / Finalization
     */
    private void initialize() {

        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.bt_ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        mService.setupService(mActivityHandler);

        // If BT is not on, request that it be enabled.
        // RetroWatchService.setupBT() will then be called during onActivityResult
        if(!mService.isBluetoothEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, Constants.REQUEST_ENABLE_BT);
        }

        // Load activity reports and display
        if(mRefreshTimer != null) {
            mRefreshTimer.cancel();
        }

        // Use below timer if you want scheduled job
        //mRefreshTimer = new Timer();
        //mRefreshTimer.schedule(new RefreshTimerTask(), 5*1000);
    }

    /*****************************************************
     *	Handler, Callback, Sub-classes
     ******************************************************/

    public class ActivityHandler extends Handler {
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what) {
                case Constants.MESSAGE_READ_CHAT_DATA:
                    if(msg.obj != null) {
                        showMessage((String)msg.obj);
                    }
                    break;

                default:
                    break;
            }

            super.handleMessage(msg);
        }
    }	// End of class ActivityHandler




    // 초기 세팅 메소드
    public void setting() {

        // 컴포넌트 연결

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);

        Home_txt = (TextView)findViewById(R.id.home);
        Away_txt = (TextView)findViewById(R.id.away);

        score_txt1 = (TextView)findViewById(R.id.score_txt1);
        score_txt2 = (TextView)findViewById(R.id.score_txt2);
        score_txt3 = (TextView)findViewById(R.id.score_txt3);
        score_txt4 = (TextView)findViewById(R.id.score_txt4);

        btnScore = (ImageButton) findViewById(R.id.btn_score);
        btnEnd = (ImageButton) findViewById(R.id.btn_end);

        // 글꼴 연결
        Home_txt.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        Away_txt.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));

        txt1.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        txt2.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));

        score_txt1.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        score_txt2.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        score_txt3.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        score_txt4.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));



        // 버튼 클릭 이벤트 리스너 등록
        btnScore.setOnClickListener(this);
        btnEnd.setOnClickListener(this);

    }
}