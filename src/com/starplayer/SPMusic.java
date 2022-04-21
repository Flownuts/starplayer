package com.starplayer;

import android.app.Service;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.IBinder;
import android.content.Context;

public class SPMusic extends Service{
	public static boolean isRunning = false;
	static MediaPlayer player;
	private static Context context;
	@Override
	public IBinder onBind(Intent arg0){
		return null;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		context = this;
		
	setMusicOptions(context,SPEngine.LOOP_BACKGROUND_MUSIC, SPEngine.R_VOLUME, SPEngine.L_VOLUME,
			SPEngine.SPLASH_SCREEN_MUSIC);
				}
	public static void setMusicOptions(Context context, boolean isLooped, int rVolume,
				int lVolume, int soundFile){
					player = MediaPlayer.create(context,  soundFile);
					player.setLooping(isLooped);
					player.setVolume(rVolume, lVolume);
		}
	
	public int onStartCommand(Intent intent, int flags, int startId){
		try
		{
			player.start();
			isRunning = true;
				}catch(Exception e){
			isRunning = false;
			player.stop();
		}		
		return 1;
	}
	
	public void onStart(Intent intent, int startId){		
	}
	
	public IBinder onUnBind(Intent arg0){
		//TODO Auto-generated method stub
		return null;
	}
	
	public void onStop(){
		isRunning = false;
	}
	
	public void onPause(){	
		player.start();
		isRunning = true;
	}
	
	@Override
	public void onDestroy(){
			player.stop();
			player.release();
	}	
	@Override
	public void onLowMemory(){
			//player.stop();
	}
}
	