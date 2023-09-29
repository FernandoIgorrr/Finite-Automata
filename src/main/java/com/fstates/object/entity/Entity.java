package com.fstates.object.entity;

import com.fstates.automata.NoAnimation;
import com.fstates.automata.State;
import com.fstates.library.Coordinates;
import com.fstates.library.Direction;
import com.fstates.library.Status;
import com.fstates.object.GameObject;


/**
 * Essa será a classe base para as entidades do jogo,
 * Como por exemplo o player, os inimigos, os "neutros"
 * (nps de forma geral), qualquer coisa que se mova ou 
 * não seja estática.
*/
public abstract class Entity implements GameObject
{

    //Propriedades gerais:
    private final   EntityType      entityType;
    private         Coordinates     coordinates;
    private         double          speed;
    private         State<Entity>   state;
    private         Status          status;

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
        status              = new Status();
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

    protected Entity(EntityType  entityType, State state, Coordinates coordinates, double speed){
        this(entityType,coordinates,speed);
        this.state = state;
    }

    protected Entity(Entity entity)
    {
        status              = entity.getStatus();            
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

    // Setter e Getter do atributo status
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
    // Fim dos Setter e Getter de status

    // *****************************************************************

    /** 
     * Método raíz para chamar outros métodos que modificarão
     * as propriedades do objeto.
     */

    public void move(Direction direction){
        switch(direction){
            case NORTH:
                setY(getY()-(int)speed);
                break;
            case SOUTH:
                setY(getY()+(int)speed);
                break;
            case EAST:
                setX(getX()+(int)speed);
                break;
            case WEST:
                setX(getX()-(int)speed);
                break;
            
            //Duas dieções ao mesmo tempos

            case NORTH_EAST:
                setY(getY()-(int)speed);
                setX(getX()+(int)speed);
                break;
            case NORTH_WEST:
                setY(getY()-(int)speed);
                setX(getX()-(int)speed);
                break;
            case SOUTH_EAST:
                setY(getY()+(int)speed);
                setX(getX()+(int)speed);
                break;
            case SOUTH_WEST:
                setY(getY()+(int)speed);
                setX(getX()-(int)speed);
                break;
        }
    }


    public void update()
    {
        this.state.execute(this);
    }

    /**
     * Método para alterar o estado da entidade baseado no padrão State Design, 
     * onde teremos o fluxo de ações (entrada, mudança e saída) para cada
     * estado
    */
    public void changeState(State state)
    {

        boolean  transitionStates = this.state != null && state != null;
        
        assert(transitionStates);

        state.exit(this);

        this.state = state;

        state.enter(this);

    }

    @Override
    public Entity clone()
    {
        return null;
    }

    @Override
    public String toString() {
        return 
        
        "\tEntity type\t= "   + entityType    + "\n" +
        "\tCoordinate\t= "    + coordinates   + "\n" +
        "\tSpeed\t\t= "       + speed         + "\n" +
        "\tState\t\t= "       + state         + "\n" +
        "\t" + status;
    }
}
