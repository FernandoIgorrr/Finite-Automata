package com.fstates.automata;

import com.fstates.object.entity.Player;

public class NoAnimation implements State<Player>{

    public NoAnimation(){

    }

    @Override
    public void enter(Player p)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enter'");
    }

    @Override
    public void execute(Player p)
    {

    }

    @Override
    public void exit(Player p)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }
}
