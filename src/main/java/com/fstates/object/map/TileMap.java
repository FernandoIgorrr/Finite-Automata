package com.fstates.object.map;

import com.fstates.game.GamePanel;
import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.object.GameObject;

import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TileMap implements GameObject {

    private Character[][] map;
    private List<SubTileMap> subTileMaps;

    private Map<Character,Boolean> tileColision;

    public TileMap(GamePanel gamePanel)
    {
        tileColision = new HashMap<>();
        tileColision.put('W',true);
        tileColision.put('G',false);
        map = new Character[gamePanel.maxScreenRow][gamePanel.maxScreenCol];

        subMapsGenerator(gamePanel.screenWidth, gamePanel.screenHight);
    }

    public Character[][] getMap(){
        return map;
    }

    public List<SubTileMap> getSubTileMaps()
    {
        return subTileMaps;
    }

    public Map getTileColision()
    {
        return tileColision;
    }

    @Override
    public void draw(Graphics2D g)
    {

    }

    @Override
    public void update() {

    }

    public int subMapFinder(Coordinate coordinate){

        for (SubTileMap subTileMap: subTileMaps) {
            if(subTileMap.itsInside(coordinate)){
                return  subTileMap.getNum();
            }
        }

        return -1;
    }

    private void subMapsGenerator(int screenWidth,int screenHeight){
        subTileMaps = new ArrayList();
        int c = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                Integer pix  = (j*screenWidth)/3;
                Integer piy  = (i*screenHeight)/3;
                Integer pfx  = pix + screenWidth/3;
                Integer pfy  = piy + screenHeight/3;

                subTileMaps.add(new SubTileMap(c++, new Area(pix,piy,pfx,pfy)));
            }
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < map.length; i++) {
            str+= (i +" - ");
            for (int j = 0; j < map[i].length; j++) {
                str+=(map[i][j] + " ");
            }
            str+=("\n");
        }
        return str;
    }
}
