package com.mygdx.bhtest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.handler.InputHandler;

public class OptionsScreen implements Screen {

    private BHGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Screen previousScreen;

    public OptionsScreen(BHGame game, OrthographicCamera camera, SpriteBatch batch, Screen previousScreen) {
        this.game = game;
        this.camera = camera;
        this.batch = batch;
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (InputHandler.X) {
            game.setScreen(previousScreen);
        }

        batch.begin();

        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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
    }
}
