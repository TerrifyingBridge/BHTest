package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.bhtest.helper.ConstShot;
import com.mygdx.bhtest.helper.MathFunctions;
import com.mygdx.bhtest.helper.Path;

import java.util.ArrayList;

public class Enemy {
    private float x;
    private float y;
    private float length;
    private float velY;
    private float velX;
    private int health;

    private ArrayList<ConstShot> constShots;
    private ArrayList<Path> paths;

    private Texture texture;
    //private Rectangle hitbox;
    private Circle hitbox;

    public Enemy(float x, float y, float length, float velX, float velY, int health){
        this.x = x;
        this.y = y;
        this.length = length;
        this.velX = velX;
        this.velY = velY;
        this.health = health;

        this.constShots = new ArrayList<>();
        this.paths = new ArrayList<>();

        //hitbox = new Rectangle(x + 12.5f - length/2, y + 12.5f - length/2, length, length);
        hitbox = new Circle(x + 12.5f, y + 12.5f, length/2);
        texture = new Texture("blue.png");
    }

    public void addConstShot(int shotDelay, float bulVelX, float bulVelY) {
        constShots.add(new ConstShot(shotDelay, 0, bulVelX, bulVelY));
    }

    public void addConstShot(int shotDelay, int curShotDelay, float bulVelX, float bulVelY) {
        constShots.add(new ConstShot(shotDelay, curShotDelay, bulVelX, bulVelY));
    }

    public void addConstShot(ConstShot shot) {
        constShots.add(shot);
    }

    public void addPath(Path path) {
        paths.add(path);
    }

    public void addConstPath(float x, float y, float speed){
        paths.add(new Path(this, x, y, speed));
    }

    public void addAccelPath(float x, float y, float speed, float maxSpeed, float accel){
        paths.add(new Path(this, x, y, speed, maxSpeed, accel));
    }

    public void addFunctionPath(MathFunctions function, int time) {
        paths.add(new Path(this, function, time));
    }

    public void addWait(int time) {
        MathFunctions waitFunc = new MathFunctions(new ArrayList<>(), new ArrayList<>());
        paths.add(new Path(this, waitFunc, time));
    }

    public void addPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public void updateEnemy() {
        if (paths.isEmpty()) {
            x += velX;
            y += velY;
        } else {
            paths.get(0).update();
            if (paths.get(0).isDone()) {
                paths.remove(0);
                if (!paths.isEmpty()) {
                    paths.get(0).refresh();
                    if (paths.get(0).hasShot()) {
                        addConstShot(paths.get(0).getConstShot());
                    }
                }
            }
        }
        updateHitbox();
    }

    private void updateHitbox() {
        hitbox.x = x + 12.5f;
        hitbox.y = y + 12.5f;
    }

    public void clearConstShot() {
        constShots = new ArrayList<>();
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

    //public Rectangle getHitbox() { return hitbox; }

    public Circle getHitbox() { return hitbox; }

    public ArrayList<ConstShot> getConstShots() {
        return constShots;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public Path getPath(int i) {
        return paths.get(i);
    }
}
