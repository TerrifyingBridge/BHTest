package com.mygdx.bhtest.objects;

public class GameObject {

    protected float x;
    protected float y;
    protected float length;

    public GameObject(float x, float y, float length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
        length = 25f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getLength() {
        return length;
    }
}
