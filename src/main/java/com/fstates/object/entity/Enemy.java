package com.fstates.object.entity;

import com.fstates.game.GamePanel;
import com.fstates.library.Coordinates;

import java.awt.*;

public class Enemy extends Entity
{

    public Enemy(GamePanel gamePanel, Coordinates coordinates)
    {
        super(EntityType.ENEMY, gamePanel, coordinates, 5);
    }

    public void spawnEnemy(Coordinates coordinates)
    {

    }

    public void patrol()
    {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void loadSprites() {

    }

    @Override
    public Enemy clone()
    {
        return new Enemy(gamePanel, getCoordinates());
    }
}