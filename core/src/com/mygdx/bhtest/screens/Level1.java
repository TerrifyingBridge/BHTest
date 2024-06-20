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
import com.mygdx.bhtest.helper.ConstShot;
import com.mygdx.bhtest.helper.Path;
import com.mygdx.bhtest.helper.Utility;
import com.mygdx.bhtest.objects.Enemy;
import com.mygdx.bhtest.objects.Player;

public class Level1 implements Screen {
    private final BHGame game;

    private final SpriteBatch batch;
    private final ShapeRenderer renderer;
    private final OrthographicCamera cam;

    private final Player player;
    private final BulletHandler bulletHandler;
    private final EnemyHandler enemyHandler;

    private int time;

    public Level1(BHGame game, OrthographicCamera cam) {
        this.game = game;

        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        this.cam = cam;
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);

        player = new Player(Utility.boxToStandardX(50) - 12.5f, Utility.boxToStandardY(10), 20f, 5, 5, 5);
        bulletHandler = new BulletHandler(player);
        enemyHandler = new EnemyHandler(bulletHandler, player);

        HUD.loadPlayer(player);

        time = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (player.canBomb()) {
            Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        } else {
            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //System.out.println(enemyHandler.getEnemy(0).getX());

        player.updatePlayer();
        bulletHandler.update();
        enemyHandler.updateEnemies();

        //Segments go here
        if (time == 100) {
            segment0();
        }

        batch.begin();

        bulletHandler.drawBullets(batch);
        player.renderPlayer(batch, time);
        enemyHandler.drawEnemies(batch);

        batch.end();
        HUD.drawHUD(renderer, batch);

        if (player.getLives() == 0) {
            game.setScreen(new GameOver());
        }

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

    public void segment1 () {
        for (int i = 0; i < 5; i++) {
            Enemy temp = new Enemy(Utility.boxToStandardX(-10 + i*10), Utility.boxToStandardY(110), 25, 0,0,50);
            temp.addConstPath(Utility.boxToStandardX(20*i + 10), Utility.boxToStandardY(90), 1 + i);
            temp.addWait(100 + i*5);
            enemyHandler.addEnemy(temp);
        }
    }

    public void segment0() {
        Enemy temp = new Enemy(Utility.boxToStandardX(-10 + 20), Utility.boxToStandardY(110), 25, 0,0,50);
        temp.addConstPath(Utility.boxToStandardX(20 + 10), Utility.boxToStandardY(90), 1);
        temp.addWait(60);
        Path path = new Path(temp, Utility.boxToStandardX(50), Utility.boxToStandardY(50), 1);
        path.setConstShot(new ConstShot(30, 0, 0, 2));
        temp.addPath(path);
        enemyHandler.addEnemy(temp);
    }
}
