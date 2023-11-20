/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Tool.Tool;
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
public class Chest extends Entity{
    public Chest(GamePanel gamePanel){
        super(gamePanel);
        initMap = 14;
        this.sound = gamePanel.sound;
        mapX = 3 * gamePanel.tileSize;
        mapY = 3 * gamePanel.tileSize;
        left = new BufferedImage[10];
        
        solidArea = new Rectangle(8, 16, 32, 28);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;
        
        triggerOn = false;
        seActivated = false;
        
        getImage("/ChestImage/");
    }
    @Override
    protected void getImage(String path){
        try{
            for(int i=0 ; i<=9 ; i++){
                left[i] = ImageIO.read(getClass().getResourceAsStream(path +i+".png"));
                left[i] = Tool.scaleImage(left[i], gamePanel.playerSize -10, gamePanel.playerSize-20);
                left[i] = Tool.flipImage(left[i]);
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void trigger(){
        if(!triggerOn){
            triggerOn = true;
            gamePanel.paperRoll.trigger();
            // Chest SE
            sound.playSE(7);
        }
    }
    @Override
    public void update(){
        if(triggerOn){
            delay();
        }
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image ;
        if(!triggerOn){
            image = left[0];
        }else{
            image = left[spriteNum];
        }
        g2.drawImage(image, mapX, mapY+2,  null);
    }
    @Override
    protected  void delay(){
        spriteCounter++;
            if(spriteCounter > 5 ){
                if(spriteNum < 9){
                    spriteNum ++;
                }else{
//                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
    }
    
}
