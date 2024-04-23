package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.HUD;
import com.mygdx.bhtest.handler.InputHandler;

public class Player {
    private float x;
    private float y;
    private float length;
    private int velocity;
    private float curVelocity;

    private int lives;
    private int bombs;
    private int score;
    
    private int bombDelay;
    private int respawnDelay;

    private Texture texture;

    public Player(float x, float y, float length, int velocity, int lives, int bombs) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.velocity = velocity;
        this.lives = lives;
        this.bombs = bombs;
        this.curVelocity = 0;
        this.score = 0;
        
        bombDelay = 0;
        respawnDelay = 0;

        texture = new Texture("yellow.png");
    }

    private void movePlayer() {
        float newX = 0;
        float newY = 0;
        if (InputHandler.LEFT) {
            newX -= 1;
        } if(InputHandler.RIGHT) {
            newX += 1;
        } if (InputHandler.UP) {
            newY += 1;
        } if (InputHandler.DOWN) {
            newY -= 1;
        }

        if (newX == 0 && newY == 0) {
            return;
        }

        newX /= (float) Math.sqrt(newX*newX + newY*newY);
        newY /= (float) Math.sqrt(newX*newX + newY*newY);
        curVelocity = velocity*(float)Math.sqrt(newX*newX + newY*newY);
        if (InputHandler.SHIFT) {
            newX = x + newX*velocity/2;
            newY = y + newY*velocity/2;
        } else {
            newX = x + newX*velocity;
            newY = y + newY*velocity;
        }

        if (newX <= -1*BHGame.LEVEL_WIDTH/2f + 20) {
            newX = -1*BHGame.LEVEL_WIDTH/2f + 20;
        } if (newX >= -1*BHGame.LEVEL_WIDTH/2f + 20 + HUD.BOX_WIDTH - length) {
            newX = -1*BHGame.LEVEL_WIDTH/2f + 20 + HUD.BOX_WIDTH - length;
        }

        if (newY <= -1*HUD.BOX_HEIGHT/2) {
            newY = -1*HUD.BOX_HEIGHT/2;
        } if (newY >= -1*HUD.BOX_HEIGHT/2 + HUD.BOX_HEIGHT - length) {
            newY = -1*HUD.BOX_HEIGHT/2 + HUD.BOX_HEIGHT - length;
        }

        x = newX;
        y = newY;
    }

    public void updatePlayer() {
        movePlayer();
        
        if (InputHandler.X && bombDelay == 0) {
            bombs--;
            bombDelay++;
        } else if (bombDelay > 0) {
            bombDelay++;
            if (bombDelay == 300) {
                bombDelay = 0;
            }
        }
    }

    public void renderPlayer(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getLength() {
        return length;
    }

    public float getCurVelocity() {
        return curVelocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public int getBombs() {
        return bombs;
    }
}
