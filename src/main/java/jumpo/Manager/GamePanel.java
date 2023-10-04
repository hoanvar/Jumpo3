
package jumpo.Manager;

import Entity.Player;
import InPut.KeyBoardInPut;
import Tile.BackGround;
import Tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTING
//    public final int originalTileSize = 16;
//    public final int scale = 3;
    public final int tileSize = 48;
    public final int subTileScale = 4;
    public final int subTileSize = tileSize/subTileScale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 12;
    public final int maxSubScreenCol = maxScreenCol * subTileSize;
    public final int maxSubScreenRow = maxScreenRow * subTileSize;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
//    //WORLD SETTING
//    public final int maxWorldCol = 50;
//    public final int maxWorldRow = 50;
//    public final int worldWidth = maxWorldCol * tileSize;
//    public final int worldHeight = maxWorldRow * tileSize;
    
    // FPS
    public static final int FPS = 60;
    
    KeyBoardInPut keyBoardInPut = new KeyBoardInPut();
    public Player player = new Player(this,keyBoardInPut);
    BackGround backGround = new BackGround();
    TileManager tileManager = new TileManager(this);
    Thread gameThread;   
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyBoardInPut);
        this.setFocusable(true);
    }

    public void  startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1e9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
           //DISPLAY FPS
            if(timer >= 1e9){
//                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }
    
    public void update(){
        player.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        backGround.draw(g2);
        tileManager.draw(g2);
        player.draw(g2);
        
        
        g2.dispose();
    }
}
