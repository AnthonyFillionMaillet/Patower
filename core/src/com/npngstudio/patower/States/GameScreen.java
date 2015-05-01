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
	ArrayList<Color> ArrayColor = new ArrayList<Color>();

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);
		sr = new ShapeRenderer();
		G_ArrayRect = new ArrayList<Rectangle>();
		initRectangle();
	}

	public void initRectangle(){
		// Construction de base
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
		G_ArrayRect.add(new Rectangle(100, 700, 300, 80, Color.BLUE, true));
	}
	
	public void handleInput() {
		if(Gdx.input.justTouched()){
			G_ArrayRect.get(G_ArrayRect.size()-1).setDansLeVide(true);
		}
	}
	
	public void update(float p_DelTem) {
		handleInput();

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

		if(G_ArrayRect.size() == 5){
			spawnRectangle();
		}

		if(!G_ArrayRect.get(G_ArrayRect.size()-1).isDansLeVide()){
			for(Rectangle r : G_ArrayRect){
				r.setY(r.getY() - 1);
				if(G_ArrayRect.get(1).getY() == 0){
					G_ArrayRect.remove(0);
					break;
				}
			}

		}
	}

	public void render(SpriteBatch p_SprBat) {

		p_SprBat.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);


		sr.begin(ShapeRenderer.ShapeType.Filled);



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
