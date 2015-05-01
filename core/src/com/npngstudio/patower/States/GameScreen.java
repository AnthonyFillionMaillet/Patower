package com.npngstudio.patower.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;


public class GameScreen extends State {
	

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);

	}
	
	public void handleInput() {

	}
	
	public void update(float p_DelTem) {
		handleInput();
	}

	public void render(SpriteBatch p_SprBat) {

		p_SprBat.setProjectionMatrix(cam.combined);
		p_SprBat.begin();


		p_SprBat.end();
	}

	
}
