package com.bsoft.signupprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity implements Animation.AnimationListener{
	private View image;
	private Animation animFadeIn, animFadeOut, animFadeSteady;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature( Window .FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams .FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		
		image = findViewById(R.id.image);
		
		animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
		animFadeOut.setAnimationListener(this);
		
		animFadeSteady = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_steady);
		animFadeSteady.setAnimationListener(this);
		
		animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
		animFadeIn.setAnimationListener(this);
		image.startAnimation(animFadeIn);
	}

	@Override
	public void onAnimationStart(Animation p1){
		// TODO: Implement this method
	}

	@Override
	public void onAnimationRepeat(Animation p1){
		// TODO: Implement this method
	}

	@Override
	public void onAnimationEnd(Animation p1){
		if(animFadeOut == p1){
			image.setAlpha(0);
			startActivity(new Intent(this, Main.class));
			finish();
		}else if(animFadeIn == p1){
			image.startAnimation(animFadeSteady);
		}else if(animFadeSteady == p1){
			image.startAnimation(animFadeOut);
		}
	}
}
