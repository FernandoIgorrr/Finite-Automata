package com.fstates.object.tile;

import com.fstates.game.GamePanel;
import com.fstates.library.factory.FactoryProvider;
import com.fstates.library.factory.FactoryType;
import com.fstates.library.factory.TileFactory;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class TileManager
{
    private GamePanel gamePanel;
    private Tile [][] tiles;
    private Map<Character,Tile> characterTileMap;

    private TileFactory tileFactory = (TileFactory) FactoryProvider.getInstance().getFactory(FactoryType.TILE);

    public TileManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;

        tiles = new Tile[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        try {
            characterTileMap = fillCharacterTileMap();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Tile[][] getTiles()
    {
        return tiles;
    }

    public void loadMap(String path) throws IOException
    {

        FileReader charMap = new FileReader(path);
        BufferedReader br = new BufferedReader(charMap);

        for (int i = 0; i < gamePanel.maxScreenRow; i++)
        {
            String line = br.readLine();
            for(int j = 0; j < line.length();j++)
            {
                tiles[i][j] = characterTileMap.get(line.charAt(j)).clone();
                tiles[i][j].setGamePanel(gamePanel);
                tiles[i][j].setCoordinates(tiles[i][j].tileToCoordinate(i,j));
            }
        }
    }

    public void drawnTiles(Graphics2D g2)
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                tiles[i][j].draw(g2);

              // para ver os tiles separados no game
                g2.setColor(Color.BLACK);
                g2.fillRect(j * gamePanel.tileSize,i * gamePanel.tileSize,gamePanel.tileSize,1);
                g2.fillRect(j * gamePanel.tileSize,i * gamePanel.tileSize,1, gamePanel.tileSize);
            }
        }
    }

    private Map fillCharacterTileMap() throws IOException {
        Map<Character,Tile> characterTileMap_ = new HashMap<>();

        characterTileMap_.put('W', (Wall)tileFactory.create(TileType.WALL));
        characterTileMap_.put('w', (Wall)tileFactory.create(TileType.WALL));
        characterTileMap_.get('w').collideOff();

        characterTileMap_.put('L', (Water)tileFactory.create(TileType.WATER));

        characterTileMap_.put('G', (Grass)tileFactory.create(TileType.GRASS));

        characterTileMap_.put('T', (Tree)tileFactory.create(TileType.TREE));
        characterTileMap_.put('t', (Tree)tileFactory.create(TileType.TREE));
        characterTileMap_.get('t').collideOff();


        characterTileMap_.put('[', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('[').setCurrentSprite(Water.characterPathMap.get('['));

        characterTileMap_.put(']', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get(']').setCurrentSprite(Water.characterPathMap.get(']'));

        characterTileMap_.put('{', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('{').setCurrentSprite(Water.characterPathMap.get('{'));

        characterTileMap_.put('}', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('}').setCurrentSprite(Water.characterPathMap.get('}'));

        characterTileMap_.put('-', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('-').setCurrentSprite(Water.characterPathMap.get('-'));

        characterTileMap_.put('_', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('_').setCurrentSprite(Water.characterPathMap.get('_'));

        characterTileMap_.put('/', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('/').setCurrentSprite(Water.characterPathMap.get('/'));

        characterTileMap_.put('|', (Water)tileFactory.create(TileType.WATER));
        characterTileMap_.get('|').setCurrentSprite(Water.characterPathMap.get('|'));

        return characterTileMap_;
    }
}
