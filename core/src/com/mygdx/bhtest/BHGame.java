package com.mygdx.bhtest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.bhtest.handler.InputHandler;
import com.mygdx.bhtest.screens.Level1;

public class BHGame extends Game {
	public static final int LEVEL_WIDTH = 640;
	public static final int LEVEL_HEIGHT = 480;

	public OrthographicCamera camera;
	public AssetManager assetManager;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(LEVEL_WIDTH, LEVEL_HEIGHT);
		assetManager = new AssetManager();

		setScreen(new Level1(this, camera));
		Gdx.input.setInputProcessor(new InputHandler());
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
}
