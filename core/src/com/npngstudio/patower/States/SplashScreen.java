package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

/**
 * Created by Anthony on 01/05/2015.
 */
public class SplashScreen extends State {

    Texture background;

    public SplashScreen(GSM p_Gsm){
        super(p_Gsm);
        background = new Texture(Gdx.files.internal("data/npng.png"));
    }

    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.push(new MenuScreen(gsm));
        }
    }

    public void update(float dt) {
        handleInput();
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, 480, 800);
        sb.end();
    }
}
