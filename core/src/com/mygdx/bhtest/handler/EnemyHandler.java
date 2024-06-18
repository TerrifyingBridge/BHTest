package com.mygdx.bhtest.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.helper.ConstShot;
import com.mygdx.bhtest.objects.Enemy;
import com.mygdx.bhtest.objects.Player;

import java.util.ArrayList;

public class EnemyHandler {
    private final ArrayList<Enemy> enemyList;
    private final BulletHandler bulletHandler;
    private final Player player;

    public EnemyHandler(BulletHandler bulletHandler, Player player) {
        this.enemyList = new ArrayList<>();
        this.bulletHandler = bulletHandler;
        this.player = player;
    }

    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    private void checkHit(Enemy enemy) {
        for (int i = 0; i < bulletHandler.getBulletsP().size(); i++) {
            if (enemy.getHitbox().overlaps(bulletHandler.getBulletsP().get(i).getHitbox())) {
                enemy.setHealth(enemy.getHealth() - 1);
                bulletHandler.getBulletsP().remove(i);
                i--;
            }
        }
    }

    private void checkHitWithPlayer(Enemy enemy) {
        if (enemy.getHitbox().overlaps(player.getHitbox())) {
            player.setLives(player.getLives()-1);
            player.setAlive(false);
            enemy.setHealth(enemy.getHealth()-100);
        }
    }

    private void constShoot() {
        for (Enemy enemy: enemyList) {
            for (ConstShot shot: enemy.getConstShots()) {
                if (shot.getCurShotDelay() >= shot.getShotDelay()) {
                    bulletHandler.createEnemyShot(enemy.getX() + enemy.getLength()/2 - 2.5f + enemy.getVelX(), enemy.getY() + enemy.getLength()/2, shot.getBulVelX(), shot.getBulVelY());
                    shot.setCurShotDelay(0);
                }
                shot.setCurShotDelay(shot.getCurShotDelay() + 1);
            }
        }
    }

    public void updateEnemies() {
        constShoot();
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).updateEnemy();
            checkHit(enemyList.get(i));
            checkHitWithPlayer(enemyList.get(i));
            if (enemyList.get(i).getHealth() < 0) {
                player.setScore(player.getScore() + enemyList.get(i).getScore());
                enemyList.remove(i);
                i--;
            }
            else if (enemyList.get(i).getY() < -1f * BHGame.LEVEL_HEIGHT / 2 - enemyList.get(i).getLength()) {
                enemyList.remove(i);
                i--;
            }
        }
    }

    public void drawEnemies(SpriteBatch batch) {
        for (Enemy enemy: enemyList) {
            enemy.drawEnemy(batch);
        }
    }

    public void dispose() {
        for (Enemy enemy: enemyList) {
            enemy.dispose();
        }
    }

    public Enemy getEnemy(int i) {
        return enemyList.get(i);
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
