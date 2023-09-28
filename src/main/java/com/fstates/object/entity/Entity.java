package com.fstates.object.entity;

import com.fstates.library.Coordinates;
import com.fstates.object.GameObject;


/**
 * Essa será a classe base para as entidades do jogo,
 * Como por exemplo o player, os inimigos, os "neutros"
 * (nps de forma geral), qualquer coisa que se mova ou 
 * não seja estática.
*/
public abstract class Entity implements GameObject
{

    private EntityType  entityType;
    private Coordinates coordinates;
    private double      speed;

    /**
     * Construtores com o modificador de acesso protected
     * para apenas as classes derivadas poderem invoca-lo.
     * 
     * ***Note que eu chamo construtores dentro de outros
     * construtores quanto mais parametrizado o construtor 
     * é, simplemente completo com o construtor "anterior"
     * no que se refere a quantidades de parâmetros.
    */
    protected Entity(EntityType  entityType)
    {
        this.entityType = entityType;
    }

    protected Entity(EntityType  entityType, double speed)
    {
        this(entityType);
        this.speed = speed;
    }
    
    protected Entity(EntityType  entityType, Coordinates coordinates, double speed)
    {
        this(entityType,speed);
        this.coordinates    = coordinates;
    }

    public Entity(Entity entity){
        coordinates = entity.getCoordinates();
        entityType  = entity.getEntityType();
        speed       = entity.getSpeed();
    }

    // Get do atributo entetyType
    public EntityType getEntityType() {
        return entityType;
    }

    // Setters e Get do atributo coordinates
    public Coordinates getCoordinates()
    {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public void setCoordinates(int x,int y){
        coordinates.setPair(x, y);
    }

    // Set e Get do atributo X
    public int getX()
    {
        return getCoordinates().getX();
    }

    public void setX(int x)
    {
      getCoordinates().setX(x);
    }

    // Set e Get do atributo Y
    public int getY()
    {
        return getCoordinates().getY();
    }
    public void setY(int y)
    {
      getCoordinates().setY(y);
    }

    // Set e Get do atributo speed
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public Entity clone(){
        return null;
    }
}
