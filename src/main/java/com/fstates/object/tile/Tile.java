package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.object.DrawnableGameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile extends DrawnableGameObject {

    //protected Area colisionArea;
    protected List<Coordinate> coordinates;
    protected List<Area> collisionsArea;
    protected Tile(GamePanel gamePanel){
        super(gamePanel);
        fillCoordinates();
    }

    protected abstract void drawPhase(Graphics2D g);
    protected abstract void fillCoordinates();

    protected void fillColisionArea()
    {
        collisionsArea = new ArrayList<>();
        for (Coordinate coordinate: coordinates) {
            collisionsArea.add(new Area( coordinate.getX(),
                                        coordinate.getY(),
                                    coordinate.getX() + gamePanel.tileSize,
                                    coordinate.getY() + gamePanel.tileSize));
        }
    }

}
