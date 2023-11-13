package com.fstates.library.factory;

import java.util.HashMap;
import java.util.Map;

public class FactoryProvider{

    private Map<FactoryType,AbstractFactory> factoryPrototype = new HashMap<>();
    
    private static FactoryProvider instance;

    private FactoryProvider()
    {
        createPrototypeMap();
    }

    public static FactoryProvider getInstance()
    {
        if (instance == null)
        {
            instance = new FactoryProvider();
        }
        return instance;
    }

    public static AbstractFactory getFactory(FactoryType factoryType)
    {
        return instance.factoryPrototype.get(factoryType);
    }

    private void createPrototypeMap()
    {
        factoryPrototype.put(FactoryType.ENTITY,EntityFactory.getInstance());
        factoryPrototype.put(FactoryType.TILE,TileFactory.getInstance());
    }
}
