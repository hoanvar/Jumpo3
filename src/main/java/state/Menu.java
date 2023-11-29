/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

import Tool.Tool;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;


public class Menu extends State{
    private int commandNum;
    private BufferedImage image,image2;
    private BufferedImage musicOn,musicOff;

    public Menu(GamePanel gamePanel){
        super(gamePanel);
        commandNum = 0;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/UIImage/jumpo.png"));
            image = Tool.scaleImage(image, gamePanel.tileSize*38, gamePanel.tileSize*14); 
            image2 = ImageIO.read(getClass().getResourceAsStream("/UIImage/jumpo2.png"));
            image2 = Tool.scaleImage(image2, gamePanel.tileSize*38, gamePanel.tileSize*14);
            
            musicOn = ImageIO.read(getClass().getResourceAsStream("/UIImage/music_on.png"));
            musicOn = Tool.scaleImage(musicOn, gamePanel.tileSize, gamePanel.tileSize); 
            
            musicOff = ImageIO.read(getClass().getResourceAsStream("/UIImage/music_off.png"));
            musicOff = Tool.scaleImage(musicOff, gamePanel.tileSize, gamePanel.tileSize); 

        }catch(IOException e){
            e.printStackTrace();
        }
    }
     public void draw(Graphics2D g){
        
        g.setFont(arial_40);
        String text ;
        int x ;
        int y = gamePanel.screenHeight/2 - 70;

        g.drawImage(image2, -197, 103,null);
        g.drawImage(image, -200, 100,null);
        if(gamePanel.keyBoardInput.isMCode()){
            g.drawImage(musicOn, 670, 20,null);
        }
        else{
            g.drawImage(musicOff, 670, 20,null);
        }
        // Button
        g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
        text = "Play";
        x = getXCenter(text,g);
        y += gamePanel.tileSize*3;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(new Color(153,153,153));
        g.drawString(text, x, y);
        if(commandNum == 0 ){
            g.setColor(Color.red);
            g.drawString(text, x, y);
            g.setColor(new Color(153,153,153));
        }
        text = "Tutorial";
        x = getXCenter(text,g);
        y += gamePanel.tileSize*2;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(new Color(153,153,153));
        g.drawString(text, x, y);
        if(commandNum == 1 ){
            g.setColor(Color.red);
            g.drawString(text, x, y);
            g.setColor(new Color(153,153,153));

        }
        text = "Record";
        x = getXCenter(text,g);
        y += gamePanel.tileSize*2;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(new Color(153,153,153));
        g.drawString(text, x, y);
        if(commandNum == 2 ){
            g.setColor(Color.red);
            g.drawString(text, x, y);
            g.setColor(new Color(153,153,153));
        }
        text = "About";
        x = getXCenter(text,g);
        y += gamePanel.tileSize*2;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(new Color(153,153,153));
        g.drawString(text, x, y);
        if(commandNum == 3 ){
            g.setColor(Color.red);
            g.drawString(text, x, y);
            g.setColor(new Color(153,153,153));
        }
    }
   
    public void commandUp(){
         commandNum--;
        if(commandNum < 0){
           commandNum = 3;
        }
    }
    public void commandDown(){
        commandNum++;
        if(commandNum > 3){
            commandNum = 0;
        }
    }
    public int getCommandNum(){
        return commandNum;
    }
}
