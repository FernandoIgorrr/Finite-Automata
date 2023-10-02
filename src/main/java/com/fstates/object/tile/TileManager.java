package com.fstates.object.tile;

import com.fstates.game.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class TileManager
{
    GamePanel gamePanel;
    List<Tile> tiles;

    public TileManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;

        tiles = new ArrayList<>();
    }
}
