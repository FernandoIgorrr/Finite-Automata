package com.fstates.object.entity;

/**
 * Enum para representar todos os tipos de entidades
 * que o jogo terá, no caso teremos PLAYER E ENEMY,
 * e cada vez que instanciarmos um objeto derivado 
 * da classe Entity ele terá um atributo do tipo EtityType
 * cujo valor será PLAYER ou ENEMY. poderiamos fazer também
 * um interface com Strings constantes que seriam os nomes 
 * do "tipo" da classe, e na classe Entity teriamos no lugar
 * de um atributo "EntityType entityType" um "String type". 
 */
public enum EntityType 
{
    PLAYER,
    ENEMY;
}
