package com.mygdx.bhtest.helper;

import java.util.ArrayList;

public class MathFunctions {
    public static final int CARTESIAN= 0;
    public static final int POLAR = 1;

    private int type;

    private ArrayList<Float> coefficientsX;
    private ArrayList<Float> coefficientsY;

    public MathFunctions(ArrayList<Float> coefficientsX, ArrayList<Float> coefficientsY) {
        this.type = CARTESIAN;

        this.coefficientsX = coefficientsX;
        this.coefficientsY = coefficientsY;
    }

    public MathFunctions() {
        this.coefficientsX = new ArrayList<>();
        this.coefficientsY = new ArrayList<>();
    }

    public float findVelX(int time) {
        float velX = 0;
        switch(type) {
            case CARTESIAN:
                velX = findVel(coefficientsX, time);
                break;
            case POLAR:
                break;
        }
        return velX;
    }

    public float findVelY(int time) {
        float velY = 0;
        switch(type) {
            case CARTESIAN:
                velY = findVel(coefficientsY, time);
                break;
            case POLAR:
                break;
        }
        return velY;
    }

    private float findVel(ArrayList<Float> coefficients, int time) {
        float vel = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            vel += (float) (coefficients.get(i) * Math.pow(time, i));
        }
        return vel;
    }

    public boolean isEmpty() {
        return (coefficientsX.isEmpty() && coefficientsY.isEmpty());
    }
}
