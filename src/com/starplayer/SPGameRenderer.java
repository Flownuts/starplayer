package com.starplayer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.os.ConditionVariable;

public class SPGameRenderer implements Renderer{

	private SPButtons buttons0 = new SPButtons();
		
	private SPProps props0 = new SPProps();
	//private SPProps props1 = new SPProps();
	//private SPProps props2 = new SPProps();
	//private SPProps props3 = new SPProps();
	//private SPProps props4 = new SPProps();
	
	private SPDancer dancer0 = new SPDancer();
	private SPDancer dancer1 = new SPDancer();
	private SPDancer dancer2 = new SPDancer();
	//private SPDancer dancer3 = new SPDancer();
	//private SPDancer dancer4 = new SPDancer();
	
	public static int ButtonFrame = 0;
		
	public static int PropSheet = 0;
	public static int PropLine = 0;
	public int PropFrame = 0;
	
	public static int DancerSheet = 0;
	public int DancerLine = 0;
	public int DancerFrame = 0;
	
	private long time = 0;
	private long time2 = 0;
	
	private void Play (GL10 gl){
//**************************************************************  Props				
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(1.0f, 1.0f, 1.0f);
				gl.glTranslatef(0.0f, 0.0f, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
					
				//flip image
				gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);				
				gl.glTranslatef(0.0f, 1.0f, 0.0f);
					
			//Props  12x12 sprite sheet 5 sheets //12fps
				gl.glTranslatef(PropFrame/12.0f, PropLine/12.0f, 0.0f);
				switch (PropSheet){
				case 0:
				props0.draw(gl);
				break;
				//case 1:
				///props1.draw(gl);
				//break;
				//case 2:
				//props2.draw(gl);
				//break;
				//case 3:
				//props3.draw(gl);
				//break;
				//case 4:
				//props4.draw(gl);
				//break;
				}
				gl.glPopMatrix();
				gl.glLoadIdentity();
		
//***********************************************************   Dancer								
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(1.0f, 1.0f, 1.0f);
				gl.glTranslatef(0.0f, 0.0f, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f, 0.0f, 0f);
				
				//flip image
				gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);				
				gl.glTranslatef(0.0f, 1.0f, 0.0f);
				
		//Dancer  12x12 sprite sheet 5 sheets				
				gl.glTranslatef(DancerFrame/12.0f, DancerLine/12.0f, 0.0f);
				switch (DancerSheet){
				case 0:
					dancer0.draw(gl);
					break;
				case 1:
					dancer1.draw(gl);
					break;
				case 2:
					dancer2.draw(gl);
					break;
				case 3:
					//dancer3.draw(gl);
					break;
				case 4:
					//dancer4.draw(gl);
					break;
				}
				gl.glPopMatrix();
				gl.glLoadIdentity();
				
//*****************************************************************  Buttons		
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(1.0f, 1.0f, 1.0f);
				gl.glTranslatef(0.0f, 0.0f, 0.0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
					
				//flip image
				gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);				
				gl.glTranslatef(0.0f, 1.0f, 0.0f);
				//gl.glTranslatef(0.0f, 0.0f, 0.1f);//		Adjust z-value for draw order
					
			//Background  12x12 sprite sheet 5 sheets
				gl.glTranslatef(ButtonFrame/12.0f, 0.0f, 0.0f);
				
				buttons0.draw(gl);
				
				//reset matrices prior to next render
					gl.glPopMatrix();
					gl.glLoadIdentity();				
				
//************************************************************************************************				
				// Update 12fps Frame Locations 5 sheets
			
				if ((System.currentTimeMillis()- time) > (1000/12)){
				
				//PropFrame+=1;					
				DancerFrame+=1;
				
				//if (PropFrame>=12){PropFrame=0;PropLine+=1;}
				//if (PropLine>=12){PropLine=0;PropSheet+=1;}
				//if (PropSheet>=5){PropSheet=0;} //change value (1) to # of sheets				
					
				if (DancerFrame>=12){DancerFrame=0;DancerLine+=1;}
				if (DancerLine>=12){DancerLine=0;} //DancerSheet+=1;} //dancer sheet adjusted with L button
				//if (DancerSheet>=1){DancerSheet=0;}
								
				time = System.currentTimeMillis();
				}
				
				// Update 4fps Props
				
				if ((System.currentTimeMillis()-time2) > (1000/4)){
				PropFrame+=1;
				if (PropFrame>=12){PropFrame=0;}
				time2 = System.currentTimeMillis();
				}
				
				
				//**************************************************//				
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		Play(gl);		
		gl.glEnable(GL10.GL_BLEND);
		//gl.glColor4f(1, 1, 1, 1);
		//gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
		/* **** */
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
		//gl.glBlendFunc(GL10.GL_SRC_COLOR, GL10.GL_SRC_COLOR);
		
		}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height){
		gl.glViewport(0,  0,  width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config){

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearColor(0, 0, 0, 0);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		//gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glDepthFunc(GL10.GL_ALWAYS);
		gl.glEnable(GL10.GL_BLEND);
		gl.glColor4f(1, 1, 1, 1);
		//gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
		/* **** */
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
		//gl.glBlendFunc(GL10.GL_SRC_COLOR, GL10.GL_SRC_COLOR);
		
		props0.loadTexture(gl, SPEngine.PROPS_0, SPEngine.context);
		//props1.loadTexture(gl, SPEngine.PROPS_1, SPEngine.context);
		//props2.loadTexture(gl, SPEngine.PROPS_2, SPEngine.context);
		//props3.loadTexture(gl, SPEngine.PROPS_3, SPEngine.context);
		//props4.loadTexture(gl, SPEngine.PROPS_4, SPEngine.context);
		
		dancer0.loadTexture(gl, SPEngine.DANCER_0, SPEngine.context);
		dancer1.loadTexture(gl, SPEngine.DANCER_1, SPEngine.context);
		dancer2.loadTexture(gl, SPEngine.DANCER_2, SPEngine.context);
		//dancer3.loadTexture(gl, SPEngine.DANCER_3, SPEngine.context);
		//dancer4.loadTexture(gl, SPEngine.DANCER_4, SPEngine.context);
		
		buttons0.loadTexture(gl, SPEngine.BUTTONS_0, SPEngine.context);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(1.0f, 1.0f, 1.0f);
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
			
		//flip image
		gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);				
		gl.glTranslatef(0.0f, 1.0f, 0.0f);
		//gl.glTranslatef(0.0f, 0.0f, 0.1f);//		Adjust z-value for draw order
			
	//Buttons  12x12 sprite sheet 5 sheets
		gl.glTranslatef(ButtonFrame/12.0f, 0.0f, 0.0f);		
		}
	public void onViewPause(ConditionVariable syncObj){
		props0 = null;
		//props1 = null;
		//props2 = null;
		//props3 = null;
		//props4 = null;
		dancer0 = null;
		dancer1 = null;
		dancer2 = null;
		buttons0 = null;
		
		syncObj.open();
	}
}


