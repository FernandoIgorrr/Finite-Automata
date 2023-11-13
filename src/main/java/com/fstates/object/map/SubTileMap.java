package com.fstates.object.map;

import com.fstates.library.Area;
import com.fstates.library.Coordinate;
import com.fstates.object.GameObject;

import java.awt.*;

public class SubTileMap implements GameObject
{
    int num;

   public Area area;

   public SubTileMap(int num, Area area)
   {
       this.num = num;
       this.area = area;
   }

    public boolean itsInside(Coordinate coordinate){
        if(coordinate.getX() > this.area.finalX || coordinate.getX() < this.area.initialX){
            return false;
        }
        if(coordinate.getY() > this.area.finalY || coordinate.getY() < this.area.initialY){
            return false;
        }
        return true;
    }

    public Coordinate getCenter(){

        int centerX = (this.area.finalX + this.area.initialX) / 2;
        int centerY = (this.area.initialY + this.area.finalY) / 2;

        return new Coordinate(centerX,centerY);
    }

    public int getNum() {
        return num;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update()
    {

    }
}
