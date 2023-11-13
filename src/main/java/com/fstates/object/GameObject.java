package com.fstates.object;

import com.fstates.library.*;

import java.awt.*;

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
    public void draw(Graphics2D g);
    public void update();
   //public void changeState(State state);
}
