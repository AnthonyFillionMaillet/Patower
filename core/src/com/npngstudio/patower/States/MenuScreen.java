package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.npngstudio.patower.Model.Rectangle;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

import java.util.ArrayList;

/**
 * Created by Anthony on 01/05/2015.
 */
public class MenuScreen extends State {

    public MenuScreen(GSM p_Gsm){
        super(p_Gsm);
    }

    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.push(new GameScreen(gsm));
        }
    }

    public void update(float dt) {
        handleInput();
    }

    public void render(SpriteBatch sb) {

    }
}
