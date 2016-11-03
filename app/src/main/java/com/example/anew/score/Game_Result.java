package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by new on 2016. 9. 10..
 */
public class Game_Result extends Activity implements Serializable, View.OnClickListener {

    // 텍스트뷰 컴포넌트 연결

    private TextView H_Score1;
    private TextView H_Score2;
    private TextView H_Score3;
    private TextView H_Score4;
    private TextView A_Score1;
    private TextView A_Score2;
    private TextView A_Score3;
    private TextView A_Score4;

    // 버튼 컴포넌트 연결
    private ImageButton score_done;

    private ArrayList HomeScore = new ArrayList();
    private ArrayList AwayScore = new ArrayList();

    private int[] arr_Home = {0,0,0,0};
    private int[] arr_Away = {0,0,0,0};

    // 인텐트 생성
    private Intent intent;

    // 라운드 변수
    private int Round;
    private int goal_round;

    // 스코어 객체 생성
    private Game_Info RoundScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_result);


        H_Score1 = (TextView)findViewById(R.id.H_score1);
        H_Score2 = (TextView)findViewById(R.id.H_score2);
        H_Score3 = (TextView)findViewById(R.id.H_score3);
        H_Score4 = (TextView)findViewById(R.id.H_score4);
        A_Score1 = (TextView)findViewById(R.id.A_score1);
        A_Score2 = (TextView)findViewById(R.id.A_score2);
        A_Score3 = (TextView)findViewById(R.id.A_score3);
        A_Score4 = (TextView)findViewById(R.id.A_score4);

        score_done = (ImageButton) findViewById(R.id.score_done);

        H_Score1.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        H_Score2.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        H_Score3.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        H_Score4.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));

        A_Score1.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        A_Score2.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        A_Score3.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));
        A_Score4.setTypeface(Typeface.createFromAsset(getAssets(),"NanumGothicExtraBold.otf"));



        // 그 전액티비티에서 받아온 스코어
        intent = getIntent();
        RoundScore = (Game_Info)intent.getSerializableExtra("RoundScore");


        // 스코어 저장
        HomeScore = RoundScore.getHomeScore();
        AwayScore = RoundScore.getAwayScore();
        Round = RoundScore.getRound();
        goal_round = RoundScore.getGoal_Round();

        // 텍스트 뷰에 스코어값 세팅
        H_Score1.setText(String.valueOf(HomeScore.get(0)));
        H_Score2.setText(String.valueOf(HomeScore.get(1)));
        H_Score3.setText(String.valueOf(HomeScore.get(2)));
        H_Score4.setText(String.valueOf(HomeScore.get(3)));

        A_Score1.setText(String.valueOf(AwayScore.get(0)));
        A_Score2.setText(String.valueOf(AwayScore.get(1)));
        A_Score3.setText(String.valueOf(AwayScore.get(2)));
        A_Score4.setText(String.valueOf(AwayScore.get(3)));


        // 이벤트 클릭 리스너
        score_done.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.score_done:
                if(Round==goal_round-1) {
                    intent = new Intent(getBaseContext(), Intro_Title.class);
                    startActivityForResult(intent, 1);
                    finish();
                }
                else{
                    onBackPressed();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(Round==goal_round-1) {}
        else{
            super.onBackPressed();
        }
    }
}