package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    private float x;
    private float y;
    private float length;
    private float velY;
    private float velX;
    private int health;

    private Texture texture;

    public Enemy(float x, float y, float length, float velX, float velY, int health){
        this.x = x;
        this.y = y;
        this.length = length;
        this.velX = velX;
        this.velY = velY;
        this.health = health;

        texture = new Texture("blue.png");
    }

    public void updateEnemy() {
        x += velX;
        y += velY;
    }

    public void drawEnemy(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getVelY() {
        return velY;
    }
}
