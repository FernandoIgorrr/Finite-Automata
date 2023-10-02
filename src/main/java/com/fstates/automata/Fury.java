package com.fstates.automata;

import com.fstates.object.entity.Enemy;

public class Fury implements State
{

    private static Fury instance;

    private Fury()
    {

    }

    public static Fury getInstance()
    {
        if(instance == null)
        {
            instance = new Fury();
        }

        return instance;
    }
    @Override
    public void enter(Enemy enemy) {
        System.out.println("========>>> ENTER FURY***");
    }

    @Override
    public void execute(Enemy enemy) {
        enemy.chasePlayer();
    }

    @Override
    public void exit(Enemy enemy) {

    }
}
