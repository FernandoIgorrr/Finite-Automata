package com.fstates.object;

/*
 *  Enum para representar o lugar do tile que o objeto vai ser desenhado
 *  por exemplo, o personagem e os mobs são desenhados (são insanceados pela primeira vez)
 *  no meio do tile mas as coisas do ambiente como a grama paredes e elementes do tipo são sempre
 *  desenhados apartir da ponte esquerda superior do tile (e assim prrencher todo o tile)
* */
public enum Place
{
    STANDARD,
    MIDDLE
}
