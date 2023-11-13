package com.fstates.automata;

import com.fstates.object.entity.Enemy;

public class BackToPatrol implements State
{

    private static BackToPatrol instance;

    private BackToPatrol()
    {

    }

    public static BackToPatrol getInstance()
    {
        if(instance == null)
        {
            instance = new BackToPatrol();
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
