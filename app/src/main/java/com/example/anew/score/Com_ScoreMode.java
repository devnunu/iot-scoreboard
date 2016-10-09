package com.example.anew.score;

/**
 * Created by new on 2016. 9. 19..
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Com_ScoreMode extends Activity implements View.OnClickListener{

    // 버튼 컴포넌트 생성
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    // 텍스트 컴포넌트 생성
    private TextView btnUndo;
    private TextView btnEnd;
    private TextView btnScore;

    private TextView btnConfirm;
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
    final static int Left_2up = 1;
    final static int Left_3up = 2;
    final static int Right_2up = 3;
    final static int Right_3up = 4;
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
        setContentView(R.layout.com_scoremode);

        sound1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundID = sound1.load(this, R.raw.coin, 1);

        // 취소의 리스트의 초기값 설정
        undo_list[0] = Init;

        setting();

        // setting 액티비티로부터 값 받아옴
        intent = getIntent();
        score = intent.getExtras().getInt("score");
        round = intent.getExtras().getInt("round");
        RoundScore.setGoal_Round(round);

    }



    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1up:
                    sum1_num += 2;
                    undo_num++;

                    // 스코어가 설정치 이상 일때
                    if (sum1_num >= score) {
                        gameEnd();
                    } else {

                        // 현재 상태 및 취소 리스트 동작 등록
                        undo_Status = Left_2up;
                        undo_list[undo_num] = Left_2up;
                        score_txt1.setText(String.format("%d", sum1_num/10));
                        score_txt2.setText(String.format("%d", sum1_num%10));
                        sound1.play(soundID,1f,1f,0,0,1f);
                    }

                break;

            case R.id.btn2up:
                    sum1_num += 3;
                    undo_num++;

                    if (sum1_num >= score) {
                        gameEnd();
                    } else {


                        undo_Status = Left_3up;
                        undo_list[undo_num] = Left_3up;
                        score_txt1.setText(String.format("%d", sum1_num/10));
                        score_txt2.setText(String.format("%d", sum1_num%10));
                        sound1.play(soundID,1f,1f,0,0,1f);
                    }
                break;

            case R.id.btn3up:
                    sum2_num += 2;
                    undo_num++;

                    if (sum2_num >= score) {
                        gameEnd();
                    } else {

                        undo_Status = Right_2up;
                        undo_list[undo_num] = Right_2up;
                        score_txt3.setText(String.format("%d", sum2_num/10));
                        score_txt4.setText(String.format("%d", sum2_num%10));
                        sound1.play(soundID,1f,1f,0,0,1f);
                    }
                break;

            case R.id.btn4up:
                    sum2_num += 3;
                    undo_num++;

                    if (sum2_num >= score) {
                        gameEnd();
                    } else {

                        undo_Status = Right_3up;
                        undo_list[undo_num] = Right_3up;
                        score_txt3.setText(String.format("%d", sum2_num/10));
                        score_txt4.setText(String.format("%d", sum2_num%10));
                        sound1.play(soundID,1f,1f,0,0,1f);
                    }
                break;

            // 취소 버튼 클릭시
            case R.id.btn_undo:
                // 이전에 누른 기능별로 분류
                switch (undo_Status) {
                    case Init:
                        break;

                    case Left_2up:
                        sum1_num -= 2;
                        undo_num--;

                        undo_Status = undo_list[undo_num];
                        score_txt1.setText(String.format("%d", sum1_num/10));
                        score_txt2.setText(String.format("%d", sum1_num%10));
                        break;

                    case Left_3up:
                        sum1_num -= 3;
                        undo_num--;

                        undo_Status = undo_list[undo_num];
                        score_txt1.setText(String.format("%d", sum1_num/10));
                        score_txt2.setText(String.format("%d", sum1_num%10));
                        break;

                    case Right_2up:
                        sum2_num -= 2;
                        undo_num--;

                        undo_Status = undo_list[undo_num];
                        score_txt3.setText(String.format("%d", sum2_num/10));
                        score_txt4.setText(String.format("%d", sum2_num%10));
                        break;

                    case Right_3up:
                        sum2_num -= 3;
                        undo_num--;

                        undo_Status = undo_list[undo_num];
                        score_txt3.setText(String.format("%d", sum2_num/10));
                        score_txt4.setText(String.format("%d", sum2_num%10));
                        break;

                }
                break;


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

        round_end = (TextView)input_dlg.findViewById(R.id.round_end);
        round_num = (TextView)input_dlg.findViewById(R.id.round_num);

        round_end.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        round_num.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

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



    // 초기 세팅 메소드
    public void setting() {

        // 컴포넌트 연결
        btn1 = (Button) findViewById(R.id.btn1up);
        btn2 = (Button) findViewById(R.id.btn2up);
        btn3 = (Button) findViewById(R.id.btn3up);
        btn4 = (Button) findViewById(R.id.btn4up);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);

        Home_txt = (TextView)findViewById(R.id.home);
        Away_txt = (TextView)findViewById(R.id.away);

        score_txt1 = (TextView)findViewById(R.id.score_txt1);
        score_txt2 = (TextView)findViewById(R.id.score_txt2);
        score_txt3 = (TextView)findViewById(R.id.score_txt3);
        score_txt4 = (TextView)findViewById(R.id.score_txt4);

        btnUndo = (TextView) findViewById(R.id.btn_undo);
        btnScore = (TextView) findViewById(R.id.btn_score);
        btnEnd = (TextView) findViewById(R.id.btn_end);

        // 글꼴 연결
        Home_txt.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Away_txt.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        txt1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        txt2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));

        score_txt1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        score_txt2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        score_txt3.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        score_txt4.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));

        btnUndo.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        btnScore.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        btnEnd.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        btn1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        btn2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        btn3.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        btn4.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));


        // 버튼 클릭 이벤트 리스너 등록
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        btnUndo.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnEnd.setOnClickListener(this);

    }
}