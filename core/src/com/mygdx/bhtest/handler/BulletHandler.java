package com.mygdx.bhtest.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public void update() {
        spawnBullet();

        for (int i = 0; i < bulletsP.size(); i++) {
            bulletsP.get(i).updateBullet();
            if (bulletsP.get(i).getY() > HUD.BOX_HEIGHT) {
                bulletsP.remove(i);
                i--;
            }
        }
    }

    public void drawBullets(SpriteBatch batch) {
        for (Bullet bullet: bulletsP) {
            bullet.drawBullet(batch);
        }
    }

    public void dispose() {
        for (Bullet bullet: bulletsP) {
            bullet.dispose();
        }
    }

    public ArrayList<Bullet> getBulletsP() {
        return bulletsP;
    }
}
