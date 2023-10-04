
package Entity;


import InPut.KeyBoardInPut;
import Tool.Tool;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.awt.Graphics;
import java.io.File;
import jumpo.Manager.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyBoardInPut keyBoardInPut = new KeyBoardInPut();
    
    private int energy;
    private int jumpSpeed;
    private BufferedImage lastImage;
    private boolean isJumping;
    
    public Player (GamePanel gamePanel, KeyBoardInPut keyBoardInPut){
        this.gamePanel = gamePanel;
        this.keyBoardInPut = keyBoardInPut;
        setDefaultValue();
        getImage("/PlayerImage/");
        
    }   
   private void setDefaultValue(){
       mapX = 10 * gamePanel.tileSize;
       mapY = 10 * gamePanel.tileSize;
       direction = "right";
       energy = 0;
       entityWalkSpeed = 2;
       jumpSpeed = 3;
       isJumping = false;
   }
    private void getImage(String path){
       try{
            left = ImageIO.read(getClass().getResourceAsStream(path + "left.png"));
            right = ImageIO.read(getClass().getResourceAsStream(path + "right.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
       left = scaleImg(path + "left");
       right = scaleImg(path + "right");
       
   }
   private BufferedImage scaleImg(String path){
       BufferedImage image = null ;
       path = path + ".png";
       try{
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = Tool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
       }catch(IOException e){
           e.fillInStackTrace();
       }
       return image;
   }
   
    @Override
   public void update(){
       if(isJumping == false){
           if(keyBoardInPut.getSpacePressed() == true){
           if(energy < 60) {
                   energy++;
               }else{
               isJumping = true;
           }
            }else {
                if(keyBoardInPut.getLeftPressed() == true){
                   direction = "left";
                   mapX -= entityWalkSpeed;
               }
               if(keyBoardInPut.getRightPressed() == true){
                   direction = "right";
                   mapX += entityWalkSpeed;
               }
               if(keyBoardInPut.getUpPressed() == true){
                   direction = "up";
               }
           }
       }
           if(energy > 0 && keyBoardInPut.getSpacePressed() == false){
               isJumping = true;
           }
           if(isJumping == true ){
               switch(direction){
                   case "left":
                       mapX -= jumpSpeed;
                       break;
                   case "right":
                       mapX += entityWalkSpeed;
                       break;
               }
                   mapY -= jumpSpeed;
                   energy -= 1.5;
                   if(energy < 0){
                       energy = 0;
                       isJumping = false;
                   }
           }
       
        spriteCounter++;
            if(spriteCounter > 12 ){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
//        System.out.println(energy);
   }
   
    @Override
   public void draw(Graphics g2){
       BufferedImage image = null;
       switch(direction){
           case "up":
               image = lastImage;
               break;
           case "left":
               image = left;
               lastImage = left;
               break;
           case "right":
               image = right;
               lastImage = right;
               break;
       }
       if(image == null){
           System.out.println("null");
       }
            g2.drawImage(image, mapX, mapY,  null);
      
   }
   private boolean isGrounded(){
       int bns=1231221;
       return true;
   }

}