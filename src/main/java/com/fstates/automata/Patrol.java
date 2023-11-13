package com.fstates.automata;

import com.fstates.object.entity.Enemy;
import com.fstates.object.entity.Entity;

public class Patrol implements State{

    private static Patrol instance;

    private Patrol()
    {

    }

    public static State getInstance()
    {
        if(instance == null)
        {
            instance = new Patrol();
        }

        return instance;
    }

    @Override
    public void enter(Enemy enemy) {
        System.out.println("========>>>" + enemy.hashCode() + " ENTER PATROL***");
    }

    @Override
    public void execute(Enemy enemy) {
        //System.out.println("EXECUTE PATROL***");

        if(enemy.wasStolen())
        {
            enemy.changeState(Fury.getInstance());
        }
        if(enemy.nearbyPlayer())
        {
            enemy.changeState(Chase.getInstance());
        }
        enemy.patrol();
    }

    @Override
    public void exit(Enemy enemy) {
        System.out.println("========>>>" + enemy.hashCode() + " EXIT PATROL***");

    }
}
