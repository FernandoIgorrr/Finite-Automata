package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.Coordinate;
import com.fstates.library.GameRNG;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grass extends Tile{
    Random random;
    List<Boolean> bools;
    public Grass(GamePanel gamePanel) {
        super(gamePanel);
        tileType = TileType.GRASS;
    }

    @Override
    public void loadSprites() {
        actionsSprites = null;

        List<String>paths = new ArrayList<>();
        paths.add("assets/tiles/grass00.png");
        paths.add("assets/tiles/grass01.png");

        try {
            currentSprite   = ImageIO.read(new File(paths.get(GameRNG.zeroOrOne())));
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
        return new Grass(gamePanel);
    }
}
