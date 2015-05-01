package com.npngstudio.patower.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;


public class GameCoreState extends State {
	

	public GameCoreState(GSM gsm){
		super(gsm);

	}
	
	public void handleInput() {

	}
	
	public void update(float dt) {
		handleInput();
	}

	public void render(SpriteBatch sb) {
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();

		
		sb.end();
	}

	
}
