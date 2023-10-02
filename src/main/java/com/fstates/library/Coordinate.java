package com.fstates.library;

/**
 * A classe Coordinates simplesmente representa as coordenadas
 * dos objetos do jogo, nada mais é que um Pair de dois "Integers"
 * e todos seus métodos essenciais já estão implementos na classe abstrata Pair 
*/
public class Coordinate extends Pair<Integer,Integer>{
    public Coordinate(Integer x, Integer y) {
        super(x, y);
    }


    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Coordinate)
        {
            Coordinate o = (Coordinate) obj;
            if(o.getX() == getX() && o.getY() == getY())
            {
                return true;
            }
        }
        return false;
    }
}
