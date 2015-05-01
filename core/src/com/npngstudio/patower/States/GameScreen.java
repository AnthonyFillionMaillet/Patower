package com.npngstudio.patower.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;


public class GameCoreState extends State {
	

	public GameCoreState(GSM p_Gsm) {
		super(p_Gsm);

	}

	@Override
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
