package com.mygdx.bhtest.handler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
    public static boolean LEFT;
    public static boolean RIGHT;
    public static boolean UP;
    public static boolean DOWN;
    public static boolean Z;
    public static boolean X;
    public static boolean SHIFT;

    @Override
    public boolean keyDown(int keycode) {
        boolean processed = false;
        if (keycode == Input.Keys.LEFT) {
            LEFT = true;
            processed = true;
        } if (keycode == Input.Keys.RIGHT) {
            RIGHT = true;
            processed = true;
        } if (keycode == Input.Keys.UP) {
            UP = true;
            processed = true;
        } if (keycode == Input.Keys.DOWN) {
            DOWN = true;
            processed = true;
        } if (keycode == Input.Keys.Z){
            Z = true;
            processed = true;
        } if (keycode == Input.Keys.X) {
            X = true;
            processed = true;
        } if (keycode == Input.Keys.SHIFT_LEFT) {
            SHIFT = true;
            processed = true;
        }
        return processed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean processed = false;
        if (keycode == Input.Keys.LEFT) {
            LEFT = false;
            processed = true;
        } if (keycode == Input.Keys.RIGHT) {
            RIGHT = false;
            processed = true;
        } if (keycode == Input.Keys.UP) {
            UP = false;
            processed = true;
        } if (keycode == Input.Keys.DOWN) {
            DOWN = false;
            processed = true;
        } if (keycode == Input.Keys.Z) {
            Z = false;
            processed = true;
        } if (keycode == Input.Keys.X) {
            X = false;
            processed = true;
        } if (keycode == Input.Keys.SHIFT_LEFT) {
            SHIFT = false;
            processed = true;
        }
        return processed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
