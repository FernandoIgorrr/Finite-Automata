package com.fstates.object.item;

import com.fstates.game.GamePanel;
import com.fstates.library.Coordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Axe extends Item{
    public Axe(GamePanel gamePanel, Coordinate coordinate, int submap) {
        super(gamePanel, coordinate, submap);
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(collisionArea.initialX,collisionArea.initialY, collisionArea.finalX - collisionArea.initialX, collisionArea.finalY - collisionArea.initialY);

        g.drawImage(currentSprite, getX(), getY(), gamePanel.tileSize, gamePanel.tileSize, null);
    }

    @Override
    public void loadSprites() {
        actionsSprites = null;

        try {
            currentSprite = ImageIO.read(new File("assets/itens/axe.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }
}
