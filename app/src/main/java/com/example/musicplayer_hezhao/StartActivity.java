package com.example.musicplayer_hezhao;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by 11120555 on 2020/7/15 9:58
 */
public class StartActivity extends AppCompatActivity {
    private ImageView StartImage;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.startactivity_layout);
        StartImage=findViewById(R.id.image);
        AlphaAnimation anima = new AlphaAnimation(1.0f, 1.0f);
        anima.setDuration(3000);
        StartImage.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }
    private class  AnimationImpl  implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent=new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
