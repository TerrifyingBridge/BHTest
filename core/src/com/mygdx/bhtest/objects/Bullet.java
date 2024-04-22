package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    private float x;
    private float y;
    private boolean enemy;
    private float velX;
    private float velY;

    private Texture texture;

    public Bullet(float x, float y, boolean enemy) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        if (!enemy) {
            this.velY = 8;
            this.velX = 0;
            texture = new Texture("red.png");
        } else {
            this.velX = 0;
            this.velY = 0;
            texture = new Texture("green.png");
        }

    }

    public void updateBullet() {
        x += velX;
        y += velY;
    }

    public void drawBullet(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void setVelX(float vel) {
        this.velX = vel;
    }

    public void setVelY(float vel) {
        this.velY = vel;
    }
}
