package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.object.DrawnableGameObject;
import com.fstates.object.Place;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Tile extends DrawnableGameObject
{
    protected Area colisionArea;
    protected final Place PLACE = Place.STANDARD;

    public TileType tileType;

    protected Coordinate coordinate;
    protected Tile(GamePanel gamePanel){
        super(gamePanel);
    }

    public Tile clone()
    {
        return null;
    }

    public void setCurrentSprite(String path) throws IOException {
        currentSprite = ImageIO.read(new File(path));
    }

    public void setCurrentSprite(Image sprite)
    {
        currentSprite = sprite;
    }

    public void setCollide(Boolean collision)
    {
        this.collision = collision;
    }
}
