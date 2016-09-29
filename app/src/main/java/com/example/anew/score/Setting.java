package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

/**
 * Created by new on 2016. 9. 6..
 */
public class Setting extends Activity {

    // 텍스트 컴포넌트 연결
    private TextView Setting;

    private TextView mTime;
    private TextView mScore;
    private TextView mRound;

    private TextView Mode;
    private TextView Round;
    private TextView Time;
    private TextView Score;

    private TextView mConfirm;

    // 버튼 컴포넌트 연결
    private Button min_up;
    private Button min_down;
    private Button round_up;
    private Button round_down;
    private Button score_up;
    private Button score_down;


    // 토글 버튼 컴포넌트 연결
    private TextView mode_btn;

    final static int Score_mode = 1;
    final static int Time_mode = 2;
    int cur_Status = Score_mode;



    private Intent intent;

    private int min=5, score=20, round=4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        // 텍스트 컴포넌트
        Setting = (TextView) findViewById(R.id.setting);
        mTime = (TextView) findViewById(R.id.value_time);
        mScore = (TextView) findViewById(R.id.value_score);
        mRound = (TextView) findViewById(R.id.value_round);

        Mode = (TextView)findViewById(R.id.txt_mode);
        Round = (TextView) findViewById(R.id.txt_round);
        Time = (TextView) findViewById(R.id.txt_time);
        Score = (TextView) findViewById(R.id.txt_score);

        // 버튼 컴포넌트
        min_up = (Button)findViewById(R.id.min_up);
        min_down = (Button)findViewById(R.id.min_down);
        round_up = (Button)findViewById(R.id.round_up);
        round_down = (Button)findViewById(R.id.round_down);
        score_up = (Button)findViewById(R.id.score_up);
        score_down = (Button)findViewById(R.id.score_down);

        mConfirm = (TextView) findViewById(R.id.confirm_btn);

        // 토글 컴포넌트
        mode_btn = (TextView) findViewById(R.id.mode_btn);

        // 글꼴 등록
        Setting.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        mTime.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        mScore.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        mRound.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));

        Mode.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Round.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Time.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Score.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        mode_btn.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        mConfirm.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        // 버튼 클릭 이벤트 리스너 등록
        min_up.setOnClickListener(mClickListener);
        min_down.setOnClickListener(mClickListener);
        round_up.setOnClickListener(mClickListener);
        round_down.setOnClickListener(mClickListener);
        score_up.setOnClickListener(mClickListener);
        score_down.setOnClickListener(mClickListener);

        mConfirm.setOnClickListener(mClickListener);
        mode_btn.setOnClickListener(mClickListener);
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.min_up:
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
                    if (min > 1) {
                        min--;
                        mTime.setText(String.valueOf(min));
                    }
                    break;

                case R.id.round_up:
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
                    if(round>1){
                        round--;
                        mRound.setText(String.valueOf(round));
                    }
                    break;

                case R.id.score_up:
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
                    if(score>10){
                        score--;
                        mScore.setText(String.valueOf(score));
                    }
                    break;

                case R.id.confirm_btn:
                    switch (cur_Status){
                        case Score_mode:

                            // 깜빡이는 애니메이션
                            Animation anim1 = new AlphaAnimation(0.0f, 1.0f);
                            anim1.setDuration(50); //You can manage the time of the blink with this parameter
                            anim1.setStartOffset(20);
                            anim1.setRepeatMode(Animation.REVERSE);
                            anim1.setRepeatCount(Animation.INFINITE);
                            mConfirm.startAnimation(anim1);

                            // 1초간 딜레이 후 다음 액티비티 진입
                            Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                public void run() {
                                    intent = new Intent(getBaseContext(), ScoreMode.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("round", round);
                                    startActivityForResult(intent, 1);
                                    finish();
                                }
                            }, 800);
                            break;

                        case Time_mode:
                            // 깜빡이는 애니메이션
                            Animation anim2 = new AlphaAnimation(0.0f, 1.0f);
                            anim2.setDuration(50); //You can manage the time of the blink with this parameter
                            anim2.setStartOffset(20);
                            anim2.setRepeatMode(Animation.REVERSE);
                            anim2.setRepeatCount(Animation.INFINITE);
                            mConfirm.startAnimation(anim2);

                            // 1초간 딜레이 후 다음 액티비티 진입
                            Handler handler2 = new Handler();
                            handler2.postDelayed(new Runnable() {
                                public void run() {
                                    intent = new Intent(getBaseContext(), TimeMode.class);
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
                        //mode_btn.setBackgroundColor(Color.BLUE);
                        Time.setEnabled(true);
                        min_up.setEnabled(true);
                        min_down.setEnabled(true);
                        mTime.setEnabled(true);
                        Score.setEnabled(false);
                        score_up.setEnabled(false);
                        score_down.setEnabled(false);
                        mScore.setEnabled(false);
                        mode_btn.setText("시  간");
                        mode_btn.setTextColor(Color.MAGENTA);
                        cur_Status = Time_mode;
                    }
                    else{
                        //mode_btn.setBackgroundColor(Color.RED);
                        Time.setEnabled(false);
                        min_up.setEnabled(false);
                        min_down.setEnabled(false);
                        mTime.setEnabled(false);
                        Score.setEnabled(true);
                        score_up.setEnabled(true);
                        score_down.setEnabled(true);
                        mScore.setEnabled(true);
                        mode_btn.setText("스코어");
                        mode_btn.setTextColor(Color.RED);
                        cur_Status = Score_mode;
                    }
                    break;
            }
        }
    };

}
