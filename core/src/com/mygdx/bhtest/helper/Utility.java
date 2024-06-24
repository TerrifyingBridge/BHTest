package com.mygdx.bhtest.helper;

import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.HUD;
import com.mygdx.bhtest.objects.Enemy;

public class Utility {
    // 0 <= x <= 100
    public static float boxToStandardX(float x) {
        return (-1*BHGame.LEVEL_WIDTH/2f + 20 + (x/100) * HUD.BOX_WIDTH);
    }

    // 0 <= y <= 100
    public static float boxToStandardY(float y) {
        return (-1*BHGame.LEVEL_HEIGHT/2f + 12 + (y/100) * HUD.BOX_HEIGHT);
    }

    public static Path nullPath(Enemy enemy, int time) {
        MathFunctions waitFunc = new MathFunctions();
        return new Path(enemy, waitFunc, time);
    }
}
