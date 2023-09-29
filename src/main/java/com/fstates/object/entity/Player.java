package com.fstates.object.entity;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import com.fstates.automata.ActionType;
import com.fstates.automata.NoAnimation;
import com.fstates.automata.State;
import com.fstates.game.GamePanel;
import com.fstates.library.Coordinates;
import com.fstates.library.Direction;
import com.fstates.library.KeyHandler;

import javax.imageio.IIOImage;
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

    private static Player       instance;

    private Player(String name, GamePanel gamePanel, Coordinates coordinates)
    {
        super(EntityType.PLAYER, gamePanel, new NoAnimation(), coordinates, 5);
        this.name   = name;
        keyHandler  = new KeyHandler();
        loadSprites();
    }

    public static Player getInstance(String name, GamePanel gamePanel, Coordinates coordinates)
    {
        if (instance == null)
        {
            instance = new Player (name, gamePanel, coordinates);
        }
        return instance;
    }

    public static Player getInstance(String name, GamePanel gamePanel, int x, int y)
    {
        if (instance == null)
        {
            instance = new Player (name, gamePanel, new Coordinates(x, y));
        }
        instance.setCoordinates(x, y);
        return instance;
    }

    public void update(){
        super.update();
        checkKeyToMove();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(getX(), getY(), gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void loadSprites() {
        actionsSprites = new HashMap<>();

        System.out.println(System.getProperty("user.dir"));


        try {

            BufferedImage bi = ImageIO.read(getClass().getResourceAsStream("/home/figor/Documentos/Projetos/jogos/Finite-Automata/src/main/java/resources/assets/images/sprites/player/boy_down_1.png"));

            //actionsSprites.put(ActionType.WALK_NORTH_1, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_up_1.png")));
//            actionsSprites.put(ActionType.WALK_NORTH_2, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_up_2.png")));
//            actionsSprites.put(ActionType.WALK_SOUTH_1, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_down_1.png")));
//            actionsSprites.put(ActionType.WALK_SOUTH_2, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_down_2.png")));
//            actionsSprites.put(ActionType.WALK_EAST_1, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_right_1.png")));
//            actionsSprites.put(ActionType.WALK_EAST_2, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_right_2.png")));
//            actionsSprites.put(ActionType.WALK_WEST_1, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_left_1.png")));
//            actionsSprites.put(ActionType.WALK_WEST_2, ImageIO.read(getClass().getResourceAsStream("resources/assets/images/sprites/player/boy_left_2.png")));

        }catch (IOException e)
        {
            System.out.println("DANDO ERRADO");
            //e.printStackTrace();
        }

    }

    private void checkKeyToMove(){
         //Duas teclas pressionadas ao mesmo tempo

        if(keyHandler.wPressed && keyHandler.dPressed)
        {
            move(Direction.NORTH_EAST);
        }
        else if(keyHandler.wPressed && keyHandler.aPressed)
        {
            move(Direction.NORTH_WEST);
        }
        else if(keyHandler.sPressed && keyHandler.dPressed)
        {
            move(Direction.SOUTH_EAST);
        }
        else if(keyHandler.sPressed && keyHandler.aPressed)
        {
            move(Direction.SOUTH_WEST);
        }

        //Uma tecla pressionada

        else if(keyHandler.wPressed)
        {
            move(Direction.NORTH);
        }
        else if(keyHandler.sPressed)
        {
            move(Direction.SOUTH);
        }
        else if(keyHandler.aPressed)
        {
            move(Direction.WEST);
        }
        else if(keyHandler.dPressed)
        {
            move(Direction.EAST);
        }
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
