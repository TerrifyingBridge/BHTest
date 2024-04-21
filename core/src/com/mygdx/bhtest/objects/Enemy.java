package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    private float x;
    private float y;
    private float length;
    private float velocity;
    private int health;

    private Texture texture;

    public Enemy(float x, float y, float length, float velocity, int health){
        this.x = x;
        this.y = y;
        this.length = length;
        this.velocity = velocity;
        this.health = health;

        texture = new Texture("blue.png");
    }

    public void updateEnemy() {
        x += velocity;
        y += velocity;
    }

    public void drawEnemy(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
