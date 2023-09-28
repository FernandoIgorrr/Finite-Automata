package com.fstates.library;


/**
 * Esta é classe abstrata Pair. Ela é a classe base para tudo que for utilizar
 * um par de tipos não necessariamente iguais, irá ser muito utilizada como classe
 * base da classe Coordinates que representa as coordenadas dos objetos do jogo em cena.
 *
*/
public abstract class Pair<X, Y>
{
    private X x;
    private Y y;

    /**
     * Aqui temos o construtor da classe, onde passaremos os tipos (de objetos) que
     * queremos (Integer e Integer, String e Integer, Player e Enemy e etc...)
     * e os valores.
     * @param x valor do primeiro objeto do par
     * @param y valor do segundo objeto do par
    */
    protected Pair(X x,Y y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Método get da propriedade x.
     * @return retornar a propriedade x.
    */
    public X getX()
    {
        return x;
    }
    
    /**
     * Método get da propriedade y.
     * @return retornar a propriedade y.
    */
    public Y getY()
    {
        return y;
    }

    /**
     * Método set da propriedade x.
     * @param x entra com o novo valor para a propriedade x;
    */
    public void setX(X x)
    {
        this.x = x;
    }

    /**
     * Método set da propriedade y
     * @param x entra com o novo valor para a propriedade x;
    */
    public void setY(Y y)
    {
         this.y = y;
    }


    /**
     * Método set das proprioedades x e y ao mesmo tempo
     * @param x entra com o novo valor para a propriedade x
     * @param y entra com o novo valor para a propriedade y
    */
    public void setPair(X x,Y y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
