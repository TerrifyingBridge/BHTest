package com.mygdx.bhtest.helper;

import com.mygdx.bhtest.objects.Enemy;

public class Path {
     public static final int GOTO = 0;
     public static final int TIME = 1;

     private float toX;
     private float toY;
     private Enemy enemy;
     private int type;

     private float curX;
     private float curY;
     private float curVel;
     private float angle;

     private float accel;
     private float maxSpeed;
     private float minSpeed;

     private int time;
     private int curTime;
     private MathFunctions function;

     public Path(Enemy enemy, float x, float y, float velocity) {
         this.enemy = enemy;
         this.toX = x;
         this.toY = y;
         this.curTime = 0;

         this.curVel = velocity;
         refreshGOTO();

         this.accel = 0;
         this.maxSpeed = 1000;
         this.minSpeed = -1000;

         this.type = GOTO;
     }

     public Path(Enemy enemy, float x, float y, float velocity, float maxSpeed, float accel){
          this.enemy = enemy;
          this.toX = x;
          this.toY = y;
          this.curTime = 0;

          this.curVel = velocity;
          this.maxSpeed = maxSpeed;
          this.minSpeed = 0;
          this.accel = accel;
          refreshGOTO();

          this.type = GOTO;
     }

     public Path(Enemy enemy, MathFunctions function, int time) {
          this.enemy = enemy;
          this.function = function;
          this.time = time;
          this.curTime = 0;

          refreshTIME();
          this.type = TIME;
     }

     public void update() {
          if (type == TIME) {
               if (function.isEmpty()) {
                    angle = 0;
               }
          }
          curX += (float) (curVel*Math.cos(angle));
          curY += (float) (curVel*Math.sin(angle));
          switch(type) {
               case GOTO:
                    if (curVel < maxSpeed && curVel > minSpeed) {
                         curVel += accel;
                    } else if (curVel >= maxSpeed) {
                         curVel = maxSpeed;
                    } else {
                         curVel = minSpeed;
                    }
                    break;
               case TIME:
                    float velX = function.findVelX(curTime);
                    float velY = function.findVelY(curTime);
                    if (velX == 0 && velY == 0) {
                         velX = function.findVelX(curTime-1);
                         velY = function.findVelY(curTime-1);
                    }

                    curVel = (float) Math.sqrt(velX*velX + velY*velY);
                    if (velX >= 0) {
                         angle = (float) Math.atan(velY/velX);
                    } else {
                         angle = (float) (Math.atan(velY/velX) + Math.PI);
                    }

                    break;
          }
          enemy.setX(curX);
          enemy.setY(curY);
          curTime++;
     }

     private void refreshGOTO() {
          curX = enemy.getX();
          curY = enemy.getY();

          float diffX = toX - curX;
          float diffY = toY - curY;
          if (diffX >= 0) {
               angle = (float) Math.atan(diffY/diffX);
          } else {
               angle = (float) (Math.atan(diffY/diffX) + Math.PI);
          }
     }

     private void refreshTIME() {
          curX = enemy.getX();
          curY = enemy.getY();

          float velX = function.findVelX(0);
          float velY = function.findVelY(0);

          this.curVel = (float) Math.sqrt(velX*velX + velY*velY);
          if (velX >= 0) {
               angle = (float) Math.atan(velY/velX);
          } else {
               angle = (float) (Math.atan(velY/velX) + Math.PI);
          }
     }

     public void refresh() {
          switch(type) {
               case GOTO:
                    refreshGOTO();
                    break;
               case TIME:
                    refreshTIME();
                    break;
          }
     }

     public boolean isDone() {
          switch (type) {
               case GOTO:
                    return (Math.abs(curX - toX) < curVel/2 && Math.abs(curY - toY) < curVel/2);
               case TIME:
                    return (curTime >= time);
          }
          return true;
     }

     public int getTime() {
         return time;
     }
     public int getCurTime() {
         return curTime;
     }
     public float getToX() {
         return toX;
     }
     public float getToY() {
         return toY;
     }
}
