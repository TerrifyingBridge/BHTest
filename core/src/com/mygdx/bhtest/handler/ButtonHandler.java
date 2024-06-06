package com.mygdx.bhtest.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.uistuff.MyButton;

import java.util.ArrayList;

public class ButtonHandler {

    private BHGame game;

    private ArrayList<MyButton> buttonList;
    private int spot;
    private int count;

    public ButtonHandler(BHGame game, ArrayList<MyButton> buttonList) {
        this.game = game;

        this.buttonList = buttonList;
        spot = 0;
        count = 0;
    }

    public void updateButtons() {
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == spot) {
                buttonList.get(i).setSelected(true);
            } else {
                buttonList.get(i).setSelected(false);
            }
        }

        if (InputHandler.UP && count > 10) {
            spot -= 1;
            if (spot < 0) {
                spot += buttonList.size();
            }
            count = 0;
        } else if (InputHandler.DOWN && count > 10) {
            spot = (spot + 1) % buttonList.size();
            count = 0;
        }
        count++;

        if (InputHandler.Z && buttonList.get(spot).hasScreen()) {
            game.setScreen(buttonList.get(spot).getScreen());
        } else if (InputHandler.Z) {
            buttonList.get(spot).action();
        }
    }

    public void renderButtons(SpriteBatch batch) {
        for (MyButton button: buttonList) {
            button.renderButton(batch);
        }
    }

    public void dispose() {
        for (MyButton myButton : buttonList) {
            myButton.dispose();
        }
    }
}
