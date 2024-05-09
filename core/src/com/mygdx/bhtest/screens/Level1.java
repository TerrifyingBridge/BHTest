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
import com.mygdx.bhtest.helper.MathFunctions;
import com.mygdx.bhtest.helper.Path;
import com.mygdx.bhtest.helper.Utility;
import com.mygdx.bhtest.objects.Enemy;
import com.mygdx.bhtest.objects.Player;
import sun.nio.ch.Util;

import java.util.ArrayList;

public class Level1 implements Screen {
    private BHGame game;

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private OrthographicCamera cam;

    private Player player;
    private BulletHandler bulletHandler;
    private EnemyHandler enemyHandler;

    private int time;

    public Level1(BHGame game, OrthographicCamera cam) {
        this.game = game;

        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        this.cam = cam;
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);

        player = new Player(Utility.boxToStandardX(50) - 12.5f, Utility.boxToStandardY(10), 12f, 5, 5, 5);
        bulletHandler = new BulletHandler(player);
        enemyHandler = new EnemyHandler(bulletHandler, player);

        /*
        ArrayList<Float> coef = new ArrayList<>();
        coef.add(5f);
        coef.add(-0.25f);
        MathFunctions f = new MathFunctions(coef, new ArrayList<Float>());
        MathFunctions g = new MathFunctions(new ArrayList<Float>(), coef);
        Enemy temp = new Enemy(Utility.boxToStandardX(50), Utility.boxToStandardY(70), 25, 0, 0, 100);
        temp.addConstPath(Utility.boxToStandardX(50), Utility.boxToStandardY(20), 2);
        temp.addAccelPath(Utility.boxToStandardX(10), Utility.boxToStandardY(20), 2, 10, 1);
        MathFunctions h = new MathFunctions();
        temp.addWait(100);
        //temp.addFunctionPath(h, 100);
        temp.addFunctionPath(f, 30);
        temp.addFunctionPath(g, 30);
        enemyHandler.addEnemy(temp);
         */

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
        //System.out.println(enemyHandler.getEnemy(0).getX());

        player.updatePlayer();
        bulletHandler.update();
        enemyHandler.updateEnemies();

        //Segments go here
        //segment1();

        batch.begin();

        bulletHandler.drawBullets(batch);
        player.renderPlayer(batch);
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

    public void segment1() {
        if (time > 5*30 && time % 60 == 0) {
            Enemy temp = new Enemy(-1*BHGame.LEVEL_WIDTH/2f, BHGame.LEVEL_HEIGHT/2f,25,2,-1,10);
            temp.addConstShot(30, 0, -2);
            enemyHandler.addEnemy(temp);
        }
    }

    public void segment0() {
        if (time > 5*30 && time % 60 == 0){

        }
    }
}
