package com.fstates.automata;

public interface State<T>{

    public void execute(T t);

    public StateType getState();
}
