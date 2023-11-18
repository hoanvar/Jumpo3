/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Tool.Tool;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;

/**
 *
 * @author FPT-SHOP
 */
public class Slime extends Entity{
    private boolean triggerOn;
    private boolean seActivated;
    private int routineCount;
    public Slime(GamePanel gamePanel,int initMap,int mapRow,int mapCol){
        super(gamePanel);
        this.sound = gamePanel.sound;
        this.initMap = initMap;
        mapX = mapRow * gamePanel.tileSize;
        mapY = mapCol * gamePanel.tileSize - 20;        
        
        this.initMap = initMap;
        triggerOn = false;
        seActivated = false;
        routineCount=0;

        solidArea = new Rectangle(12, 28, 14, 14);
        solidAreaDefaultX = 12;
        solidAreaDefaultY = 28;

        spriteCounter=0;
        spriteNum = 0;
        // Idle
        left  = new BufferedImage[8];
        // Jumped
        right = new BufferedImage[7];
        getImage("/SlimeImage/");
    }
    @Override
    public void update(){
        if(!triggerOn){
            delay();
        }else{
            delay2();
        }
    }
    @Override
    protected void delay(){
//        System.out.println("delay");
        spriteCounter++;
        if(spriteCounter > 20){
            if(spriteNum < 7)
                spriteNum++;
            else
                spriteNum = 0;
            spriteCounter = 0;
        }
    }
    private void delay2(){
//        System.out.println(spriteNum);

        spriteCounter++;
        if(spriteCounter > 3){
            if(spriteNum < 6){
                spriteNum++;
                routineCount++; 
            }else{
                spriteNum = 0;
            }
            spriteCounter = 0;
            if(routineCount > 6){
                routineCount = 0;
                triggerOn = false;
                seActivated = false;
            }
        }
    }
    public void trigger(){
        triggerOn = true;
        spriteCounter = 0;
        spriteNum = 0;
        // Slime SE
        if(!seActivated){
            sound.playSE(5);
            seActivated = true;
        }
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image;
        if(!triggerOn){
            image = left[spriteNum];
        }else{
            image = right[spriteNum];
        }
        g2.drawImage(image, mapX, mapY+2,  null);
    }
    @Override
    protected void getImage(String path){
        try{
            for(int i=0 ; i<=7 ; i++){
                left[i] = ImageIO.read(getClass().getResourceAsStream(path + i +".png"));
                left[i] = Tool.scaleImage(left[i], gamePanel.playerSize , gamePanel.playerSize );
            }
            
            for(int i=0 ; i<=6 ; i++){
                right[i] = ImageIO.read(getClass().getResourceAsStream(path + i +"_jumped.png"));
                right[i] = Tool.scaleImage(right[i], gamePanel.playerSize , gamePanel.playerSize );
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public int getInitMap(){
        return initMap;
    }
}
