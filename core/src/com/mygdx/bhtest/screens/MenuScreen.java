package com.mygdx.bhtest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.handler.ButtonHandler;
import com.mygdx.bhtest.handler.InputHandler;
import com.mygdx.bhtest.uistuff.MyButton;

import java.awt.*;
import java.util.ArrayList;

public class MenuScreen implements Screen {

    private BHGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private ButtonHandler buttonHandler;

    public MenuScreen(BHGame game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        this.batch = new SpriteBatch();

        ArrayList<MyButton> list = new ArrayList<>();
        list.add(new MyButton("start", 100, 100, 200, 50, new Level1(game, camera)));
        list.add(new MyButton("start", 100, 200, 200, 50));
        list.add(new MyButton("start", 100, 300, 200, 50));

        buttonHandler = new ButtonHandler(game, list);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0f, 1f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buttonHandler.updateButtons();

        batch.begin();

        buttonHandler.renderButtons(batch);

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
        buttonHandler.dispose();
        batch.dispose();
    }
}
