package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.GameRNG;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tree extends Tile
{
    public Tree(GamePanel gamePanel) {
        super(gamePanel);
        collision = true;
    }

    @Override
    public void loadSprites() {
        actionsSprites = null;

        try {
            currentSprite = ImageIO.read(new File("assets/tiles/tree.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    public Tile clone()
    {
        Tree t = new Tree(gamePanel);
        t.setCurrentSprite(currentSprite);
        t.setCollide(collision);
        return t;
    }
}
