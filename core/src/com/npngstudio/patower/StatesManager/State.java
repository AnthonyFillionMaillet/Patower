package com.npngstudio.patower.StatesManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.npngstudio.patower.Game;


public abstract class State {
	
	protected GSM gsm;
	protected OrthographicCamera cam;
	
	protected State(GSM gsm){
		this.gsm = gsm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
	}

	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	
}
