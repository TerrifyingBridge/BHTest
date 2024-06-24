package com.mygdx.bhtest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.bhtest.handler.InputHandler;
import com.mygdx.bhtest.screens.MenuScreen;

public class BHGame extends Game {
	public static final int LEVEL_WIDTH = 640;
	public static final int LEVEL_HEIGHT = 480;

	public OrthographicCamera camera;
	public AssetManager assetManager;

	public MenuScreen menuScreen;

	private int difficulty;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(LEVEL_WIDTH, LEVEL_HEIGHT);
		assetManager = new AssetManager();

		menuScreen = new MenuScreen(this, camera);
		setScreen(menuScreen);

		//setScreen(new Level1(this, camera));
		Gdx.input.setInputProcessor(new InputHandler());

		difficulty = 1;
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		assetManager.dispose();
	}

	public int getDifficulty() {
		return difficulty;
	}
}
