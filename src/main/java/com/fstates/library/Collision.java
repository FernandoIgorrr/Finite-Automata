package com.fstates.library;

public class Collision {
    public boolean collisionOn;
    public Direction collisionDirection;

    public  Collision(boolean collisionOn, Direction collisionDirection){
        this.collisionOn        = collisionOn;
        this.collisionDirection = collisionDirection;
    }
}
