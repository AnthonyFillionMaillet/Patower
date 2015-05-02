package com.npngstudio.patower.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.npngstudio.patower.StatesManager.GSM;
import com.npngstudio.patower.StatesManager.State;

import java.util.logging.Handler;

/**
 * Created by Anthony on 01/05/2015.
 */
public class SplashScreen extends State {

    private Texture background;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private float alpha;
    private boolean fadeIn = true;
    private boolean fadeOut = false;
    Music splash;

    public SplashScreen(GSM p_Gsm){
        super(p_Gsm);
        shapeRenderer = new ShapeRenderer();
        //font = new BitmapFont(Gdx.files.internal("data/text2.fnt"));
        alpha = 0.0f;
        splash = Gdx.audio.newMusic(Gdx.files.internal("data/splash1.ogg"));
        background = new Texture(Gdx.files.internal("data/test2.png"));
        splash.play();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                fadeOut();
            }
        }, 3);
    }

    public void handleInput() {
    }

    public void fadeOut(){
        fadeOut = true;
       /* splash.stop();
        gsm.push(new MenuScreen(gsm));*/
    }

    public void update(float dt) {
        handleInput();
    }

    public void changeScreen(){
        splash.dispose();
        splash.stop();
        gsm.push(new MenuScreen(gsm));
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, 0, 480, 800);
        shapeRenderer.end();

        if(alpha < 0.99f && fadeIn) alpha += 0.007f;
        else if (alpha >= 0.99f && fadeIn) {
            alpha = 1.0f;
            fadeIn = false;
        }

        if(fadeOut && alpha > 0.1f){
            alpha -= 0.01f;
        }
        else if(fadeOut && alpha <= 0.1f){
            alpha = 0f;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    changeScreen();
                }
            }, 1.5f);

        }


        sb.begin();
        /*font.draw(sb, "NPNG", 140, 580);
        font.draw(sb, "STUDIO", 85, 300);*/
        sb.setColor(1.0f, 1.0f, 1.0f, alpha);
        sb.draw(background, 0, 0, 480, 800);
        sb.end();
    }
}
