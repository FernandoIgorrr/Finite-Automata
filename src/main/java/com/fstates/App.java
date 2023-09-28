package com.fstates;

import com.fstates.library.Coordinates;
import com.fstates.object.entity.Player;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println(new Coordinates(30, 30));

        System.out.println(Player.getInstance("Fernando",
        new Coordinates(30, 30)));
    }
}
