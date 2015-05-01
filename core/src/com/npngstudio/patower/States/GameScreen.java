package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.npngstudio.patower.Model.Rectangle;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

import java.util.ArrayList;


public class GameScreen extends State {

	ShapeRenderer sr;
	ArrayList<Rectangle> G_ArrayRect;

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);
		sr = new ShapeRenderer();
		G_ArrayRect = new ArrayList<Rectangle>();
		initRectangle();
	}

	public void initRectangle(){
		// Construction de base
		/*for (int i = 0; i <= 5; i++)
		{
			G_ArrayRect.add(new Rectangle(160, i * 80, 160, 80));
		}*/
		G_ArrayRect.add(new Rectangle(160, 200, 160, 80));

	}
	
	public void handleInput() {

	}
	
	public void update(float p_DelTem) {
		handleInput();

	}

	public void render(SpriteBatch p_SprBat) {

		p_SprBat.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);


		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);

		for(Rectangle rec : G_ArrayRect) {
			rec.update();
			sr.rect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
		}

		sr.end();

		p_SprBat.begin();


		p_SprBat.end();

	}

	
}
