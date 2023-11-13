package com.fstates.object.entity;

import com.fstates.automata.Patrol;
import com.fstates.automata.State;
import com.fstates.game.GamePanel;
import com.fstates.library.ActionType;
import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.library.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Enemy extends Entity
{
    Random random;
    private int patrolRange = 60;
    public Area limitPatrol;
    private Coordinate     patrolCoord;
    public Enemy(GamePanel gamePanel, Coordinate coordinate)
    {
        super(EntityType.ENEMY, gamePanel, coordinate, 3);
        state       = Patrol.getInstance();
        direction   = Direction.WEST;
        random      = new Random();
    }

    public void setPatrolCoord(Coordinate patrolCoord) {
        this.patrolCoord = patrolCoord;

        limitPatrol = new Area(
                getPatrolCoordinates().getX() - patrolRange,
                getPatrolCoordinates().getY() - patrolRange,
                getPatrolCoordinates().getX() + patrolRange,
                getPatrolCoordinates().getY() + patrolRange);

        //setCollisionArea();
    }

    public void setSprite(Image sprite)
    {
        //System.out.println("MUDOU SPRITE PARA: " + sprite.getSource());
        currentSprite = sprite;
    }

    public void checkBound()
    {

        if(getX() <= limitPatrol.initialX)
        {
            direction = Direction.NORTH;
        }
        if(getY() <= limitPatrol.initialY)
        {
            direction = Direction.EAST;

        }
        if(getX() >= limitPatrol.finalX)
        {
            direction = Direction.SOUTH;

        }
        if(getY() >= limitPatrol.finalY)
        {
            direction = Direction.WEST;

        }

        if(getY() >= limitPatrol.finalY && getX() <= limitPatrol.initialX){
            direction = Direction.NORTH_WEST;
        }
    }

    public void changeAnimation()
    {
        switch (direction)
        {
            case NORTH:
                if (spriteNum == 1) {
                    setSprite(actionsSprites.get(ActionType.WALK_NORTH_1));
                }
                if (spriteNum == 2) {
                    //currentSprite = actionsSprites.get(ActionType.WALK_NORTH_2);
                    setSprite( actionsSprites.get(ActionType.WALK_NORTH_2));

                }
                break;
            case SOUTH:
                if (spriteNum == 1) {
                    //currentSprite = actionsSprites.get(ActionType.WALK_SOUTH_1);
                    setSprite( actionsSprites.get(ActionType.WALK_SOUTH_1));

                }
                if (spriteNum == 2) {
                   // currentSprite = actionsSprites.get(ActionType.WALK_SOUTH_2);
                    setSprite( actionsSprites.get(ActionType.WALK_SOUTH_2));

                }
                break;
            case EAST:
            case SOUTH_EAST:
            case NORTH_EAST:
                if (spriteNum == 1) {
                    currentSprite = actionsSprites.get(ActionType.WALK_EAST_1);
                }
                if (spriteNum == 2) {
                    currentSprite = actionsSprites.get(ActionType.WALK_EAST_2);
                }
                break;
            case WEST:
            case NORTH_WEST:
            case SOUTH_WEST:
                if (spriteNum == 1) {
                    currentSprite = actionsSprites.get(ActionType.WALK_WEST_1);
                }
                if (spriteNum == 2) {
                    currentSprite = actionsSprites.get(ActionType.WALK_WEST_2);
                }
                break;
        }
    }

    public void patrol()
    {
        checkBound();
        move();
        changeAnimation();
    }

    public boolean collided(Entity entity)
    {
        return collisionArea.isOverlapping(entity.getCollisionArea());
    }
    public void backToPatrol()
    {
        move();
        changeAnimation();

        if(getX() <= getPatrolCoordinates().getX() && getY() <= getPatrolCoordinates().getY())
        {
            direction = Direction.SOUTH_EAST;
        }

        else if(getX() >= getPatrolCoordinates().getX() && getY() <= getPatrolCoordinates().getY())
        {
            direction = Direction.SOUTH_WEST;
        }

        else  if(getX() <= getPatrolCoordinates().getX() && getY() >= getPatrolCoordinates().getY())
        {

            direction = Direction.NORTH_EAST;
        }

        else if(getX() >= getPatrolCoordinates().getX() && getY() >= getPatrolCoordinates().getY())
        {
            direction = Direction.NORTH_WEST;
        }

        //*********************************************************************************************************

        else  if(getX() <= getPatrolCoordinates().getX())
        {
            direction = Direction.EAST;
        }
        else  if(getX() >= getPatrolCoordinates().getX())
        {
            direction = Direction.WEST;
        }
        else if(getY() <= getPatrolCoordinates().getY())
        {
            direction = Direction.SOUTH;
        }
        else  if(getY() >= getPatrolCoordinates().getY())
        {
            direction = Direction.NORTH;
        }
        //System.out.println("Coord: " + getCoordinates() + "\n PCoord" + getPatrolCoordinates());
    }



    public void changeState(State state)
    {
        if(state != null)
        {
            this.state.exit(this);

            this.state = state;

            this.state.enter(this);
        }
    }


    public void chasePlayer(){

        move();
        changeAnimation();

        if(getX() < Player.getInstance().getX() && getY() < Player.getInstance().getY())
        {
            direction = Direction.SOUTH_EAST;
        }

        else if(getX() > Player.getInstance().getX() && getY() < Player.getInstance().getY())
        {
            direction = Direction.SOUTH_WEST;
        }

        else  if(getX() < Player.getInstance().getX() && getY() > Player.getInstance().getY())
        {
            direction = Direction.NORTH_EAST;
        }

         else if(getX() > Player.getInstance().getX() && getY() > Player.getInstance().getY())
        {
            direction = Direction.NORTH_WEST;
        }

        //*********************************************************************************************************

        else  if(getX() <= Player.getInstance().getX())
        {
            direction = Direction.EAST;
        }
        else  if(getX() > Player.getInstance().getX())
        {
            direction = Direction.WEST;
        }
         else if(getY() <= Player.getInstance().getY())
        {
            direction = Direction.SOUTH;
        }
        else  if(getY() > Player.getInstance().getY())
        {
            direction = Direction.NORTH;
        }
    }

    private int calculateDistance(int x1,int y1,int x2,int y2){
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public boolean nearbyPlayer(){
        int distance = calculateDistance(
                getX(),
                getY(),
                Player.getInstance().getX(),
                Player.getInstance().getY()
        );
        //System.out.println("DISTANCIA: " + distance);
        if(distance > 200){
            return false;
        }
        return true;
    }

    public boolean nearbyPatrol(){
        int distance = calculateDistance(
                getCoordinates().getX(),
                getCoordinates().getY(),
                getPatrolCoordinates().getX(),
                getPatrolCoordinates().getY()
        );
        if(distance > patrolRange){
            return false;
        }
        return true;
    }

    public boolean wasStolen(){
        //int submapnum = gamePanel.tileMap.subMapFinder(patrolCoord);

        //return !gamePanel.checkIteminSubMap(submapnum);
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(currentSprite, getX(), getY(), gamePanel.tileSize, gamePanel.tileSize, null);

//        g.setColor(Color.BLACK);
//       g.fillRect(collisionArea.initialX,collisionArea.initialY, gamePanel.tileSize - 7, gamePanel.tileSize - 6);

    }

    @Override
    public void loadSprites() {
        actionsSprites = new HashMap<>();

        try {
            actionsSprites.put(ActionType.WALK_NORTH_1, ImageIO.read(new File("assets/monster/orc_up_1.png")));
            actionsSprites.put(ActionType.WALK_NORTH_2, ImageIO.read(new File("assets/monster/orc_up_2.png")));
            actionsSprites.put(ActionType.WALK_SOUTH_1, ImageIO.read(new File("assets/monster/orc_down_1.png")));
            actionsSprites.put(ActionType.WALK_SOUTH_2, ImageIO.read(new File("assets/monster/orc_down_2.png")));
            actionsSprites.put(ActionType.WALK_EAST_1,  ImageIO.read(new File("assets/monster/orc_right_1.png")));
            actionsSprites.put(ActionType.WALK_EAST_2,  ImageIO.read(new File("assets/monster/orc_right_2.png")));
            actionsSprites.put(ActionType.WALK_WEST_1,  ImageIO.read(new File("assets/monster/orc_left_1.png")));
            actionsSprites.put(ActionType.WALK_WEST_2,  ImageIO.read(new File("assets/monster/orc_left_2.png")));

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        spriteCount++;

        if(spriteCount > 10){
            if(spriteNum == 1){
                spriteNum   = 2;
            }
            else{
                spriteNum   = 1;
            }
            spriteCount = 0;
        }

        if(state != null)
        {
            state.execute(this);
        }

    }
    private Coordinate getPatrolCoordinates(){
        return patrolCoord;
    }
    @Override
    public Enemy clone()
    {
        return new Enemy(gamePanel, getCoordinates());
    }
}