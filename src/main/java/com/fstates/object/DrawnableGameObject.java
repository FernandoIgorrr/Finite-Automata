package com.fstates.object;

import com.fstates.library.ActionType;
import com.fstates.game.GamePanel;
import com.fstates.library.Coordinate;

import java.awt.*;
import java.util.Map;

public abstract  class DrawnableGameObject implements GameObject{
    public          GamePanel           gamePanel;

    protected       Coordinate          coordinate;
    protected Map<ActionType, Image>    actionsSprites;
    protected       Image               currentSprite;
    protected       int                 spriteCount = 0;
    protected       int                 spriteNum   = 1;
    protected       boolean             collision = false;

    protected DrawnableGameObject()
    {
        loadSprites();
    }

    protected  DrawnableGameObject(GamePanel gamePanel)
    {
        this();
        this.gamePanel = gamePanel;
    }

    protected DrawnableGameObject(GamePanel gamePanel, Coordinate coordinate)
    {
        this(gamePanel);
        this.coordinate = coordinate;
    }

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    // Setters e Getter do atributo coordinates
    public Coordinate getCoordinates()
    {
        return coordinate;
    }
    public void setCoordinates(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
    public void setCoordinates(int x,int y)
    {
        coordinate.setPair(x, y);
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

    // Transforma a row e a column da matriz de tiles em coordenadas

    public Coordinate tileToCoordinate(int row, int column)
    {
        return new Coordinate(column * gamePanel.tileSize, row * gamePanel.tileSize);
    }
    public Coordinate CoordinateToTile(int x,int y)
    {
        return new Coordinate(
                x / gamePanel.tileSize,
                y / gamePanel.tileSize
        );
    }
    // Desenha a entity na tela
    public void draw(Graphics2D g)
    {
        g.drawImage(currentSprite, getX(), getY(), gamePanel.tileSize, gamePanel.tileSize, null);
    }
    public abstract void loadSprites();

    public boolean collide()
    {
        return collision;
    }

    public void collideOff()
    {
        collision = false;
    }
}
