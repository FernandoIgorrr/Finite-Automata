package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.Coordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grass extends Tile{
    Image otherSprite;
    Random random;
    List<Boolean> bools;
    public Grass(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    protected void drawPhase(Graphics2D g) {

        int i = 0;

        for(Coordinate c : coordinates)
        {
            if(bools.get(i))
            {
                g.drawImage(currentSprite, c.getX(), c.getY(), gamePanel.tileSize, gamePanel.tileSize, null);

            }
            else
            {
                g.drawImage(otherSprite, c.getX(), c.getY(), gamePanel.tileSize, gamePanel.tileSize, null);

            }
            i++;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        drawPhase(g);
    }

    @Override
    public void loadSprites() {
        actionsSprites = null;

        try {
            currentSprite   = ImageIO.read(new File("assets/tiles/grass00.png"));
            otherSprite      = ImageIO.read(new File("assets/tiles/grass01.png"));
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
        bools = new ArrayList<>();

        random = new Random();

        for (int i = 1; i < gamePanel.maxScreenRow - 1; i++)
        {
            for(int j = 1; j < gamePanel.maxScreenCol - 1; j++)
            {
                coordinates.add(tileToCoordinate(i,j));
                bools.add(random.nextBoolean());
                gamePanel.tileMap.getMap()[i][j] = 'g';
            }
        }
    }
}
