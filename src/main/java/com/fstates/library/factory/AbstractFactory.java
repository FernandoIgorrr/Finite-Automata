package com.fstates.library.factory;

public interface AbstractFactory<T, E>{
    T create(E type); 
}