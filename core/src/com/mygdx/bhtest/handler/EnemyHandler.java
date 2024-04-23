package com.mygdx.bhtest.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bhtest.BHGame;
import com.mygdx.bhtest.objects.Enemy;
import com.mygdx.bhtest.objects.Player;

import java.util.ArrayList;

public class EnemyHandler {
    private ArrayList<Enemy> enemyList;
    private BulletHandler bulletHandler;
    private Player player;

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
            if (bulletHandler.getBulletsP().get(i).getX() >= enemy.getX() && bulletHandler.getBulletsP().get(i).getX() < enemy.getX() + enemy.getLength() && bulletHandler.getBulletsP().get(i).getY() >= enemy.getY() && bulletHandler.getBulletsP().get(i).getY() < enemy.getY() + enemy.getLength()) {
                enemy.setHealth(enemy.getHealth() - 1);
                bulletHandler.getBulletsP().remove(i);
                i--;
            }
        }
    }

    private void checkHitWithPlayer(Enemy enemy) {
        boolean b = !(enemy.getX() < player.getX() + player.getLength() &&
                enemy.getX() + enemy.getLength() > player.getX() &&
                enemy.getY() + enemy.getLength() > player.getY() &&
                enemy.getY() < player.getY() + player.getLength());
        if (b) {
            player.setLives(player.getLives()-1);
            enemy.setHealth(enemy.getHealth()-100);
        }
    }

    public void updateEnemies() {
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).updateEnemy();
            checkHit(enemyList.get(i));
            //checkHitWithPlayer(enemyList.get(i));
            if (enemyList.get(i).getHealth() < 0) {
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
