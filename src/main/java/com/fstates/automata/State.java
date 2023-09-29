package com.fstates.automata;

/**
 * Simples interface para as classes que representam o estado das entidades
 * @param T o tipo do game object
*/
public interface State<T>{

    /**
     * Este método é executado quando se entra no estado
    */
    public void enter(T t);

    /**
    * Este método é chamado pela função de atualização (update)
	* do game object a cada etapa de atualização
    */
    public void execute(T t);

    /**
     * Este método é executado quando se sai do estado
    */
    public void exit(T t);

    public StateType getState();
}
