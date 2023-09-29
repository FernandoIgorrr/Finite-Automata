package com.fstates.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.fstates.library.Direction;
import com.fstates.library.KeyHandler;
import com.fstates.object.entity.Player;

public class GamePanel extends JPanel implements Runnable{
    
    //configurações de tela
    private final int originalTileSize  = 16; // 16x16
    private final int scale             = 2;

    private final int tileSize          = originalTileSize * scale; // 32x32 tile
    private int maxScreenCol            = 42;
    private int maxScreenRow            = 24;
    private final int screenWidth       = tileSize * maxScreenCol; // 1280 pixels
    private final int screenHight       = tileSize * maxScreenRow; // 720 pixels

    // criação da Thread
    private Thread gameThread;

    int FPS = 60;

    //configurações do player
    Player player                       = Player.getInstance("ykky",640,310);

    // configuração dos comandos
    //KeyHandler keyHandler               = new KeyHandler();

    public GamePanel(){

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
        
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
     
       player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(player.getX(), player.getY(), tileSize, tileSize);
        g2.dispose();
    }

    @Override
    public void run() {

        double drawnInteval = 1000000000/FPS; // 0.01666666 seconds
        double delta        = 0;
        double lastTime     = System.nanoTime();
        double currentTime;
        long timer          = 0;
        int drawCount       = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime-lastTime) / drawnInteval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;


            if(delta >= 1){
            
                // Atualiza a informação, como por exemplo as coordenadas do player
                update();
                //System.out.println(player);
                // Redesenha a tela com a informação atualizada
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
