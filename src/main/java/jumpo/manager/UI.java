/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jumpo.Manager;

import Tool.Tool;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import state.About;
import state.EndState;
import state.Intro;
import state.Menu;
import state.Tutorial;
import state.Record;

public class UI {
    private GamePanel gamePanel;
    private Font arial_40;
    private BufferedImage menuImage;
    private Graphics2D g;
    public Menu menu;
    public About about;
    public Tutorial tutorial;
    public Record record;
    public EndState end;
    public Intro intro;
    
    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        menu = new Menu(gamePanel);
        about = new About(gamePanel);
        tutorial = new Tutorial(gamePanel);
        record = new Record(gamePanel);
        end = new EndState(gamePanel);
        intro = new Intro(gamePanel);
        arial_40 = new Font("ARIAL", Font.PLAIN, 40);
        try {
            menuImage = ImageIO.read(getClass().getResourceAsStream("/UIImage/menu.png"));
            menuImage = Tool.scaleImage(menuImage, gamePanel.tileSize*gamePanel.maxScreenCol, gamePanel.tileSize*gamePanel.maxScreenRow);        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        g = g2;
        g.setFont(arial_40);
        g.drawImage(menuImage, 0, 0,null);
        
        if(gamePanel.gameState == gamePanel.menuState){
            menu.draw(g2);
        }
        if(gamePanel.gameState == gamePanel.aboutState){
            about.draw(g2);
        }
        if(gamePanel.gameState == gamePanel.tutorialState){
            tutorial.draw(g2);
        }
        if(gamePanel.gameState == gamePanel.recordState){
            record.draw(g2);
        }
        if(gamePanel.gameState == gamePanel.endState){
            end.draw(g2);
        }
        if(gamePanel.gameState == gamePanel.introState){
            intro.draw(g2);
            if(intro.finish()){
                gamePanel.gameState = gamePanel.menuState;
                gamePanel.sound.playMusic();
            }
        }
    }
    public void drawPause(Graphics2D g2){
        if(gamePanel.gameState == gamePanel.pauseState){
            g= g2;
            String text = "PAUSE";
            g.setFont(arial_40);
            g.setColor(Color.white);
            int x = getXCenter(text);
            int y = gamePanel.screenHeight/2;

            g.drawString(text, x, y);
        }
    }
    private int getXCenter(String text){
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        return gamePanel.screenWidth/2 - length/2;
        
    }
}
