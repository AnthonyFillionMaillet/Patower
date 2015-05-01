package com.npngstudio.patower.Model;

/**
 * Created by Anthony on 01/05/2015.
 */
public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int p_x, int p_y, int p_width, int p_height){
        this.x = p_x;
        this.y = p_y;
        this.width = p_width;
        this.height = p_height;
    }

    public void update(){

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

}
