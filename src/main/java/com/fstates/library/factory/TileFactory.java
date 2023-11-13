package com.fstates.library.factory;

import com.fstates.object.tile.*;

import java.util.HashMap;
import java.util.Map;

public class TileFactory implements AbstractFactory<Tile, TileType>
{
    private Map<TileType, Tile> tilePrototype = new HashMap<>();

    private static  TileFactory instance;


    private TileFactory()
    {
        createPrototypeMap();
    }

    public static TileFactory getInstance()
    {
        if (instance == null)
        {
            instance = new TileFactory();
        }
        return instance;
    }

    @Override
    public Tile create(TileType tileType) {
        return tilePrototype.get(tileType).clone();
    }

    private void createPrototypeMap()
    {
        tilePrototype.put(TileType.GRASS,   new Grass(null));
        tilePrototype.put(TileType.WALL,    new Wall(null));
        tilePrototype.put(TileType.WATER,   new Water(null));
        tilePrototype.put(TileType.TREE,    new Tree(null));
    }
}
