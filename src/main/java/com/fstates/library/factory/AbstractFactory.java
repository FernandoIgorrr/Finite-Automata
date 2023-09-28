package com.fstates.library.factory;

public interface AbstractFactory<E,T>{
    T create(E type); 
}
