package com.fstates.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import com.fstates.library.CollisionChecker;
import com.fstates.library.Coordinate;
import com.fstates.library.factory.EntityFactory;
import com.fstates.library.factory.FactoryProvider;
import com.fstates.library.factory.FactoryType;
import com.fstates.object.entity.Enemy;
import com.fstates.object.entity.EntityType;
import com.fstates.object.entity.Player;
import com.fstates.object.item.Axe;
import com.fstates.object.item.Boots;
import com.fstates.object.item.Coin;
import com.fstates.object.item.Item;
import com.fstates.object.map.SubTileMap;
import com.fstates.object.map.TileMap;
import com.fstates.object.tile.Grass;
import com.fstates.object.tile.Wall;

public class GamePanel extends JPanel implements Runnable{
    
    //configurações de tela
    private final int originalTileSize  = 16; // 16x16
    private final int scale             = 2;

    public final int tileSize          = originalTileSize * scale; // 32x32 tile

    public int maxScreenCol            = 40;
    public int maxScreenRow            = 22;
    public final int screenWidth       = tileSize * maxScreenCol; // 1280 pixels
    public final int screenHight       = tileSize * maxScreenRow; // 720 pixels

    // criação da Thread
    private Thread gameThread;

    int FPS = 60;

    //Mapa de tiles
    public TileMap tileMap = new TileMap(this);

    //configurações do player
    Player player                       = Player.getInstance("ykky",this,640,310);

    //fábrica para produzir entities
    EntityFactory entityFactory = (EntityFactory) FactoryProvider.getInstance().getFactory(FactoryType.ENTITY);

    //configurações dos inimigos
    Deque<Enemy> enemies;

    //configuração dos tiles
    //wall
    Wall wall = new Wall(this);
    Grass grass = new Grass(this);

    //configuração de colisao
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    //configuração de itens

    public List<Item> itens = new ArrayList<>();


    public GamePanel()
    {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);

        enemies = new ArrayDeque<>();
        for(SubTileMap subTileMap: tileMap.getSubTileMaps())
        {
            if(subTileMap.getNum() != 4)
            {
                System.out.println("NUM: " + subTileMap.getNum() + " | CENTER: " + subTileMap.getCenter());
                Enemy enemy = (Enemy) entityFactory.create(EntityType.ENEMY);
                Coordinate coord;
                if(subTileMap.getNum() < 5)
                {
                    if(subTileMap.getNum() == 1){
                        coord = new Coordinate(subTileMap.getCenter().getX(), subTileMap.getCenter().getY() - 70);
                    }
                    else {
                        coord = new Coordinate(subTileMap.getCenter().getX(), subTileMap.getCenter().getY() + 70);
                    }
                }
                else
                {

                    coord = new Coordinate(subTileMap.getCenter().getX(), subTileMap.getCenter().getY()-70);
                }
                enemy.setCoordinates(coord);
                enemy.setPatrolCoord(subTileMap.getCenter());
                if(subTileMap.getNum() >= 6){
                    enemy.limitPatrol.finalY = enemy.limitPatrol.finalY - 30;
                }
                enemy.setGamePanel(this);

                if(subTileMap.getNum() == 0 ||subTileMap.getNum() == 8){
                    itens.add(new Boots(this,subTileMap.getCenter(),subTileMap.getNum()));

                }
                else{
                    itens.add(new Coin(this,subTileMap.getCenter(),subTileMap.getNum()));
                }
                enemies.add(enemy);
            }
        }
    }

    public boolean checkIteminSubMap(int num){
        for (Item item : itens) {
            if(item.submap == num){
                return true;
            }
        }
        return false;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update()
    {
        for (Enemy enemy : enemies) {
            //System.out.println("********* " + enemy.getCoordinates());
            enemy.update();
            if(enemy.collided(Player.getInstance()))
            {
                gameThread = null;
                JOptionPane.showMessageDialog(null,"VOCÊ PERDEU!!!");

            }
        }
        //enemies.getFirst().update();


        Item itemToRemove = null;
        for (Item item : itens) {
            //System.out.println("********* " + enemy.getCoordinates());
            item.update();
            if(item.collided(Player.getInstance())){
                Player.getInstance().powerUp(item);
                itemToRemove = item;
            }
        }

        itens.remove(itemToRemove);
        player.update();

        if(itens.isEmpty()){
            gameThread = null;
            JOptionPane.showMessageDialog(null,"VOCÊ VENCEU!!!");
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        grass.draw(g2);
        wall.draw(g2);
//        for (int i = 0; i < maxScreenRow; i++) {
//            for (int j = 0; j < maxScreenCol; j++) {
//                g2.setColor(Color.BLACK);
//                g2.fillRect(j * tileSize,i * tileSize,tileSize,2);
//                g2.fillRect(j * tileSize,i * tileSize,2, tileSize);
//            }
//        }
//
        for (Enemy enemy : enemies) {
            enemy.draw(g2);
        }
        //enemies.getFirst().draw(g2);


        for (Item item : itens) {
            item.draw(g2);
        }

        //System.out.println(enemies.getFirst().getCoordinates());

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
