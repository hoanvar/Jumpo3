
package Entity;


import InPut.KeyBoardInPut;
import Tool.Tool;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import jumpo.Manager.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyBoardInPut keyBoardInPut = new KeyBoardInPut();
    
    private int energy;
    private int xSpeed;
    private int ySpeed;
    private BufferedImage lastImage;
    private boolean isJumping;
    private boolean isFalling;
    private boolean hitTop;
    private boolean hitSide;
    
    public Player (GamePanel gamePanel, KeyBoardInPut keyBoardInPut){
        this.gamePanel = gamePanel;
        this.keyBoardInPut = keyBoardInPut;
        solidArea = new Rectangle(8, 16, 32, 32);
        setDefaultValue();
        getImage("/PlayerImage/");
        
    }   
   private void setDefaultValue(){
       mapX = 10 * gamePanel.tileSize;
       mapY = 10 * gamePanel.tileSize - 60;
       direction = "right";
       energy = 0;
       entityWalkSpeed = 2;
       xSpeed = 3;
       ySpeed = 5;
       isJumping = false;
       isFalling = false;
       hitTop = false;
       hitSide = true;
   }
    @Override
    protected void getImage(String path){
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
       if(isGrounded()){
            handleKBInput();
           if(energy > 0 && keyBoardInPut.getSpacePressed() == false){
                isJumping = true;
            }
       }
        
        if(isJumping == true ){
            collisionOn = false;
            hitTop = false;
            collisionOn = gamePanel.collisionChecker.checkTile(this,"jump"); 
//            System.out.println("check Jump");
            if(!collisionOn){
                switch(direction){
                    case "left":
                        mapX -= xSpeed;
                        break;
                    case "right":
                        mapX += xSpeed;
                        break;
                }
                mapY -= ySpeed;
                energy -= 1;
                if(energy <= 0){
                    energy = 0;
                    isJumping = false;
                    isFalling = true;
                }
            }else{
                if(!hitTop){
                    setKnockBackInfo();
                }
                     
            }
        }

        if(isFalling == true){
            collisionOn = false;
            hitSide = false;
            collisionOn = gamePanel.collisionChecker.checkTile(this,"fall");
//            System.out.println("check Fall");
            if(hitSide){
                if("left".equals(direction)) {
                    direction = "right";
                }
                else if("right".equals(direction)){
                    direction = "left";
                }
                collisionOn = false;
            }
            if(!collisionOn){
                switch(direction){
                    case "left":
                        mapX -= xSpeed;
                        break;
                    case "right":
                        mapX += xSpeed;
                        break;
                }
                    mapY += ySpeed;
            }
            
        }    
        delay();
        
        System.out.println("Energy " + energy);
        System.out.println("Jumping " + isJumping);
        System.out.println("Falling " + isFalling);
        System.out.println("HitTop " + hitTop);
        System.out.println("HitSide " + hitSide);
        System.out.println("Direction " +direction);
        System.out.println("Collision " + collisionOn);
   }
   private void setKnockBackInfo(){
       if(direction.equals("left"))     direction = "right";
       else if(direction.equals("right"))    direction = "left";
       
       energy = 10;
   }
   private void handleKBInput(){
    if(keyBoardInPut.getSpacePressed() == true){
            if(energy < 50) {
                energy++;
            }
    }else{
        if(keyBoardInPut.getLeftPressed() == true){
            direction = "left";
            if(!gamePanel.collisionChecker.checkTile(this,"walk"))
                mapX -= entityWalkSpeed;
        }
        if(keyBoardInPut.getRightPressed() == true){
            direction = "right";
            if(!gamePanel.collisionChecker.checkTile(this,"walk"))
                mapX += entityWalkSpeed;
        }
        if(keyBoardInPut.getUpPressed() == true){
            direction = "up";
        }
    }

       
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
       return isJumping == false && isFalling == false;
   }
   private void delay(){
       spriteCounter++;
            if(spriteCounter > 12 ){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
   }
   public void setHitSide(){
       hitSide = !hitSide;
   }
   public void setHitTop(){
       energy = 0;
       hitTop = true;
   }
   public void setIsJumping(){
       isJumping = !isJumping;
   }
   public void setIsFalling(){
       isFalling = !isFalling;
   }
   public String getDirection(){
       return direction;
   }
   public int getMapX(){
       return mapX;
   }
   public int getMapY(){
       return mapY;
   }
   public Rectangle getSolidArea(){
       return solidArea;
   }
   public int getWalkSpeed(){
       return entityWalkSpeed;
   }
   public int getXSpeed(){
       return xSpeed;
   }
   public void setPlayerCollision(boolean bool){
       collisionOn = bool;
   }
   public int getYSpeed(){
       return ySpeed;
   }
   
}