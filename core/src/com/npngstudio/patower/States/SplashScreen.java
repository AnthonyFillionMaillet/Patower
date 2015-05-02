package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

/**
 * Created by Anthony on 01/05/2015.
 */
public class SplashScreen extends State {

    private Texture background;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    public SplashScreen(GSM p_Gsm){
        super(p_Gsm);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("data/text2.fnt"));

        background = new Texture(Gdx.files.internal("data/npng.png"));
    }

    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.push(new MenuScreen(gsm));
        };
    }

    public void update(float dt) {
        handleInput();
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(0, 0, 480, 800);
        shapeRenderer.end();

        sb.begin();
        font.draw(sb, "NPNG", 140, 580);
        font.draw(sb, "STUDIO", 85, 300);
        //sb.draw(background, 0, 0, 480, 800);
        sb.end();
    }
}
