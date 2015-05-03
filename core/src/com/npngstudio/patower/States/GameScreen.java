package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.npngstudio.patower.Game;
import com.npngstudio.patower.Model.Rectangle;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

import java.util.ArrayList;

import sun.rmi.runtime.Log;


public class GameScreen extends State {

	ShapeRenderer sr;
	ArrayList<Rectangle> G_ArrayRect;
	ArrayList<Color> ArrayColor = new ArrayList<Color>();
	final int DROITE = 0;
	final int GAUCHE = 1;
	final int STOP = 2;
	int RandDirection;
	int vitesse = 8;
	int g_Score = 0;
	int storeRandomColor = 0;
	int random = 1;
	private BitmapFont bit_score;

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);
		Game.myRequestHandler.showAds(true);
		sr = new ShapeRenderer();
		G_ArrayRect = new ArrayList<Rectangle>();
		bit_score = new BitmapFont(Gdx.files.internal("data/text.fnt"), false);
		initColor();
		initRectangle();
	}

	// Génération de la colonne de base
	public void initRectangle(){
		for (int i = 0; i < 5; i++)
		{
			G_ArrayRect.add(new Rectangle(160, i * 80, 160, 80, getColorRand(), false));
		}
	}

	public void initColor()
	{
		ArrayColor.add(new Color(Color.rgb888(255, 108, 180)));
		ArrayColor.add(new Color(Color.rgb888(255, 232, 119)));
		ArrayColor.add(new Color(Color.rgb888(245, 255, 125)));
		ArrayColor.add(new Color(Color.rgb888(198, 255, 142)));
		ArrayColor.add(new Color(Color.rgb888(187, 255, 255)));

	}

	// Permet de récupérer une couleur aléatoirement dans la liste des couleurs
	public Color getColorRand()
	{
		storeRandomColor = random;
		while(random == storeRandomColor) {
			random = (int) (Math.random() * ArrayColor.size());
		}
		return ArrayColor.get(random);
	}

	public void spawnRectangle(){
		RandDirection = (int)(Math.random()*2);
		int randomWidth = 80 + (int)(Math.random()*80);

		if(RandDirection == GAUCHE){
			G_ArrayRect.add(new Rectangle(0 - randomWidth, 550, randomWidth, 80, getColorRand(), false));
		}
		else if(RandDirection == DROITE){
			G_ArrayRect.add(new Rectangle( 480, 550, randomWidth, 80, getColorRand(), false));
		}
	}
	
	public void handleInput() {
		if(Gdx.input.justTouched()){
			// Autorise la descente du bloc
			G_ArrayRect.get(G_ArrayRect.size()-1).setDansLeVide(true);
			RandDirection = STOP;
		}
	}
	
	public void update(float p_DelTem) {
		handleInput();

		//INTERSECT
		for(int i = 1; i < G_ArrayRect.size(); i++) {
			if (G_ArrayRect.get(i).getX() < G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
					&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() > G_ArrayRect.get(i-1).getX()
					&& G_ArrayRect.get(i).getY() + G_ArrayRect.get(i).getHeight() >= G_ArrayRect.get(i-1).getY())
			{
				if(G_ArrayRect.get(i).getY() < G_ArrayRect.get(i-1).getY() + G_ArrayRect.get(i-1).getHeight())
				{
					G_ArrayRect.get(i).setDansLeVide(false);
					G_ArrayRect.get(G_ArrayRect.size()-1).setY(G_ArrayRect.get(i-1).getY() + G_ArrayRect.get(i-1).getHeight());
				}
			}

			//SI LA PIECE TOMBE A COTE = LOSE
			if((G_ArrayRect.get(i).getX() >= G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
				&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() > G_ArrayRect.get(i-1).getX()
				&& G_ArrayRect.get(i).getY() + G_ArrayRect.get(i).getHeight() >= G_ArrayRect.get(i-1).getY()) ||
				(G_ArrayRect.get(i).getX() < G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
				&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() <= G_ArrayRect.get(i-1).getX()
				&& G_ArrayRect.get(i).getY() + G_ArrayRect.get(i).getHeight() >= G_ArrayRect.get(i-1).getY()))
			{
				if(G_ArrayRect.get(i).getY() < G_ArrayRect.get(i-1).getY() + G_ArrayRect.get(i-1).getHeight())
				{
					gsm.push(new MenuScreen(gsm));
				}
			}
		}

		// Quand la colonne a perdu un bloc, on en fait spawn un nouveau
		if(G_ArrayRect.size() == 5){
			spawnRectangle();
		}

		// Quand le bloc touche le haut de la colonne
		if(!G_ArrayRect.get(G_ArrayRect.size()-1).isDansLeVide() && G_ArrayRect.get(G_ArrayRect.size()-1).getY() < 550){

			// Pour chaque rectangle
			for(Rectangle r : G_ArrayRect){
				// On fait descendre la colonne
				r.setY(r.getY() - vitesse / 2);
				// Quand le dernier bloc à disparu de l'écran
				if(G_ArrayRect.get(1).getY() <= 0){
					// On fait descendre le reste de la colonne
					for(int j = 2; j < G_ArrayRect.size(); j++){
						G_ArrayRect.get(j).setY(G_ArrayRect.get(j).getY() - vitesse / 2);
					}
					// On ajoute un point au score
					g_Score++;
					if(g_Score % 5 == 0 && g_Score != 0)
					{
						vitesse ++;
					}
					//System.out.println("Vitesse : " + vitesse + " Score : "  + g_Score);
					// On supprime le bloc qui a disparu (de l'écran) de la liste
					G_ArrayRect.remove(0);
					// Stop la descente de la colonne
					break;
				}
			}

		}

		// Fait bouger à droite ou à gauche le bloc qui a spawn
		if(RandDirection == GAUCHE){
			// Fait avancer le bloc de gauche à droite
			G_ArrayRect.get(G_ArrayRect.size()-1).setX(G_ArrayRect.get(G_ArrayRect.size()-1).getX() + vitesse);
			// Quand le bloc sort de l'écran, le joueur perd
			if(G_ArrayRect.get(G_ArrayRect.size()-1).getX() > 480){
				gsm.push(new MenuScreen(gsm));
			}
		}
		else if (RandDirection == DROITE){
			// Fait avancer le bloc de droite à gauche
			G_ArrayRect.get(G_ArrayRect.size()-1).setX(G_ArrayRect.get(G_ArrayRect.size()-1).getX() - vitesse);
			// Quand le bloc sort de l'écran, le joueur perd
			if(G_ArrayRect.get(G_ArrayRect.size()-1).getX() + G_ArrayRect.get(G_ArrayRect.size()-1).getWidth() < 0){
				gsm.push(new MenuScreen(gsm));
			}
		}
	}

	public void render(SpriteBatch p_SprBat) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		p_SprBat.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);

		for(Rectangle rec : G_ArrayRect) {
			rec.update();
			sr.setColor(rec.getColor());
			sr.rect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
			sr.setColor(Color.BLACK);
			sr.rect(rec.getX(), rec.getY() - 2, rec.getWidth(), 2);
			sr.rect(rec.getX(), rec.getY(), 2, rec.getHeight());
			sr.rect(rec.getX(), rec.getY() + rec.getHeight() - 2, rec.getWidth(), 2);
			sr.rect(rec.getX() + rec.getWidth() - 2, rec.getY(), 2, rec.getHeight());
		}

		sr.end();

		p_SprBat.begin();
		if(g_Score < 10)
			bit_score.draw(p_SprBat, ""+g_Score, 232, 730);
		else if(g_Score < 100)
			bit_score.draw(p_SprBat, ""+g_Score, 212, 730);
		else
			bit_score.draw(p_SprBat, ""+g_Score, 192, 730);
		p_SprBat.end();

	}

	
}
