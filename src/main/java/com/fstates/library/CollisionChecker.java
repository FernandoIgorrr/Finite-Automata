package com.fstates.library;

import com.fstates.game.GamePanel;
import com.fstates.object.entity.Entity;

public class CollisionChecker
{
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftCol  = entity.getCollisionArea().initialX / gamePanel.tileSize;
        int entityTopRow   =  entity.getCollisionArea().initialY / gamePanel.tileSize;

        int entityRightCol  = entity.getCollisionArea().finalX  / gamePanel.tileSize;
        int entityBottomRow = entity.getCollisionArea().finalY   / gamePanel.tileSize;

        char tileNW, tileNE, tileSW, tileSE;

        if(entityLeftCol < 0    || entityLeftCol > 40      ||
           entityRightCol < 1   || entityRightCol > 41     ||
           entityTopRow < 0     || entityTopRow > 22       ||
           entityBottomRow < 1  || entityBottomRow > 23)
        {

        }

        else
        {

//            tileNW = gamePanel.tileMap.getMap()[entityTopRow][entityLeftCol];
//            tileNE = gamePanel.tileMap.getMap()[entityTopRow][entityRightCol];
//
//            tileSW = gamePanel.tileMap.getMap()[entityBottomRow][entityLeftCol];
//            tileSE = gamePanel.tileMap.getMap()[entityBottomRow][entityRightCol];

           // System.out.println("TILES:\t (" + entityTopRow + "," + entityRightCol + ")\n \t\t(" + entityBottomRow + "," + entityRightCol + ")");

            //System.out.println(entity.getDirection());
            switch (entity.getDirection()) {
                case NORTH:

                    entityTopRow    = (int)((double)entity.getCollisionArea().initialY - entity.getSpeed()) / gamePanel.tileSize;

                    if (
                            (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityTopRow][entityLeftCol]) ||
                                    (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityTopRow][entityRightCol]))
                    {
                        entity.getCollision().collisionOn        = true;
                        entity.getCollision().collisionDirection = Direction.NORTH;
                    }
                    break;

                case SOUTH:

                    entityBottomRow    = (int)((double)entity.getCollisionArea().finalY + entity.getSpeed())/gamePanel.tileSize;

                    if (
                            (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityBottomRow][entityLeftCol]) ||
                                    (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityBottomRow][entityRightCol]))
                    {
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.SOUTH;
                    }
                    break;
                case EAST:

                    entityRightCol    = (int)((double)entity.getCollisionArea().finalX + entity.getSpeed())/gamePanel.tileSize;

                    if (
                            (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityTopRow][entityRightCol]) ||
                                    (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityBottomRow][entityRightCol]))
                    {
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.EAST;
                    }
                    break;
                case WEST:

                    entityLeftCol    = (int)((double)entity.getCollisionArea().initialX - entity.getSpeed())/gamePanel.tileSize;


                    if (
                            (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityTopRow][entityLeftCol]) ||
                                    (boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityBottomRow][entityLeftCol]))
                    {
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.WEST;

                    }
                    break;

                case NORTH_EAST:

                    entityTopRow    = (int)((double)entity.getCollisionArea().initialY - entity.getSpeed()) / gamePanel.tileSize;
                    entityRightCol    = (int)((double)entity.getCollisionArea().finalX + entity.getSpeed())/gamePanel.tileSize;


                    if(((boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityTopRow][entityRightCol])))
                    {
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.NORTH_EAST;
                    }

                    break;

                case NORTH_WEST:

                    entityTopRow    = (int)((double)entity.getCollisionArea().initialY - entity.getSpeed()) / gamePanel.tileSize;
                    entityLeftCol    = (int)((double)entity.getCollisionArea().initialX - entity.getSpeed())/gamePanel.tileSize;


                    if( ((boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityTopRow][entityLeftCol])))
                    {
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.NORTH_WEST;
                    }
                    break;

                case SOUTH_EAST:

                    entityBottomRow   = (int)((double)entity.getCollisionArea().finalY + entity.getSpeed())/gamePanel.tileSize;
                    entityRightCol    = (int)((double)entity.getCollisionArea().finalX + entity.getSpeed())/gamePanel.tileSize;

                    if((boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityBottomRow][entityRightCol]))
                    {
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.SOUTH_EAST;
                    }
                    break;

                case SOUTH_WEST:

                    entityBottomRow  = (int)((double)entity.getCollisionArea().finalY + entity.getSpeed())/gamePanel.tileSize;
                    entityLeftCol    = (int)((double)entity.getCollisionArea().initialX - entity.getSpeed())/gamePanel.tileSize;

                    if((boolean) gamePanel.tileMap.getTileColision().get(gamePanel.tileMap.getMap()[entityBottomRow][entityLeftCol]))
                    {
                        //entity.setDirection(Direction.WEST);
                        entity.getCollision().collisionOn = true;
                        entity.getCollision().collisionDirection = Direction.SOUTH_WEST;
                    }
                    break;
            }
        }
    }
}
