package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by new on 2016. 10. 10..
 */
public class BLE_Setting extends Activity {

    // 텍스트 컴포넌트 연결

    private TextView mTime;
    private TextView mScore;
    private TextView mRound;

    private ImageButton mConfirm;

    // 버튼 컴포넌트 연결
    private ImageButton min_up;
    private ImageButton min_down;
    private ImageButton round_up;
    private ImageButton round_down;
    private ImageButton score_up;
    private ImageButton score_down;

    // 토글 버튼 컴포넌트 연결
    private TextView mode_btn;

    final static int Score_mode = 1;
    final static int Time_mode = 2;
    int cur_Status = Score_mode;

    private SoundPool sound1;
    private int soundID1;

    private Intent intent;

    private int min=5, score=20, round=4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_setting);

        // 텍스트 컴포넌트
        mTime = (TextView) findViewById(R.id.value_time);
        mScore = (TextView) findViewById(R.id.value_score);
        mRound = (TextView) findViewById(R.id.value_round);

        // 버튼 컴포넌트
        min_up = (ImageButton)findViewById(R.id.min_up);
        min_down = (ImageButton)findViewById(R.id.min_down);
        round_up = (ImageButton)findViewById(R.id.round_up);
        round_down = (ImageButton)findViewById(R.id.round_down);
        score_up = (ImageButton)findViewById(R.id.score_up);
        score_down = (ImageButton)findViewById(R.id.score_down);

        mConfirm = (ImageButton) findViewById(R.id.confirm_btn);

        // 토글 컴포넌트
        mode_btn = (TextView) findViewById(R.id.mode_btn);

        // 글꼴 등록
        mTime.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        mScore.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        mRound.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));

        mode_btn.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));

        mConfirm.setBackground(getDrawable(R.drawable.form_button_ready));

        // 버튼 클릭 이벤트 리스너 등록
        min_up.setOnClickListener(mClickListener);
        min_down.setOnClickListener(mClickListener);
        round_up.setOnClickListener(mClickListener);
        round_down.setOnClickListener(mClickListener);
        score_up.setOnClickListener(mClickListener);
        score_down.setOnClickListener(mClickListener);

        mConfirm.setOnClickListener(mClickListener);
        mode_btn.setOnClickListener(mClickListener);

        // 사운드 세팅
        sound1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundID1 = sound1.load(this, R.raw.smw_kick, 1);
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.min_up:
                    // 클릭 사운드
                    sound1.play(soundID1,1f,1f,0,0,1f);
                    if(min>59){
                        min=1;
                        mTime.setText(String.valueOf(min));
                    }
                    else {
                        min++;
                        mTime.setText(String.valueOf(min));
                    }
                    break;

                case R.id.min_down:
                    // 클릭 사운드
                    sound1.play(soundID1,1f,1f,0,0,1f);
                    if (min > 1) {
                        min--;
                        mTime.setText(String.valueOf(min));
                    }
                    break;

                case R.id.round_up:
                    // 클릭 사운드
                    sound1.play(soundID1,1f,1f,0,0,1f);
                    if(round>3){
                        round = 1;
                        mRound.setText(String.valueOf(round));
                    }
                    else {
                        round++;
                        mRound.setText(String.valueOf(round));
                    }
                    break;

                case R.id.round_down:
                    // 클릭 사운드
                    sound1.play(soundID1,1f,1f,0,0,1f);
                    if(round>1){
                        round--;
                        mRound.setText(String.valueOf(round));
                    }
                    break;

                case R.id.score_up:
                    // 클릭 사운드
                    sound1.play(soundID1,1f,1f,0,0,1f);
                    if(score>99) {
                        score=10;
                        mScore.setText(String.valueOf(score));
                    }
                    else{
                        score++;
                        mScore.setText(String.valueOf(score));
                    }
                    break;

                case R.id.score_down:
                    // 클릭 사운드
                    sound1.play(soundID1,1f,1f,0,0,1f);
                    if(score>10){
                        score--;
                        mScore.setText(String.valueOf(score));
                    }
                    break;

                case R.id.confirm_btn:
                    switch (cur_Status){
                        case Score_mode:

                            // 클릭 사운드
                            sound1.play(soundID1,1f,1f,0,0,1f);

                            // 1초간 딜레이 후 다음 액티비티 진입
                            Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                public void run() {
                                    intent = new Intent(getBaseContext(), BLE_ScoreMode.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("round", round);
                                    startActivityForResult(intent, 1);
                                    finish();
                                }
                            }, 800);
                            break;

                        case Time_mode:

                            // 클릭 사운드
                            sound1.play(soundID1,1f,1f,0,0,1f);

                            // 1초간 딜레이 후 다음 액티비티 진입
                            Handler handler2 = new Handler();
                            handler2.postDelayed(new Runnable() {
                                public void run() {
                                    intent = new Intent(getBaseContext(), BLE_TimeMode.class);
                                    intent.putExtra("time",(min*60));
                                    intent.putExtra("round", round);
                                    startActivityForResult(intent, 1);
                                    finish();
                                }
                            }, 800);
                            break;
                    }

                    break;
                case R.id.mode_btn:
                    if(cur_Status==Score_mode){
                        min_up.setEnabled(true);
                        min_down.setEnabled(true);
                        mTime.setEnabled(true);
                        score_up.setEnabled(false);
                        score_down.setEnabled(false);
                        mScore.setEnabled(false);
                        mode_btn.setBackground(getDrawable(R.drawable.btn_round));
                        cur_Status = Time_mode;

                        // 클릭 사운드
                        sound1.play(soundID1,1f,1f,0,0,1f);
                    }
                    else{
                        min_up.setEnabled(false);
                        min_down.setEnabled(false);
                        mTime.setEnabled(false);
                        score_up.setEnabled(true);
                        score_down.setEnabled(true);
                        mScore.setEnabled(true);
                        mode_btn.setBackground(getDrawable(R.drawable.btn_score));
                        cur_Status = Score_mode;

                        // 클릭 사운드
                        sound1.play(soundID1,1f,1f,0,0,1f);
                    }
                    break;
            }
        }
    };

}