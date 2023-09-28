package com.fstates.object.entity;

import com.fstates.automata.State;
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

    /**
     * Propriedades:
     * 
    */
    private final   EntityType      entityType;
    private         Coordinates     coordinates;
    private         double          speed;
    private         State<Enemy>    state;


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
        this.entityType     = entityType;
    }

    protected Entity(EntityType  entityType, double speed)
    {
        this(entityType);
        this.speed          = speed;
    }
    
    protected Entity(EntityType  entityType, Coordinates coordinates, double speed)
    {
        this(entityType,speed);
        this.coordinates    = coordinates;
    }

    public Entity(Entity entity)
    {
        coordinates         = entity.getCoordinates();
        entityType          = entity.getEntityType();
        speed               = entity.getSpeed();
    }
    // Fim dos construtores

    // *****************************************************************

    // Get do atributo entetyType
    public EntityType getEntityType()
    {
        return entityType;
    }

    // Setters e Getter do atributo coordinates
    public Coordinates getCoordinates()
    {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates)
    {
        this.coordinates    = coordinates;
    }
    public void setCoordinates(int x,int y)
    {
        coordinates.setPair(x, y);
    }
    // Fim dos Setters e Getter do atributo coordinates

    // *****************************************************************

    // Set e Get do atributo x do atributo coordiantes
    public int getX()
    {
        return getCoordinates().getX();
    }

    public void setX(int x)
    {
      getCoordinates().setX(x);
    }

    // Set e Get do atributo y do atributo coordiantes
    public int getY()
    {
        return getCoordinates().getY();
    }
    public void setY(int y)
    {
      getCoordinates().setY(y);
    }
    // Fim dos Setters e Getters dos atributos x & y do atributo coordinates

    // *****************************************************************

    // Setter e Getter do atributo speed
    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    // Fim do Setter e Getter do atribudo speed

    // *****************************************************************

    /** 
     * Método raíz para chamar outros métodos que modificarão
     * as propriedades do objeto.
     */
    public void update()
    {

    }

    public void changeState(State state)
    {
        this.state = state;
    }

    @Override
    public Entity clone()
    {
        return null;
    }

    @Override
    public String toString() {
        return "[\n" + 
        "\tEntity type\t\t= "   + entityType    + "\n" +
        "\tCoordinate\t= "      + coordinates   + "\n" +
        "\tSpeed\t= "           + speed         + "\n" +
        "\tState\t= "           + state         + "\n";
    }
}
