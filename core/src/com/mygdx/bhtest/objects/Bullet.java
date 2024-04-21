package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    private float x;
    private float y;
    private float vel;

    private Texture texture;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        vel = 8;
        texture = new Texture("red.png");
    }

    public void updateBullet() {
        y += vel;
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

    public void setVel(float vel) {
        this.vel = vel;
    }
}
