package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.npngstudio.patower.Model.Rectangle;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

import java.util.ArrayList;

import sun.rmi.runtime.Log;


public class GameScreen extends State {

	ShapeRenderer sr;
	ArrayList<Rectangle> G_ArrayRect;

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);
		sr = new ShapeRenderer();
		G_ArrayRect = new ArrayList<Rectangle>();
		initRectangle();

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				spawnRectangle();
			}
		}, 0f, 3f);
	}

	public void initRectangle(){
		// Construction de base
		ArrayList<Color> ArrayColor = new ArrayList<Color>();
		ArrayColor.add(Color.BLUE);
		ArrayColor.add(Color.GREEN);
		ArrayColor.add(Color.YELLOW);
		ArrayColor.add(Color.RED);
		ArrayColor.add(Color.MAGENTA);

		for (int i = 0; i < 5; i++)
		{
			int random = (int)(Math.random()*5);
			G_ArrayRect.add(new Rectangle(160, i * 80, 160, 80, ArrayColor.get(random), false));
		}

	}

	public void spawnRectangle(){
		G_ArrayRect.add(new Rectangle(100, 700, 300, 80, Color.BLUE, false));
	}
	
	public void handleInput() {
		if(Gdx.input.justTouched()){
			G_ArrayRect.get(G_ArrayRect.size()-1).setDansLeVide(true);
		}
	}
	
	public void update(float p_DelTem) {
		handleInput();

	}

	public void render(SpriteBatch p_SprBat) {

		p_SprBat.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);


		sr.begin(ShapeRenderer.ShapeType.Filled);

		//INTERSECT
		for(int i = 1; i < G_ArrayRect.size(); i++) {
				if (G_ArrayRect.get(i).getX() < G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
						&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() > G_ArrayRect.get(i-1).getX()
						&& G_ArrayRect.get(i).getY() + G_ArrayRect.get(i).getHeight() > G_ArrayRect.get(i-1).getY())
				{
					System.out.println(G_ArrayRect.get(i).getWidth() + "    " + G_ArrayRect.size());
					if(G_ArrayRect.get(i).getY() < G_ArrayRect.get(i-1).getY() + G_ArrayRect.get(i-1).getHeight())
					{
						G_ArrayRect.get(i).setDansLeVide(false);
					}
				}

		}

		for(Rectangle rec : G_ArrayRect) {
			rec.update();
			sr.setColor(rec.getColor());
			sr.rect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
		}

		sr.end();

		p_SprBat.begin();


		p_SprBat.end();

	}

	
}
