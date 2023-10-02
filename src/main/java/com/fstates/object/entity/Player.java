package com.fstates.object.entity;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fstates.library.ActionType;
import com.fstates.game.GamePanel;
import com.fstates.library.Coordinate;
import com.fstates.library.Direction;
import com.fstates.library.KeyHandler;
import com.fstates.object.item.Axe;
import com.fstates.object.item.Boots;
import com.fstates.object.item.Coin;
import com.fstates.object.item.Item;

import javax.imageio.ImageIO;

/**
 * A famigerada classe player, como podemos ver foi aplicado o
 * padrão singleton visto que realmente só existe um player no jogo
 * inteiro. Então para evitar erros ou instâncias de player desnecessária
 * voilà
*/
public class Player extends Entity
{
    private        String       name;
    private        KeyHandler  keyHandler;
    private         int coin = 0;
    private         boolean     colisionItem = false;
    private        int          axe = 0;



    private static Player       instance;

    private Player(String name, GamePanel gamePanel, Coordinate coordinate)
    {
        super(EntityType.PLAYER, gamePanel, null, coordinate, 4);
        this.name       = name;
        keyHandler      = new KeyHandler();
        //loadSprites();
        currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_1);
    }

    public static Player getInstance()
    {
        return instance;
    }
    public static Player getInstance(String name, GamePanel gamePanel, Coordinate coordinate)
    {
        if (instance == null)
        {
            instance = new Player (name, gamePanel, coordinate);
        }
        return instance;
    }

    public static Player getInstance(String name, GamePanel gamePanel, int x, int y)
    {
        if (instance == null)
        {
            instance = new Player (name, gamePanel, new Coordinate(x, y));
        }
        instance.setCoordinates(x, y);
        return instance;
    }
//    public void throwAxe(){
//        if(axe > 1)
//        {
//            Axe axe = new Axe(gamePanel, coordinate);
//            switch (direction)
//            {
//                case NORTH:
//                defaul:
//                    axe.setY(getY() - speed/2);
//                    break;
//                case SOUTH:
//                    axe.setY(getY() + speed/2);
//                    break;
//                case EAST:
//                    axe.setY(getX() + speed/2);
//                    break;
//                case WEST:
//                    axe.setY(getY() - speed/2);
//                    break;
//
//            }
//            this.axe--;
//            System.out.println("ACABOU OS MACHADO");
//        }

//    }
    public void powerUp(Item item)
    {
        if(item instanceof Boots)
        {
            speed++;
        }
        else if(item instanceof Coin)
        {
            coin++;
        }
    }
    public void update()
    {
        checkKeyToMove();

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
    }

    @Override
    public void draw(Graphics2D g)
    {
//        g.setColor(Color.WHITE);
//        g.fillRect(getX(), getY(), gamePanel.tileSize, gamePanel.tileSize);
        g.drawImage(currentSprite, getX(), getY(), gamePanel.tileSize, gamePanel.tileSize, null);
//        g.setColor(Color.BLACK);
//       g.fillRect(colisionArea.initialX,colisionArea.initialY, gamePanel.tileSize - 7, gamePanel.tileSize - 6);
    }

    @Override
    public void loadSprites()
    {
        actionsSprites = new HashMap<>();

        try {
            actionsSprites.put(ActionType.WALK_NORTH_1, ImageIO.read(new File("assets/player/boy_up_1.png")));
            actionsSprites.put(ActionType.WALK_NORTH_2, ImageIO.read(new File("assets/player/boy_up_2.png")));
            actionsSprites.put(ActionType.WALK_SOUTH_1, ImageIO.read(new File("assets/player/boy_down_1.png")));
            actionsSprites.put(ActionType.WALK_SOUTH_2, ImageIO.read(new File("assets/player/boy_down_2.png")));
            actionsSprites.put(ActionType.WALK_EAST_1,  ImageIO.read(new File("assets/player/boy_right_1.png")));
            actionsSprites.put(ActionType.WALK_EAST_2,  ImageIO.read(new File("assets/player/boy_right_2.png")));
            actionsSprites.put(ActionType.WALK_WEST_1,  ImageIO.read(new File("assets/player/boy_left_1.png")));
            actionsSprites.put(ActionType.WALK_WEST_2,  ImageIO.read(new File("assets/player/boy_left_2.png")));

        }catch (IOException e)
        {
           e.printStackTrace();
        }
    }

    private void checkKeyToMove()
    {
        //Duas teclas pressionadas ao mesmo tempo
        if(keyHandler.wPressed && keyHandler.dPressed)
        {
            direction       = Direction.NORTH_EAST;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_NORTH_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_NORTH_2);
            }
        }
        else if(keyHandler.wPressed && keyHandler.aPressed)
        {
            direction       = Direction.NORTH_WEST;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_NORTH_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_NORTH_2);
            }
        }
        else if(keyHandler.sPressed && keyHandler.dPressed)
        {
            direction       = Direction.SOUTH_EAST;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_2);
            }
        }
        else if(keyHandler.sPressed && keyHandler.aPressed)
        {
            direction       = Direction.SOUTH_WEST;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_2);
            }
        }

        //Uma tecla pressionada

        else if(keyHandler.wPressed)
        {
            direction   = Direction.NORTH;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_NORTH_1);
            }
            if(spriteNum == 2){
                currentSprite   = actionsSprites.get(ActionType.WALK_NORTH_2);
            }
        }
        else if(keyHandler.sPressed)
        {
            direction   = Direction.SOUTH;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_SOUTH_2);
            }

        }
        else if(keyHandler.aPressed)
        {
            direction   = Direction.WEST;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_WEST_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_WEST_2);
            }

        }
        else if(keyHandler.dPressed)
        {
            direction   = Direction.EAST;
            if(spriteNum == 1)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_EAST_1);
            }
            if(spriteNum == 2)
            {
                currentSprite   = actionsSprites.get(ActionType.WALK_EAST_2);
            }
        }

        // Esse null é necessário para o player ficar parado
        else
        {
            direction = null;
        }

        move();
    }

    public KeyListener getKeyHandler(){
        return keyHandler;
    }

    @Override
    public String toString() {
        return "Player [\n" + 
        "\tname\t\t= " + name +
        "\n" + super.toString() + "\n]";
    }
}
