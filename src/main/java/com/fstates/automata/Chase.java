package com.fstates.automata;

import com.fstates.object.entity.Enemy;

public class Chase implements State{

    private static Chase instance;

    private Chase()
    {

    }

    public static Chase getInstance()
    {
        if(instance == null)
        {
            instance = new Chase();
        }

        return instance;
    }
    @Override
    public void enter(Enemy enemy)
    {
        System.out.println("========>>> ENTER CHASE***");

    }

    @Override
    public void execute(Enemy enemy)
    {
       // System.out.println("EXECUTE CHASE***");
        if(enemy.wasStolen())
        {
            enemy.changeState(Fury.getInstance());
        }
        if(!enemy.nearbyPlayer())
        {
            enemy.changeState(BackPatrol.getInstance());
        }
        enemy.chasePlayer();
    }

    @Override
    public void exit(Enemy enemy)
    {
        System.out.println("========>>> EXITE CHASE***");

    }
}
