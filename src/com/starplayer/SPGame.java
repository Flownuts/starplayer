package com.starplayer;

import android.app.Activity;
import android.os.Bundle;

public class SPGame extends Activity {

	public static SPGameView gameView;
	public static SPGameRenderer renderer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		gameView = new SPGameView(this);		
		renderer = new SPGameRenderer();
		gameView.setBackgroundResource(R.drawable.load0);
		gameView.setZOrderOnTop(true);
		gameView.setRenderer(renderer);
		setContentView(gameView);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		gameView.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		gameView.onPause();
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		onDestroy();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.finish();
		}
}
	