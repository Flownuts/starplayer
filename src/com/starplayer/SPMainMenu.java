package com.starplayer;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Handler;

import com.google.android.gms.ads.*;

public class SPMainMenu extends Activity {
	
	public static InterstitialAd Ad;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		 //Set menu button options 
		final ImageButton start = (ImageButton)findViewById(R.id.btnStart);
		final ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
		
		start.getBackground().setAlpha(SPEngine.MENU_BUTTON_ALPHA);
		start.setHapticFeedbackEnabled(SPEngine.HAPTIC_BUTTON_FEEDBACK);
		
		exit.getBackground().setAlpha(SPEngine.MENU_BUTTON_ALPHA);
		exit.setHapticFeedbackEnabled(SPEngine.HAPTIC_BUTTON_FEEDBACK);
			
	start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				start.setImageResource(R.drawable.start_down);
				new Handler().post(new Thread(){
					@Override
					public void run(){					
						Intent game = new Intent(SPMainMenu.this,SPGame.class);
						SPMainMenu.this.startActivity(game);
						overridePendingTransition(R.layout.fadein,R.layout.fadeout);
									 } 		   }//,SPEngine.GAME_LOAD_DELAY
								   );}}
							 );
	exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				exit.setImageResource(R.drawable.exit_down);
				boolean clean = false;
				clean = SPEngine.onExit(v);
				if (clean)
				{int pid= android.os.Process.myPid();
				android.os.Process.killProcess(pid);}
										}        }
							);	
		
		Ad = new InterstitialAd(this);
		Ad.setAdUnitId("ca-app-pub-3252436141717227/4635104392");
		
		AdRequest adRequest = new AdRequest.Builder()
        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        //.addTestDevice("")
               .build();

		Ad.loadAd(adRequest);
		
		Ad.setAdListener(new AdListener(){
			@Override
			public void onAdClosed(){
				start.setImageResource(R.drawable.start_up);
			}			
		});
		/** Start background music */
		SPEngine.musicThread = new Thread(){
				public void run(){
					Intent bgmusac = new
				Intent(getApplicationContext(), SPMusic.class);
					startService(bgmusac);
					SPEngine.context = getApplicationContext();
				}
		};
		SPEngine.musicThread.start();
	}	

	@Override
	protected void onResume()
	{
		super.onResume();
		//start.setImageResource(R.drawable.start_down);
		displayAd();
	}	

	public void displayAd() {
	   if (Ad.isLoaded()) {
		 //setContentView(R.layout.ad_view);
	     Ad.show();
	   }
	 }
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		final View v= null;
		SPEngine.onExit(v);
	}
}
