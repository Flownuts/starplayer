package com.starplayer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.os.Handler;
import android.os.ConditionVariable;
import com.starplayer.SPGameRenderer;

public class SPGameView extends GLSurfaceView {
	
	public static boolean ALLOW_TOUCH = true;
	public SPGameView(Context SPGameRenderer){super(SPGameRenderer);}
	private final ConditionVariable syncObj = new ConditionVariable();

	@Override
	public boolean onTouchEvent(MotionEvent e){
		SPGameView.this.performClick();
		switch (e.getActionMasked()){
		
		case MotionEvent.ACTION_DOWN:
					float w = super.getWidth();
					float h = super.getHeight();
					float x = e.getX();
					float y = e.getY();
	    
		if (ALLOW_TOUCH = true)			{
	    
		//Bottom right click
			if ((x>w/2) &&(y>(3*h)/4)){
				//Highlighter on
				SPGameRenderer.ButtonFrame = 1;
				ALLOW_TOUCH = false;
	    	
				//Handle events
				SPGameRenderer.PropLine+=1;
				if (SPGameRenderer.PropLine>=12){SPGameRenderer.PropLine=0;}	    	
	    		
				//Highlighter off 
				new Handler().postDelayed(new Thread(){
	    		@Override
	    		public void run()					{
	    			SPGameRenderer.ButtonFrame = 0;
	    			ALLOW_TOUCH = true;}},1000);
	    			}	    
		//Bottom left click
			else if ((x<w/2) &&
					(y>(3*h)/4))
	    				{
				//Highlighter on
				SPGameRenderer.ButtonFrame = 2;
				ALLOW_TOUCH = false;
	    	
				//Handle events
				SPGameRenderer.DancerSheet+=1;
				if (SPGameRenderer.DancerSheet>=3){SPGameRenderer.DancerSheet=0;}
	    	
				//Highlighter off
				new Handler().postDelayed(new Thread(){
	    		@Override
	    		public void run()					{	    
	    			SPGameRenderer.ButtonFrame = 0;
	    			ALLOW_TOUCH = true;}},1000);
	    	}

		//No Button clicked but screen clicked
			else 
	    			{
				//fix to scroll through props
				setBackgroundResource(0);
				SPGameRenderer.ButtonFrame = 0;//redundant
	    			}    
				return true;		
						}else return false;
		
	case MotionEvent.ACTION_UP:	
		//fix for highlighting to act properly
		break;
	
}
return true;	}
	
	@Override
	public boolean performClick(){
	super.performClick();
		return true;	
	}	
	
	@Override
	public void onPause() {
		
	      super.onPause();
	      syncObj.close();	      
	        queueEvent(new Runnable() {	 
	            @Override public void run() {
	            SPGameRenderer renderer = new SPGameRenderer();
	            renderer.onViewPause(syncObj);	
	           	setRenderMode(RENDERMODE_WHEN_DIRTY);
	            }});	 
	        syncObj.block();
	}
	}


