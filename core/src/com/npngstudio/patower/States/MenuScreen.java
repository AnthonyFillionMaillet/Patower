package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.npngstudio.patower.Game;
import com.npngstudio.patower.Model.Rectangle;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

import java.util.ArrayList;

/**
 * Created by Anthony on 01/05/2015.
 */
public class MenuScreen extends State {

    private static final String PREFS_SCORE = "score";
    private static final String PREFS_NAME = "Patower";
    private Preferences preferences = Gdx.app.getPreferences(PREFS_NAME);
    private static int mu = 0;
    private BitmapFont bit_score;
    private int g_Score = 0;

    public MenuScreen(GSM p_Gsm){
        super(p_Gsm);
        Game.myRequestHandler.showAds(true);

        bit_score = new BitmapFont(Gdx.files.internal("data/text.fnt"), false);

        // Création du score au premier lancement de l'appli
        if (!preferences.contains(PREFS_SCORE)) {
            preferences.putInteger(PREFS_SCORE, 0);
            preferences.flush();
        }

        g_Score = preferences.getInteger(PREFS_SCORE);

        //Music main = Gdx.audio.newMusic(Gdx.files.internal("data/mainmusic.wav"));
        /*if (mu == 0) {
            main.play();
            main.setLooping(true);
            mu++;
        }*/
    }

    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.push(new GameScreen(gsm));
        }
    }

    public void update(float dt) {
        handleInput();
    }

    public void render(SpriteBatch p_SprBat) {

        p_SprBat.setProjectionMatrix(cam.combined);
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
