package com.mygdx.bhtest.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.HUD;
import com.mygdx.bhtest.objects.Bullet;
import com.mygdx.bhtest.objects.Player;

import java.util.ArrayList;

public class BulletHandler {
    private final Player player;

    private final ArrayList<Bullet> bulletsP;
    private final ArrayList<Bullet> bulletsE;
    private final ArrayList<Bullet> bulletQueue;

    private int SHOT_DELAY;

    private int delay;
    private int startTime;

    public BulletHandler(Player player) {
        this.player = player;

        bulletsP = new ArrayList<>();
        bulletsE = new ArrayList<>();
        bulletQueue = new ArrayList<>();

        delay = 0;
        SHOT_DELAY = 5;
        startTime = 0;
    }

    private void spawnBullet() {
        if (InputHandler.Z && startTime >= 10 && bulletQueue.isEmpty() && player.getAlive()) {
            bulletQueue.add(new Bullet(player.getX() + 10f, player.getY() + player.getLength()/2f, false));
            bulletQueue.add(new Bullet(player.getX() + 10f, player.getY() + player.getLength()/2f, false));
            bulletQueue.add(new Bullet(player.getX() + 10f, player.getY() + player.getLength()/2f, false));
        }

        if (delay >= SHOT_DELAY && !bulletQueue.isEmpty()){
            Bullet temp = bulletQueue.remove(0);
            if (temp != null) {
                bulletsP.add(temp);
            }
            delay = 0;
        }

        for (Bullet bullet: bulletQueue) {
            bullet.setX(player.getX() + 10f);
            bullet.setY(player.getY() + player.getLength() / 2f);
        }

        if (startTime < 10) {
            startTime++;
        }
    }

    public void createEnemyShot(float x, float y, float velX, float velY) {
        Bullet temp = new Bullet(x, y, true);
        temp.setVelX(velX);
        temp.setVelY(velY);
        bulletsE.add(temp);
    }

    public void update() {
        delay++;
        SHOT_DELAY = 5; // + (int) player.getCurVelocity();

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
        if (bullet.getHitbox().overlaps(player.getHitbox()) && player.getAlive()){
            player.setLives(player.getLives()-1);
            player.setAlive(false);
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
