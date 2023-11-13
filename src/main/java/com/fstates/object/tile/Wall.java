package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.Coordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Wall extends Tile {

    public Wall(GamePanel gamePanel)
    {
        super(gamePanel);
        collision =   true;
        tileType = TileType.WALL;

    }

    @Override
    public void loadSprites()
    {
        actionsSprites = null;

        try {
            currentSprite = ImageIO.read(new File("assets/tiles/wall.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public Tile clone()
    {
        Wall w = new Wall(gamePanel);
        w.setCurrentSprite(currentSprite);
        w.setCollide(collision);
        return w;
    }
}
