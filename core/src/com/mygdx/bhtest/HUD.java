package com.mygdx.bhtest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class HUD {
    public static final float BOX_WIDTH = BHGame.LEVEL_WIDTH / 1.75f;
    public static final float BOX_HEIGHT = BHGame.LEVEL_HEIGHT-24;

    private static BitmapFont font = new BitmapFont();
    public static void drawHUD(ShapeRenderer renderer, SpriteBatch batch) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background
        renderer.setColor(Color.FIREBRICK);
        renderer.rect(-1*BHGame.LEVEL_WIDTH/2f, -1*BHGame.LEVEL_HEIGHT/2f,BHGame.LEVEL_WIDTH, 12f);
        renderer.rect(-1*BHGame.LEVEL_WIDTH/2f, BHGame.LEVEL_HEIGHT/2f-12,BHGame.LEVEL_WIDTH, 12f);
        renderer.rect(-1*BHGame.LEVEL_WIDTH/2f, -1*BHGame.LEVEL_HEIGHT/2f,20f, BHGame.LEVEL_HEIGHT);
        renderer.rect(-1*BHGame.LEVEL_WIDTH/2f + BOX_WIDTH + 20, -1*BHGame.LEVEL_HEIGHT/2f,BHGame.LEVEL_WIDTH - BOX_WIDTH - 20, BHGame.LEVEL_HEIGHT);
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        //Border
        renderer.setColor(Color.WHITE);
        renderer.rect(-1*BHGame.LEVEL_WIDTH/2f + 20,-1*BOX_HEIGHT/2,BOX_WIDTH,BOX_HEIGHT);
        renderer.end();

        batch.begin();
        drawText(batch);
        batch.end();
    }

    private static void drawText(SpriteBatch batch) {
        font.draw(batch, "Score", 200, 200);
    }

    public static void dispose() {
        font.dispose();
    }
}
