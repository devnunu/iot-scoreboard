package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by new on 2016. 9. 6..
 */
public class Intro extends Activity {

    private TextView txt1;
    private TextView txt2;
    private TextView mbtn;
    private Intent intent;
    private BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        // 취소 버튼 핸들러 연결
        backPressCloseHandler = new BackPressCloseHandler(this);

        // 텍스트 컴포넌트 연결
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        mbtn = (TextView)findViewById(R.id.intro);

        // 글꼴 변경
        txt1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        txt2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        mbtn.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        // 클릭 리스너 등록
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 깜빡이는 애니메이션
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(50); //You can manage the time of the blink with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                mbtn.startAnimation(anim);

                // 1초간 딜레이 후 다음 액티비티 진입
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        intent = new Intent(getBaseContext(), BLE_Connect.class);
                        startActivityForResult(intent, 1);
                        finish();
                    }
                }, 800);


            }
        });

    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
