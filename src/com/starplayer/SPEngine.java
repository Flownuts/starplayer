package com.starplayer;

import java.util.HashMap;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.content.Context;
import android.content.Intent;
//import android.graphics.drawable.Drawable;
import android.view.View;
import android.app.Application;

public class SPEngine extends Application{
	
	public enum TrackerName {
		    APP_TRACKER, // Tracker used only in this app.
		    GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
		    ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
		  }

		  HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
		  
		  synchronized Tracker getTracker(TrackerName trackerId) {
			    if (!mTrackers.containsKey(trackerId)) {

			      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			      Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(R.xml.app_tracker)
			          : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
			              : analytics.newTracker(R.xml.ecommerce_tracker);
			      mTrackers.put(trackerId, t);

			    }
			    return mTrackers.get(trackerId);
			  }
	
	public static final int GAME_THREAD_DELAY=1000;
	public static final int GAME_LOAD_DELAY=5000;
	public static final int MENU_BUTTON_ALPHA = 0;
	public static final boolean HAPTIC_BUTTON_FEEDBACK = true;
	public static final int SPLASH_SCREEN_MUSIC = R.raw.musac3;
	public static final int R_VOLUME = 100;
	public static final int L_VOLUME = 100;
	public static final boolean LOOP_BACKGROUND_MUSIC = true;
	public static Context context;
	public static Thread musicThread;
	
	//public static boolean loaded = false;
	//static Drawable drawable;  //possible use later with remote download
	
	public static boolean ReMain = false;
	
//  Button sheets		
	public static final int BUTTONS_0 = R.drawable.buttons0;
	
//  Dancer sheets
	
	public static final int DANCER_0 = R.drawable.dancer0;
	public static final int DANCER_1 = R.drawable.dancer1;
	public static final int DANCER_2 = R.drawable.dancer2;
	//public static final int DANCER_3 = R.drawable.dancer3;
	//public static final int DANCER_4 = R.drawable.dancer4;
	
//  Prop sheets
	public static final int PROPS_0 = R.drawable.props0;
	//public static final int PROPS_1 = R.drawable.props1;
	//public static final int PROPS_2 = R.drawable.props2;
	//public static final int PROPS_3 = R.drawable.props3;
	//public static final int PROPS_4 = R.drawable.props4;
	
	/* not used, but set up for future remote file download
	public static Drawable NewFile(Drawable drawable) 
	{
	try
		{
		URL SSurl = new URL("https://app.box.com/representation/file_version_19358196765/image_2048_jpg/1.jpg");
		InputStream in = new BufferedInputStream((InputStream) SSurl.getContent());
		drawable = Drawable.createFromStream(in, INPUT_SERVICE);
				}
	catch(Exception e) 
		{
		return null;
		}
	return drawable;
	}					*/

		public static boolean onExit(View v) 
		{
		try
			{
			Intent bgmusac = new Intent(context, SPMusic.class);
			context.stopService(bgmusac);
			return true;
			}
		catch(Exception e) 
			{
			return false;
			}
		}
		
}
