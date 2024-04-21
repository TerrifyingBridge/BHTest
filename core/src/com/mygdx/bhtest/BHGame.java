package com.mygdx.bhtest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.bhtest.handler.InputHandler;
import com.mygdx.bhtest.screens.Level1;

public class BHGame extends Game {
	public static final int LEVEL_WIDTH = 640;
	public static final int LEVEL_HEIGHT = 480;
	
	@Override
	public void create () {
		setScreen(new Level1());
		Gdx.input.setInputProcessor(new InputHandler());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
