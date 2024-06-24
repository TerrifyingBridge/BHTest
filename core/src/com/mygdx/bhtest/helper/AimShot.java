package com.mygdx.bhtest.helper;

import com.mygdx.bhtest.objects.GameObject;

public class AimShot extends ConstShot{

    private final GameObject reference;
    private final float offset;

    public AimShot(GameObject reference, int shotDelay, int curShotDelay, float velX, float velY, float offset) {
        super(shotDelay, curShotDelay, velX, velY);
        this.reference = reference;
        this.offset = offset;
    }
}
