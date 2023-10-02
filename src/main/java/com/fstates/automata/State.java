package com.fstates.automata;

import com.fstates.object.entity.Enemy;
import com.fstates.object.entity.Entity;

/**
 * Simples interface para as classes que representam o estado das entidades
 * @param T o tipo do game object
*/
public interface State {

    /**
     * Este método é executado quando se entra no estado
     */
    public void enter(Enemy enemy);

    /**
     * Este método é chamado pela função de atualização (update)
     * do game object a cada etapa de atualização
     */
    public void execute(Enemy enemy);

    /**
     * Este método é executado quando se sai do estado
     */
    public void exit(Enemy enemy);

}
