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
	final int DROITE = 0;
	final int GAUCHE = 1;
	final int STOP = 2;
	int RandDirection;
	int vitesse = 16;
	int g_Score = 0;

	public GameScreen(GSM p_Gsm){
		super(p_Gsm);
		sr = new ShapeRenderer();
		G_ArrayRect = new ArrayList<Rectangle>();
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
		ArrayColor.add(Color.BLUE);
		ArrayColor.add(Color.GREEN);
		ArrayColor.add(Color.YELLOW);
		ArrayColor.add(Color.RED);
		ArrayColor.add(Color.MAGENTA);
	}

	// Permet de récupérer une couleur aléatoirement dans la liste des couleurs
	public Color getColorRand()
	{
		int random = (int)(Math.random()*ArrayColor.size());
		return ArrayColor.get(random);
	}

	public void spawnRectangle(){
		RandDirection = (int)(Math.random()*2);
		int randomWidth = 80 + (int)(Math.random()*80);

		if(RandDirection == GAUCHE){
			G_ArrayRect.add(new Rectangle(0 - randomWidth, 650, randomWidth, 80, getColorRand(), false));
		}
		else if(RandDirection == DROITE){
			G_ArrayRect.add(new Rectangle( 480, 650, randomWidth, 80, getColorRand(), false));
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

		if(g_Score % 5 == 0 && g_Score != 0)
		{
			vitesse ++;
		}

		//INTERSECT
		for(int i = 1; i < G_ArrayRect.size(); i++) {
			if (G_ArrayRect.get(i).getX() <= G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
					&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() >= G_ArrayRect.get(i-1).getX()
					&& G_ArrayRect.get(i).getY() + G_ArrayRect.get(i).getHeight() >= G_ArrayRect.get(i-1).getY())
			{
				if(G_ArrayRect.get(i).getY() < G_ArrayRect.get(i-1).getY() + G_ArrayRect.get(i-1).getHeight())
				{
					G_ArrayRect.get(i).setDansLeVide(false);
					G_ArrayRect.get(G_ArrayRect.size()-1).setY(G_ArrayRect.get(i-1).getY() + G_ArrayRect.get(i-1).getHeight());
				}
			}

			//SI LA PIECE TOMBE A COTE = LOSE
			if((G_ArrayRect.get(i).getX() > G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
				&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() >= G_ArrayRect.get(i-1).getX()
				&& G_ArrayRect.get(i).getY() + G_ArrayRect.get(i).getHeight() >= G_ArrayRect.get(i-1).getY()) ||
				(G_ArrayRect.get(i).getX() <= G_ArrayRect.get(i-1).getX() + G_ArrayRect.get(i-1).getWidth()
				&& G_ArrayRect.get(i).getX() + G_ArrayRect.get(i).getWidth() < G_ArrayRect.get(i-1).getX()
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
		if(!G_ArrayRect.get(G_ArrayRect.size()-1).isDansLeVide() && G_ArrayRect.get(G_ArrayRect.size()-1).getY() < 650){
			// On ajoute un point au score
			g_Score++;
			// Pour chaque rectangle
			for(Rectangle r : G_ArrayRect){
				// On fait descendre la colonne
				r.setY(r.getY() - vitesse / 2);
				// Quand le dernier bloc à disparu de l'écran
				if(G_ArrayRect.get(1).getY() == 0){
					// On fait descendre le reste de la colonne
					for(int j = 2; j < G_ArrayRect.size(); j++){
						G_ArrayRect.get(j).setY(G_ArrayRect.get(j).getY() - vitesse / 2);
					}
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
