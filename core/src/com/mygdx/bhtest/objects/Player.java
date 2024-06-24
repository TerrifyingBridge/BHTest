package com.mygdx.bhtest.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.HUD;
import com.mygdx.bhtest.handler.InputHandler;

public class Player extends GameObject{
    private final int velocity;
    private final Circle hitbox;

    private int lives;
    private int bombs;
    private int score;
    
    private int bombDelay;
    private boolean bomb;
    private int respawnDelay;
    private boolean alive;

    private final Texture texture1;
    private final Texture texture2;

    public Player(float x, float y, float length, int velocity, int lives, int bombs) {
        super(x, y, length);
        this.velocity = velocity;
        this.lives = lives;
        this.bombs = bombs;
        this.score = 0;

        float xCenter = x + 12.5f;
        float yCenter = y + 12.5f;

        this.hitbox = new Circle(xCenter, yCenter, length/2);

        bombDelay = 0;
        bomb = false;
        respawnDelay = 0;
        alive = true;

        texture1 = new Texture("player1.png");
        texture2 = new Texture("player2.png");
    }

    private void movePlayer() {
        float newX = 0;
        float newY = 0;
        if (InputHandler.LEFT && alive) {
            newX -= 1;
        } if(InputHandler.RIGHT && alive) {
            newX += 1;
        } if (InputHandler.UP && alive) {
            newY += 1;
        } if (InputHandler.DOWN && alive) {
            newY -= 1;
        }

        if (newX == 0 && newY == 0) {
            return;
        }

        newX /= (float) Math.sqrt(newX*newX + newY*newY);
        newY /= (float) Math.sqrt(newX*newX + newY*newY);
        if (InputHandler.SHIFT) {
            newX = x + newX*velocity/2;
            newY = y + newY*velocity/2;
        } else {
            newX = x + newX*velocity;
            newY = y + newY*velocity;
        }

        if (newX <= -1*BHGame.LEVEL_WIDTH/2f + 20) {
            newX = -1*BHGame.LEVEL_WIDTH/2f + 20;
        } if (newX >= -1*BHGame.LEVEL_WIDTH/2f + 20 + HUD.BOX_WIDTH - 25) {
            newX = -1*BHGame.LEVEL_WIDTH/2f + 20 + HUD.BOX_WIDTH - 25;
        }

        if (newY <= -1*HUD.BOX_HEIGHT/2) {
            newY = -1*HUD.BOX_HEIGHT/2;
        } if (newY >= -1*HUD.BOX_HEIGHT/2 + HUD.BOX_HEIGHT - 25) {
            newY = -1*HUD.BOX_HEIGHT/2 + HUD.BOX_HEIGHT - 25;
        }

        x = newX;
        y = newY;
    }

    public void updatePlayer() {
        movePlayer();
        updateHitbox();
        
        if (InputHandler.X && bombDelay == 0 && alive) {
            bombs--;
            bombDelay++;
            bomb = true;
        } else if (bombDelay > 0) {
            bombDelay++;
            if (bombDelay == 150) {
                bombDelay = 0;
            }
        } else {
            bomb = false;
        }

        if (!alive) {
            respawn();
        }
    }

    public void respawn() {
        if (respawnDelay >= 200) {
            alive = true;
            respawnDelay = 0;
        } else if (respawnDelay > 0){
            y += 1;
            respawnDelay++;
        } else {
            x = -1*BHGame.LEVEL_WIDTH/2f + 20 + HUD.BOX_WIDTH/2 - 12.5f;
            y = -1*BHGame.LEVEL_HEIGHT/2f - 100;
            respawnDelay++;
        }
    }

    private void updateHitbox() {
        hitbox.x = x + 12.5f;
        hitbox.y = y + 12.5f;
    }

    public void renderPlayer(SpriteBatch batch, int frame) {
        if (frame % 30 < 15) {
            batch.draw(texture1, x, y);
        } else {
            batch.draw(texture2, x ,y);
        }
    }

    public void dispose() {
        texture1.dispose();
        texture2.dispose();
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

    public boolean canBomb() {
        return bomb;
    }

    public int getBombs() {
        return bombs;
    }

    public Circle getHitbox() { return hitbox; }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
