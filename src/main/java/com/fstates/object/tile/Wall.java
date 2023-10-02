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
    }

    @Override
    protected void drawPhase(Graphics2D g) {
        for(Coordinate c : coordinates)
        {
            g.drawImage(currentSprite, c.getX(), c.getY(), gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        drawPhase(g);
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
    protected void fillCoordinates() {
        coordinates = new ArrayList<>();

        for (int i = 0; i < gamePanel.maxScreenRow; i++)
        {
            for(int j = 0; j < gamePanel.maxScreenCol; j++)
            {
                if(i == 0 || j == 0 || i >= gamePanel.maxScreenRow -1 || j >= gamePanel.maxScreenCol - 1)
                {
                    coordinates.add(tileToCoordinate(i,j));
                    gamePanel.tileMap.getMap()[i][j] = 'w';
                }
            }
        }
    }
}
