package com.mygdx.bhtest.uistuff;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.handler.InputHandler;
import com.mygdx.bhtest.screens.MenuScreen;

public class MyButton {

    private Texture selected;
    private Texture unselected;

    private float x;
    private float y;
    private float width;
    private float height;

    private boolean isSelected;
    private Screen screen;
    private boolean hasScreen;

    public MyButton(String filename, float x, float y, float width, float height){
        selected = new Texture(filename + "_selected.png");
        unselected = new Texture(filename + "_unselected.png");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        isSelected = false;
        hasScreen = false;
    }

    public MyButton(String filename, float x, float y, float width, float height, Screen screen){
        selected = new Texture(filename + "_selected.png");
        unselected = new Texture(filename + "_unselected.png");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        isSelected = false;
        this.screen = screen;
        hasScreen = true;
    }

    public void dispose() {
        selected.dispose();
        unselected.dispose();
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void renderButton(SpriteBatch batch) {
        if (isSelected) {
            batch.draw(selected, x, y);
        } else {
            batch.draw(unselected, x, y);
        }
    }

    public void action() {
        //Override me!
        return;
    }

    public Screen getScreen() {
        return screen;
    }

    public boolean hasScreen() {
        return hasScreen;
    }
}
