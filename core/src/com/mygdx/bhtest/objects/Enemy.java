package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.mygdx.bhtest.helper.ConstShot;
import com.mygdx.bhtest.helper.MathFunctions;
import com.mygdx.bhtest.helper.Path;

import java.util.ArrayList;

public class Enemy extends GameObject{

    public enum EnemyType{
        basicSpider, bigSpider
    }

    private float velY;
    private float velX;
    private int health;
    private final int score;

    private ArrayList<ConstShot> constShots;
    private ArrayList<Path> paths;

    private Texture texture1;
    private Texture texture2;
    private final EnemyType enemyType;
    private Circle hitbox;

    public Enemy(float x, float y, float velX, float velY, int health, EnemyType enemyType){
        //Default chosen for length, as it changes based on
        super(x, y);
        this.velX = velX;
        this.velY = velY;
        this.health = health;
        this.score = health;

        this.constShots = new ArrayList<>();
        this.paths = new ArrayList<>();

        this.enemyType = enemyType;

        switch (this.enemyType) {
            case basicSpider:
                length = 25f;
                hitbox = new Circle(x + 12.5f, y + 12.5f, length/2);
                texture1 = new Texture("1enemy1.png");
                texture2 = new Texture("1enemy2.png");
            case bigSpider:
                //Add later
        }
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
        MathFunctions waitFunc = new MathFunctions();
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
        switch (enemyType) {
            case basicSpider:
                hitbox.x = x + length/2;
                hitbox.y = y + length/2;
            case bigSpider:
                //Add later
        }

    }

    public void clearConstShot() {
        constShots = new ArrayList<>();
    }

    public void drawEnemy(SpriteBatch batch, int time) {
        if (time % 30 < 15) {
            batch.draw(texture1, x, y);
        } else {
            batch.draw(texture2, x, y);
        }
    }

    public void dispose() {
        texture1.dispose();
        texture2.dispose();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
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

    public int getScore() {
        return score;
    }
}
