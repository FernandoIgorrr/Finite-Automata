package com.fstates;

import javax.swing.JFrame;

import com.fstates.game.GamePanel;

public class App 
{
    public static void main( String[] args )
    {
        //System.out.println(new Coordinates(30, 30));

        // System.out.println(Player.getInstance("Fernando",
        // new Coordinates(30, 30)));

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("whatever");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
