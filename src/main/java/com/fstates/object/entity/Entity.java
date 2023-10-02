package com.fstates.object.entity;

import com.fstates.automata.State;
import com.fstates.game.GamePanel;
import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.library.Direction;
import com.fstates.library.Status;
import com.fstates.object.DrawnableGameObject;


/**
 * Essa será a classe base para as entidades do jogo,
 * Como por exemplo o player, os inimigos, os "neutros"
 * (nps de forma geral), qualquer coisa que se mova ou 
 * não seja estática.
*/
public abstract class Entity extends DrawnableGameObject
{

    //Propriedades gerais:
    private final   EntityType          entityType;
    protected       int                 speed;
    protected       State               state;
    protected       Status              status;
    protected       Direction           direction;
    protected       Area collisionArea;
    protected       boolean             collisionOn = false;


    /**
     * Construtores com o modificador de acesso protected
     * para apenas as classes derivadas poderem invoca-lo.
     * 
     * ***Note que eu chamo construtores dentro de outros
     * construtores quanto mais parametrizado o construtor 
     * é, simplemente completo com o construtor "anterior"
     * no que se refere a quantidades de parâmetros.
    */
    protected Entity(EntityType  entityType, GamePanel gamePanel)
    {
        super(gamePanel);
        this.collision = true;
        this.entityType     = entityType;
        status              = new Status();
    }

    protected Entity(EntityType  entityType, GamePanel gamePanel, int speed)
    {
        this(entityType, gamePanel);
        this.speed          = speed;
    }

    protected Entity(EntityType  entityType, GamePanel gamePanel, Coordinate coordinate, int speed)
    {
        super(gamePanel, coordinate);
        collisionArea = new Area();
        this.entityType = entityType;
        this.speed      = speed;
    }

    protected Entity(EntityType  entityType, GamePanel gamePanel, State state, Coordinate coordinate, int speed){
        this(entityType, gamePanel, coordinate,speed);
        this.state = state;
    }

    protected Entity(Entity entity)
    {
        status              = entity.getStatus();            
        coordinate = entity.getCoordinates();
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

    // *****************************************************************

    // Setter e Getter do atributo speed
    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
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

    // Getter para direction
    public Direction getDirection()
    {
        return direction;
    }
    // fim do Getter do atributo direction

    // *****************************************************************

    // Setter e Getter para a propriedade collisionOn
    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    // Fim do Setter e Getter da propriedade collisionOn

    // *****************************************************************

    //Move a entity na direção atual"
    public void move()
    {
        if(direction == null){

        }
        else {
            setCollisionArea();
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            if(!collisionOn)
            {
                switch (direction)
                {
                    case NORTH:
                        setY(getY() - (int) speed);
                        break;
                    case SOUTH:
                        setY(getY() + (int) speed);
                        break;
                    case EAST:
                        setX(getX() + (int) speed);
                        break;
                    case WEST:
                        setX(getX() - (int) speed);
                        break;

                    //Duas dieções ao mesmo tempos

                    case NORTH_EAST:
                        setY(getY() - (int) speed);
                        setX(getX() + (int) speed);
                        break;
                    case NORTH_WEST:
                        setY(getY() - (int) speed);
                        setX(getX() - (int) speed);
                        break;
                    case SOUTH_EAST:
                        setY(getY() + (int) speed);
                        setX(getX() + (int) speed);
                        break;
                    case SOUTH_WEST:
                        setY(getY() + (int) speed);
                        setX(getX() - (int) speed);
                        break;
                    default:
                        break;
                }
            }
        }
    }


    public Area getCollisionArea()
    {
        return collisionArea;
    }

    public void setCollisionArea()
    {
        collisionArea.initialX   = coordinate.getX() + 8;
        collisionArea.initialY   = coordinate.getY() + 16;
        collisionArea.finalX     = coordinate.getX() + gamePanel.tileSize - 8;
        collisionArea.finalY     = coordinate.getY() + gamePanel.tileSize;
    }



     /** 
     * Método raíz para chamar outros métodos que modificarão
     * as propriedades do objeto.
     */

    public abstract void update();

    /**
     * Método para alterar o estado da entidade baseado no padrão State Design, 
     * onde teremos o fluxo de ações (entrada, mudança e saída) para cada
     * estado
    */

    @Override
    public Entity clone()
    {
        return null;
    }

    @Override
    public String toString() {
        return 
        
        "\tEntity type\t= "   + entityType    + "\n" +
        "\tCoordinate\t= "    + coordinate + "\n" +
        "\tSpeed\t\t= "       + speed         + "\n" +
        "\tState\t\t= "       + state         + "\n" +
        "\t" + status;
    }
}
