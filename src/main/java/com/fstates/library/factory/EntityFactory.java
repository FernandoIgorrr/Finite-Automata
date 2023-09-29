package com.fstates.library.factory;

import java.util.HashMap;
import java.util.Map;

import com.fstates.object.entity.Enemy;
import com.fstates.object.entity.Entity;
import com.fstates.object.entity.EntityType;

public class EntityFactory implements AbstractFactory<Entity, EntityType>
{

    private         Map<EntityType,Entity>  entityPrototype = new HashMap<>();
    private static  EntityFactory           instance;


    private EntityFactory()
    {
        createPrototypeMap();
    }

    public static EntityFactory getInstance()
    {
        if (instance == null)
        {
            instance = new EntityFactory();
        }
        return instance;
    }

    @Override
    public Entity create(EntityType entityType)

    {
        return entityPrototype.get(entityType).clone();
    }


    private void createPrototypeMap()

    {
        entityPrototype.put(EntityType.ENEMY, new Enemy(null, null));
    }
}
