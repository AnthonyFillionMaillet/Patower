package com.npngstudio.patower.Model;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Anthony on 01/05/2015.
 */
public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private boolean dansLeVide;

    public Rectangle(int p_x, int p_y, int p_width, int p_height, Color p_color, boolean p_dansLeVide){
        this.x = p_x;
        this.y = p_y;
        this.width = p_width;
        this.height = p_height;
        this.color = p_color;
        this.dansLeVide = p_dansLeVide;
    }

    public void update(){

        if(dansLeVide) {
            if (y > 0) {
                y--;
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor(){
        return color;
    }

    public boolean isDansLeVide(){
        return dansLeVide;
    }

    public void setX(int p_x){
        this.x = p_x;
    }

    public void setY(int p_y){
        this.y = p_y;
    }

    public void setDansLeVide(boolean p_dansLeVide) {
        this.dansLeVide = p_dansLeVide;
    }


}
