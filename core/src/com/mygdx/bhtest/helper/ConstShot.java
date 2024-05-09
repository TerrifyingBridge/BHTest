package com.mygdx.bhtest.helper;

public class ConstShot {
    private int shotDelay;
    private int curShotDelay;
    private float bulVelX;
    private float bulVelY;

    public ConstShot(int shotDelay, int curShotDelay, float velX, float velY){
        this.shotDelay = shotDelay;
        this.curShotDelay = curShotDelay;
        this.bulVelX = velX;
        this.bulVelY = velY;
    }

    public int getShotDelay() {
        return shotDelay;
    }
    public int getCurShotDelay() {
        return curShotDelay;
    }
    public float getBulVelX() {
        return bulVelX;
    }
    public float getBulVelY() {
        return bulVelY;
    }
    public void setCurShotDelay(int curDelay) {
        this.curShotDelay = curDelay;
    }
}
