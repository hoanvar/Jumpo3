/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Tool.Tool;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;
import tool.RaysEffect;


public class PaperRoll extends Entity{
        public PaperRoll(GamePanel gamePanel){
            super(gamePanel);
            initMap = 14;
            this.sound = gamePanel.sound;
            mapX = 3 * gamePanel.tileSize;
            mapY = 3 * gamePanel.tileSize;
            left = new BufferedImage[1];

            triggerOn = false;
            seActivated = false;
            
            getImage("/ChestImage/");
    }
        
        
    protected void getImage(String path){
        try{
                left[0] = ImageIO.read(getClass().getResourceAsStream(path +"paper2.png"));
                left[0] = Tool.scaleImage(left[0], gamePanel.playerSize -30, gamePanel.playerSize-30);
                left[0] = Tool.flipImage(left[0]);
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(){
        if(triggerOn){
            if(spriteCounter < 95){
               delay();
               if(spriteCounter > 70)
               mapY--;
            }else{
                gamePanel.raysEffect.trigger();
            }
        }
    }
    public void trigger(){
        triggerOn = true;
        // Paper SE
        if(!seActivated){
            sound.playSE(7);
            seActivated = true;
        }
    }
    @Override
    public void draw(Graphics2D g2){
        if(triggerOn && spriteCounter > 70){
            BufferedImage image = left[0];
            g2.drawImage(image, mapX+14, mapY+2,  null);
        }
    }
    @Override
    protected  void delay(){
        spriteCounter++;
            
    }
}

