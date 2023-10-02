package com.fstates.object.item;

import com.fstates.game.GamePanel;

import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.object.entity.Entity;
import com.fstates.object.tile.Tile;

public abstract class Item extends Tile
{
    public int submap;
    protected Area collisionArea;
    public Item(GamePanel gamePanel, Coordinate coordinate, int submap)
    {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.coordinate = coordinate;
        this.submap = submap;

        collision = true;

        collisionArea = new Area();

        collisionArea.initialX = coordinate.getX() + 2;
        collisionArea.finalX = coordinate.getX() + 28;
        collisionArea.initialY = coordinate.getY() + 4;
        collisionArea.finalY = coordinate.getY() + gamePanel.tileSize - 2;
    }

    public void setCollisionArea(Area area){
        this.collisionArea = area;
    }
     public boolean collided(Entity entity)
     {

         return collisionArea.isOverlapping(entity.getCollisionArea());
     }

}
