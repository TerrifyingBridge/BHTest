package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Bullet extends GameObject{
    private float velX;
    private float velY;

    private final Texture texture;
    private final Circle hitbox;

    public Bullet(float x, float y, boolean enemy) {
        super(x, y, 5f);
        hitbox = new Circle(x + 2.5f, y + 2.5f, 2);

        if (!enemy) {
            this.velY = 16;
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVelX(float vel) {
        this.velX = vel;
    }

    public void setVelY(float vel) {
        this.velY = vel;
    }

    public Circle getHitbox() { return hitbox; }
}
