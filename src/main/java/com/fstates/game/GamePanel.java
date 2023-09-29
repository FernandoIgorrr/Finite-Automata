package com.fstates.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.fstates.library.factory.EntityFactory;
import com.fstates.library.factory.FactoryProvider;
import com.fstates.library.factory.FactoryType;
import com.fstates.object.entity.Enemy;
import com.fstates.object.entity.EntityType;
import com.fstates.object.entity.Player;

public class GamePanel extends JPanel implements Runnable{
    
    //configurações de tela
    private final int originalTileSize  = 16; // 16x16
    private final int scale             = 2;

    public final int tileSize          = originalTileSize * scale; // 32x32 tile
    
    private int maxScreenCol            = 42;
    private int maxScreenRow            = 24;
    private final int screenWidth       = tileSize * maxScreenCol; // 1280 pixels
    private final int screenHight       = tileSize * maxScreenRow; // 720 pixels

    // criação da Thread
    private Thread gameThread;

    int FPS = 60;

    //configurações do player
    Player player                       = Player.getInstance("ykky",this,640,310);

    //fábrica para produzir entities
    EntityFactory entityFactory = (EntityFactory) FactoryProvider.getInstance().
                                    getFactory(FactoryType.ENTITY);

    //configurações dos inimigos
    List<Enemy> enemies = new ArrayList<>();

    public GamePanel(){

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);

//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
//        enemies.add((Enemy)entityFactory.create(EntityType.ENEMY));
        
    }
    
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update()
    {
       player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }

    @Override
    public void run() {

        double  drawnInterval = (double) 1000000000 / FPS; // 0.01666666 seconds
        double  delta        = 0;
        double  lastTime     = System.nanoTime();
        double  currentTime;
        long    timer        = 0;
        int     drawCount    = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawnInterval;
            timer += (long) (currentTime - lastTime);
            lastTime = currentTime;


            if(delta >= 1){
            
                // Atualiza a informação, como por exemplo as coordenadas do player
                update();
               
                //Redesenha a tela com a informação atualizada
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
