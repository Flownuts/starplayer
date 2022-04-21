package com.starplayer;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.os.Handler;


public class StarPlayerActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
	new Handler().postDelayed(new Thread(){
		@Override
		public void run(){
			Intent mainMenu = new Intent(StarPlayerActivity.this,
				SPMainMenu.class);
			StarPlayerActivity.this.startActivity(mainMenu);
			StarPlayerActivity.this.finish();
			overridePendingTransition(R.layout.fadein,R.layout.fadeout);
							}		
		}, SPEngine.GAME_THREAD_DELAY);
		}}