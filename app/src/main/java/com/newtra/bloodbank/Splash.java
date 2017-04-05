package com.newtra.bloodbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {
	private Animation mRotation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ImageView iv = (ImageView) findViewById(R.id.imageViewSplash);
		mRotation = AnimationUtils.loadAnimation(this, R.anim.button_rotate);
		iv.startAnimation(mRotation);
		mRotation.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(getApplicationContext(),
						Registration.class);
				startActivity(intent);
				finish();
			}
		});
		// TODO ANIMATION SPLASH

	}

}
