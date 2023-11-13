package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.GameRNG;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Water extends Tile
{

    public static Map<Character,String> characterPathMap = new HashMap<Character,String>()
    {
        {
            put('[', "assets/tiles/water02.png");
            put('-', "assets/tiles/water03.png");
            put(']', "assets/tiles/water04.png");
            put('/', "assets/tiles/water05.png");
            put('|', "assets/tiles/water06.png");
            put('{', "assets/tiles/water07.png");
            put('_', "assets/tiles/water08.png");
            put('}', "assets/tiles/water09.png");
        }
    };


    public Water(GamePanel gamePanel)
    {
        super(gamePanel);
        collision =   true;
        tileType = TileType.WATER;

    }

    @Override
    public void loadSprites()
    {
        actionsSprites = null;

        List<String> paths = new ArrayList<>();
        paths.add("assets/tiles/water00.png");
        paths.add("assets/tiles/water01.png");

        try {
            currentSprite = ImageIO.read(new File(paths.get(GameRNG.zeroOrOne())));
            //currentSprite = ImageIO.read(new File(paths.get(0)));
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
        Water w = new Water(gamePanel);
        w.setCurrentSprite(currentSprite);
        w.setCollide(collision);
        return w;
    }
}
