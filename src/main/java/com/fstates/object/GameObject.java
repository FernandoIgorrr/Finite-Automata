package com.fstates.object;

import com.fstates.automata.State;
import com.fstates.library.*;

/**
 * A interface GameObject é a classe "raíz" da aplicação.
 * Quase tudo é um objeto de jogo (vulgo gameobject),
 * até as coisas estáticas, ou seja, tudo que intereja 
 * visualmente, "fisicamente"(uma barreira invisível por exemplo)
 * ou de qualquer outra maneira resulta em umas das interações
 * anteriores irá implementar essa interface 
 * 
*/
public interface GameObject
{
    
    public void update();
    public void changeState(State state);

    public Coordinates getCoordinates();
    public int getX();
    public int getY();
}
