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
import com.mygdx.bhtest.objects.Enemy;
import com.mygdx.bhtest.objects.Player;

public class Level1 implements Screen {
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private OrthographicCamera cam;

    private Player player;
    private BulletHandler bulletHandler;
    private Enemy enemy;

    public Level1() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        cam = new OrthographicCamera(BHGame.LEVEL_WIDTH, BHGame.LEVEL_HEIGHT);
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);

        player = new Player(0f, 0f, 25f, 5, 5, 5);
        bulletHandler = new BulletHandler(player);

        enemy = new Enemy(-20, -20, 25, 0, 100);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.updatePlayer();
        bulletHandler.update();
        enemy.updateEnemy();

        batch.begin();
        bulletHandler.drawBullets(batch);
        player.renderPlayer(batch);
        enemy.drawEnemy(batch);
        batch.end();

        HUD.drawHUD(renderer, batch);
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
        enemy.dispose();
    }
}
