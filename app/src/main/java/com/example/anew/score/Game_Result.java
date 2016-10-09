package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by new on 2016. 9. 10..
 */
public class Game_Result extends Activity implements Serializable, View.OnClickListener {

    // 텍스트뷰 컴포넌트 연결
    private TextView Home;
    private TextView Away;

    private TextView Q1;
    private TextView Q2;
    private TextView Q3;
    private TextView Q4;

    private TextView H_Score1;
    private TextView H_Score2;
    private TextView H_Score3;
    private TextView H_Score4;
    private TextView A_Score1;
    private TextView A_Score2;
    private TextView A_Score3;
    private TextView A_Score4;

    // 버튼 컴포넌트 연결
    private TextView score_done;

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

        Home = (TextView)findViewById(R.id.home);
        Away = (TextView)findViewById(R.id.away);

        Q1 = (TextView)findViewById(R.id.Q1);
        Q2 = (TextView)findViewById(R.id.Q2);
        Q3 = (TextView)findViewById(R.id.Q3);
        Q4 = (TextView)findViewById(R.id.Q4);

        H_Score1 = (TextView)findViewById(R.id.H_score1);
        H_Score2 = (TextView)findViewById(R.id.H_score2);
        H_Score3 = (TextView)findViewById(R.id.H_score3);
        H_Score4 = (TextView)findViewById(R.id.H_score4);
        A_Score1 = (TextView)findViewById(R.id.A_score1);
        A_Score2 = (TextView)findViewById(R.id.A_score2);
        A_Score3 = (TextView)findViewById(R.id.A_score3);
        A_Score4 = (TextView)findViewById(R.id.A_score4);

        score_done = (TextView) findViewById(R.id.score_done);

        Home.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Away.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        Q1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Q2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Q3.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        Q4.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        H_Score1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        H_Score2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        H_Score3.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        H_Score4.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));

        A_Score1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        A_Score2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        A_Score3.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));
        A_Score4.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro Bold.otf"));

        score_done.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));


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

        for(int i = 0; i<4; i++) {
            arr_Home[i] = Integer.parseInt(HomeScore.get(i).toString());
            arr_Away[i] = Integer.parseInt(AwayScore.get(i).toString());
        }

        if(arr_Home[0]>arr_Away[0]){
            H_Score1.setTextColor(Color.GREEN);
        }
        else if(arr_Away[0]>arr_Home[0]){
            A_Score1.setTextColor(Color.GREEN);
        }
        else{}

        if(arr_Home[1]>arr_Away[1]){
            H_Score2.setTextColor(Color.GREEN);
        }
        else if(arr_Away[1]>arr_Home[1]){
            A_Score2.setTextColor(Color.GREEN);
        }
        else{}

        if(arr_Home[2]>arr_Away[2]){
            H_Score3.setTextColor(Color.GREEN);
        }
        else if(arr_Away[2]>arr_Home[2]){
            A_Score3.setTextColor(Color.GREEN);
        }
        else{}

        if(arr_Home[3]>arr_Away[3]){
            H_Score4.setTextColor(Color.GREEN);
        }
        else if(arr_Away[3]>arr_Home[3]){
            A_Score4.setTextColor(Color.GREEN);
        }
        else{}

        // 이벤트 클릭 리스너
        score_done.setOnClickListener(this);

        if(Round==goal_round-1) {
            score_done.setText("확  인");
        }



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