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

    public Rectangle(int p_x, int p_y, int p_width, int p_height, Color p_color){
        this.x = p_x;
        this.y = p_y;
        this.width = p_width;
        this.height = p_height;
        this.color = p_color;
    }

    public void update(ArrayList<Rectangle> p_ArrRec){
        if(p_ArrRec.get(p_ArrRec.size()-1).y > p_ArrRec.get(p_ArrRec.size()-2).y)
        {
            this.y--;
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

}
