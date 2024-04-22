package com.mygdx.bhtest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.HUD;
import com.mygdx.bhtest.handler.BulletHandler;
import com.mygdx.bhtest.handler.EnemyHandler;
import com.mygdx.bhtest.objects.Enemy;
import com.mygdx.bhtest.objects.Player;

import java.util.ArrayList;

public class Level1 implements Screen {
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private OrthographicCamera cam;

    private Player player;
    private BulletHandler bulletHandler;
    private EnemyHandler enemyHandler;

    private int time;

    public Level1() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        cam = new OrthographicCamera(BHGame.LEVEL_WIDTH, BHGame.LEVEL_HEIGHT);
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);

        player = new Player(0f, 0f, 25f, 5, 5, 5);
        bulletHandler = new BulletHandler(player);
        enemyHandler = new EnemyHandler(bulletHandler);

        HUD.loadPlayer(player);

        time = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (time % 150 == 0) {
            enemyHandler.addEnemy(new Enemy(-240, 180, 25, 0, -1, 100));
        }

        player.updatePlayer();
        bulletHandler.update();
        enemyHandler.updateEnemies();

        batch.begin();

        bulletHandler.drawBullets(batch);
        player.renderPlayer(batch);
        enemyHandler.drawEnemies(batch);

        //HUD.drawHUD(renderer, batch);

        batch.end();
        HUD.drawHUD(renderer, batch);
        time++;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        player.dispose();
        bulletHandler.dispose();
        enemyHandler.dispose();
        HUD.dispose();
    }
}
