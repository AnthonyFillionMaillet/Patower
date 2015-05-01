package com.npngstudio.patower;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.npngstudio.patower.StatesManager.GSM;

public class Game extends ApplicationAdapter {

	/*public static IActivityRequestHandler myRequestHandler;
	public static ActionResolver actionResolver;*/

	public static final String TITLE = "Omo";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	private GSM g_Gsm;
	private SpriteBatch g_SprBat;
	
	/*public Omo(IActivityRequestHandler handler, ActionResolver action){
		myRequestHandler = handler;
		actionResolver = action;
	}*/
	
	public void create () {
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);

		g_SprBat = new SpriteBatch();
		g_Gsm = new GSM();

		//gsm.push(new NPNGState(gsm));
	}

	
	public void render () {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		g_Gsm.update(Gdx.graphics.getDeltaTime());
		g_Gsm.render(g_SprBat);
	}
}
