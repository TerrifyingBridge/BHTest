package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private float x;
    private float y;
    private boolean enemy;
    private float velX;
    private float velY;

    private Texture texture;
    //private Rectangle hitbox;
    private Circle hitbox;

    public Bullet(float x, float y, boolean enemy) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        //hitbox = new Rectangle(x+1, y+1, 3, 3);
        hitbox = new Circle(x + 2.5f, y + 2.5f, 2);
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
        updateHitbox();
    }

    private void updateHitbox() {
        hitbox.x = x + 2.5f;
        hitbox.y = y + 2.5f;
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

    //public Rectangle getHitbox() { return hitbox; }

    public Circle getHitbox() { return hitbox; }
}
