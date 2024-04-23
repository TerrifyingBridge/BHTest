package com.mygdx.bhtest.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.HUD;
import com.mygdx.bhtest.objects.Bullet;
import com.mygdx.bhtest.objects.Player;

import java.util.ArrayList;

public class BulletHandler {
    private Player player;

    private ArrayList<Bullet> bulletsP;
    private ArrayList<Bullet> bulletsE;
    private int delay;

    public BulletHandler(Player player) {
        this.player = player;

        bulletsP = new ArrayList<>();
        bulletsE = new ArrayList<>();
        delay = 0;
    }

    private void spawnBullet() {
        delay++;
        if (delay < 0) {
            delay = 0;
        }
        if (InputHandler.Z && delay % 2 == 0) {
            bulletsP.add(new Bullet(player.getX() + player.getLength()/2 - 2.5f, player.getY(), false));
            delay = 0;
        }
    }

    public void createEnemyShot(float x, float y, float velX, float velY) {
        Bullet temp = new Bullet(x, y, true);
        temp.setVelX(velX);
        temp.setVelY(velY);
        bulletsE.add(temp);
    }

    public void update() {
        spawnBullet();

        for (int i = 0; i < bulletsP.size(); i++) {
            bulletsP.get(i).updateBullet();
            if (bulletsP.get(i).getY() > HUD.BOX_HEIGHT) {
                bulletsP.remove(i);
                i--;
            }
        }

        for (int i = 0; i < bulletsE.size(); i++) {
            bulletsE.get(i).updateBullet();
            if (bulletsE.get(i).getX() < -1*BHGame.LEVEL_WIDTH/2f || bulletsE.get(i).getX() > BHGame.LEVEL_WIDTH/2f || bulletsE.get(i).getY() < -1*BHGame.LEVEL_HEIGHT/2f) {
                bulletsE.remove(i);
                i--;
            } else if (hitPlayer(bulletsE.get(i))) {
                bulletsE.remove(i);
                i--;
            }
        }
    }

    private boolean hitPlayer(Bullet bullet) {
            if (bullet.getX() > player.getX() && bullet.getX() < player.getX() + player.getLength() && bullet.getY() > player.getY() && bullet.getY() < player.getY() + player.getLength()) {
                player.setLives(player.getLives()-1);
                return true;
            } else {
                return false;
            }
    }

    public void drawBullets(SpriteBatch batch) {
        for (Bullet bullet: bulletsP) {
            bullet.drawBullet(batch);
        }
        for (Bullet bullet: bulletsE) {
            bullet.drawBullet(batch);
        }
    }

    public void dispose() {
        for (Bullet bullet: bulletsP) {
            bullet.dispose();
        }
        for (Bullet bullet: bulletsE) {
            bullet.dispose();
        }
    }

    public ArrayList<Bullet> getBulletsP() {
        return bulletsP;
    }
}
