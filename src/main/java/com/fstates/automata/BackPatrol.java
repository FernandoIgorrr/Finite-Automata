package com.fstates.automata;

import com.fstates.object.entity.Enemy;

public class BackPatrol implements State
{

    private static BackPatrol instance;

    private BackPatrol()
    {

    }

    public static BackPatrol getInstance()
    {
        if(instance == null)
        {
            instance = new BackPatrol();
        }

        return instance;
    }

    @Override
    public void enter(Enemy enemy)
    {
        System.out.println("========>>> ENTER BACK_TO_PATROL***");
    }

    @Override
    public void execute(Enemy enemy)
    {
        //System.out.println("EXECUTE BACK_TO_PATROL***");

       if(enemy.wasStolen()){
            enemy.changeState(Fury.getInstance());
       }
       if(enemy.nearbyPlayer())
       {
           enemy.changeState(Chase.getInstance());
       }
       if(enemy.nearbyPatrol())
       {
           enemy.changeState(Patrol.getInstance());
       }
        enemy.backToPatrol();
    }

    @Override
    public void exit(Enemy enemy)
    {
        System.out.println("========>>> EXIT BACK_TO_PATROL***");

    }
}
