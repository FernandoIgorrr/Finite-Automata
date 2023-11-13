package com.fstates.object.entity;

import com.fstates.automata.State;
import com.fstates.game.GamePanel;
import com.fstates.library.*;
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
    protected       Area                collisionArea;

    public Collision getCollision() {
        return collision;
    }

    protected       Collision           collision = new Collision(false,null);
    protected       int                 speedDiagonal;


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
        this.collision.collisionOn = true;
        this.entityType     = entityType;
        status              = new Status();
    }

    protected Entity(EntityType  entityType, GamePanel gamePanel, int speed)
    {
        this(entityType, gamePanel);
        this.speed          = speed;
        speedDiagonal = (int) Math.ceil(((double)speed)/2);
    }

    protected Entity(EntityType  entityType, GamePanel gamePanel, Coordinate coordinate, int speed)
    {
        super(gamePanel, coordinate);
        collisionArea   = new Area();
        //setCollisionArea();
        this.entityType = entityType;
        this.speed      = speed;
        speedDiagonal = (int) Math.ceil(((double)speed)/2);

    }

    protected Entity(EntityType  entityType, GamePanel gamePanel, State state, Coordinate coordinate, int speed){
        this(entityType, gamePanel, coordinate,speed);
        setCollisionArea();
        this.state = state;
    }

    protected Entity(Entity entity)
    {
        status              = entity.getStatus();            
        coordinate          = entity.getCoordinates();
        entityType          = entity.getEntityType();
        speed               = entity.getSpeed();
    }
    // Fim dos construtores

    // *****************************************************************

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //******************************************************************

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
        speedDiagonal = (int) Math.ceil(((double)speed)/2);
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
    public Collision isCollisionOn() {
        return collision;
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
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
            collision.collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            if(!collision.collisionOn) {
                switch (direction) {
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
                        setY(getY() - speedDiagonal);
                        setX(getX() + speedDiagonal);
                        break;
                    case NORTH_WEST:
                        setY(getY() - speedDiagonal);
                        setX(getX() - speedDiagonal);
                        break;
                    case SOUTH_EAST:
                        setY(getY() + speedDiagonal);
                        setX(getX() + speedDiagonal);
                        break;
                    case SOUTH_WEST:
                        setY(getY() + speedDiagonal);
                        setX(getX() - speedDiagonal);
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
