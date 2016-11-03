package com.example.anew.score;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by new on 2016. 9. 6..
 */
public class Intro_Title extends Activity {

    private ImageButton mbtn;
    private Intent intent;
    private BackPressCloseHandler backPressCloseHandler;

    private SoundPool sound1;
    private int soundID1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_title);

        // 취소 버튼 핸들러 연결
        backPressCloseHandler = new BackPressCloseHandler(this);

        // 텍스트 컴포넌트 연결
        mbtn = (ImageButton)findViewById(R.id.intro);

        // 사운드 세팅
        sound1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundID1 = sound1.load(this, R.raw.smw_kick, 1);

        // 클릭 리스너 등록
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 클릭 사운드
                sound1.play(soundID1,1f,1f,0,0,1f);

                // 1초간 딜레이 후 다음 액티비티 진입
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        intent = new Intent(getBaseContext(), Intro_menu.class);
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
