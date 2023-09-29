package com.fstates.object.entity;

import java.awt.event.KeyListener;

import com.fstates.automata.NoAnimation;
import com.fstates.automata.State;
import com.fstates.library.Coordinates;
import com.fstates.library.Direction;
import com.fstates.library.KeyHandler;
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

    private Player(String name, Coordinates coordinates)
    {
        super(EntityType.PLAYER, new NoAnimation(), coordinates, 5);
        this.name   = name;
        keyHandler  = new KeyHandler();
        
    }

    public static Player getInstance(String name, Coordinates coordinates)
    {
        if (instance == null)
        {
            instance = new Player (name, coordinates);
        }
        return instance;
    }

    public static Player getInstance(String name, int x, int y)
    {
        if (instance == null)
        {
            instance = new Player (name,new Coordinates(x, y));
        }
        instance.setCoordinates(x, y);
        return instance;
    }

    public void update(){
        super.update();
        checkKeyToMove();
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
