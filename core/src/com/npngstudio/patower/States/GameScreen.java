package com.npngstudio.patower.States;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;



public class GameScreen extends State {

	ShapeRenderer sr;

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);
		sr = new ShapeRenderer();
	}
	
	public void handleInput() {

	}
	
	public void update(float p_DelTem) {
		handleInput();
	}

	public void render(SpriteBatch p_SprBat) {

		p_SprBat.setProjectionMatrix(cam.combined);

		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(0, 0, 350, 350);
		sr.end();

		p_SprBat.begin();


		p_SprBat.end();
	}

	
}
