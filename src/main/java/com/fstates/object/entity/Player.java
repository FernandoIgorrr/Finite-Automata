package com.fstates.object.entity;

import com.fstates.library.Coordinates;
/**
 * A famigerada classe player, como podemos ver foi aplicado o
 * padrão singleton visto que realmente só existe um player no jogo
 * inteiro. Então para evitar erros ou instâncias de player desnecessária
 * voilà
*/
public class Player extends Entity{
    private        String name;
    private static Player instance;

    private Player(String name, Coordinates coordinates)
    {
        super(EntityType.PLAYER, coordinates, 5);
        this.name   = name;
    }

    public static Player getInstance(String name, Coordinates coordinates)
    {
        if (instance == null)
        {
            instance = new Player (name, coordinates);
        }
        return instance;
    }
}
