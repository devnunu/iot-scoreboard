package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * Created by new on 2016. 9. 6..
 */
public class Intro_menu extends Activity implements View.OnClickListener {


    private TextView menu1;
    private TextView menu2;
    private Intent intent;
    private BackPressCloseHandler backPressCloseHandler;


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu1:
                // 깜빡이는 애니메이션
                Animation anim1 = new AlphaAnimation(0.0f, 1.0f);
                anim1.setDuration(50); //You can manage the time of the blink with this parameter
                anim1.setStartOffset(20);
                anim1.setRepeatMode(Animation.REVERSE);
                anim1.setRepeatCount(Animation.INFINITE);
                menu1.startAnimation(anim1);

                // 1초간 딜레이 후 다음 액티비티 진입
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    public void run() {
                        intent = new Intent(getBaseContext(), Com_Setting.class);
                        startActivityForResult(intent, 1);
                        finish();
                    }
                }, 800);
                break;
            case R.id.menu2:
                // 깜빡이는 애니메이션
                Animation anim2 = new AlphaAnimation(0.0f, 1.0f);
                anim2.setDuration(50); //You can manage the time of the blink with this parameter
                anim2.setStartOffset(20);
                anim2.setRepeatMode(Animation.REVERSE);
                anim2.setRepeatCount(Animation.INFINITE);
                menu2.startAnimation(anim2);

                // 1초간 딜레이 후 다음 액티비티 진입
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    public void run() {
                        intent = new Intent(getBaseContext(), BLE_Connect.class);
                        startActivityForResult(intent, 1);
                        finish();
                    }
                }, 800);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_menu);

        // 취소 버튼 핸들러 연결
        backPressCloseHandler = new BackPressCloseHandler(this);

        // 텍스트 컴포넌트 연결
        menu1 = (TextView)findViewById(R.id.menu1);
        menu2 = (TextView)findViewById(R.id.menu2);

        // 글꼴 변경
        menu1.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));
        menu2.setTypeface(Typeface.createFromAsset(getAssets(),"RixVideoGame_Pro 3D.otf"));

        // 클릭 리스너 등록
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
