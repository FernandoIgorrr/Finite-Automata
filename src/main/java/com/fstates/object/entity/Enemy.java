package com.fstates.object.entity;

import com.fstates.automata.State;
import com.fstates.library.Coordinates;

public class Enemy extends Entity
{

    public Enemy(Coordinates coordinates)
    {
        super(EntityType.ENEMY, coordinates, 5);
    }

    @Override
    public Enemy clone()
    {
        return new Enemy(getCoordinates());
    }
}
