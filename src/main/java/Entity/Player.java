
package Entity;


import InPut.KeyBoardInPut;
import Tool.Tool;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import jumpo.Manager.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyBoardInPut keyBoardInPut = new KeyBoardInPut();
    BufferedImage jump1, fall1, knockBack1, longDistanceFall1;
    BufferedImage jump2, fall2, knockBack2, longDistanceFall2;
    BufferedImage spaceHold;
    private String lastDirection;
    private int energy;
    private int fallDistance;
    private int xSpeed;
    private int ySpeed;
    private boolean isJumping;
    private boolean isFalling;
    private boolean isWalking;
    private boolean hitTop;
    private boolean hitSide;
    private boolean updated;
    
    public Player (GamePanel gamePanel, KeyBoardInPut keyBoardInPut){
        this.gamePanel = gamePanel;
        this.keyBoardInPut = keyBoardInPut;
        solidArea = new Rectangle(8, 16, 32, 30);
        left = new BufferedImage[4];
        right = new BufferedImage[4];
        setDefaultValue();
        getImage("/PlayerImage/");
        
    }   
   private void setDefaultValue(){
       mapX = 20 * gamePanel.tileSize;
       mapY = 10 * gamePanel.tileSize ;
       direction = "right";
       lastDirection = "right";
       energy = 0;
       fallDistance = 0;
       entityWalkSpeed = 3;
       walkcount = 0;
       xSpeed = 3;
       ySpeed = 5;
       isJumping = false;
       isFalling = false;
       isWalking = false;
       hitTop = false;
       hitSide = false;
       updated = false;
   }
    @Override
    protected void getImage(String path){
       try{
            right[0] = ImageIO.read(getClass().getResourceAsStream(path + "left.png"));
            right[0] = Tool.scaleImage(right[0], gamePanel.playerSize, gamePanel.playerSize);
            left[0] = Tool.flipImage(right[0]);
            
            right[1] = ImageIO.read(getClass().getResourceAsStream(path + "leftFootForward.png"));
            right[1] = Tool.scaleImage(right[1], gamePanel.playerSize, gamePanel.playerSize);
            left[1] = Tool.flipImage(right[1]);
            
            right[2] = ImageIO.read(getClass().getResourceAsStream(path + "midRun.png"));
            right[2] = Tool.scaleImage(right[2], gamePanel.playerSize, gamePanel.playerSize);
            left[2] = Tool.flipImage(right[2]);
            
            right[3] = ImageIO.read(getClass().getResourceAsStream(path + "rightFootForward.png"));
            right[3] = Tool.scaleImage(right[3], gamePanel.playerSize, gamePanel.playerSize);
            left[3] = Tool.flipImage(right[3]);
            
            jump1 = ImageIO.read(getClass().getResourceAsStream(path + "jump.png"));
            jump1 = Tool.scaleImage(jump1, gamePanel.playerSize, gamePanel.playerSize);
            jump2 = Tool.flipImage(jump1);
            
            fall1 = ImageIO.read(getClass().getResourceAsStream(path + "fall.png"));
            fall1 = Tool.scaleImage(fall1, gamePanel.playerSize, gamePanel.playerSize);
            fall2 = Tool.flipImage(fall1);
            
            knockBack1 = ImageIO.read(getClass().getResourceAsStream(path + "knockBack.png"));
            knockBack1 = Tool.scaleImage(knockBack1, gamePanel.playerSize, gamePanel.playerSize);
            knockBack2 = Tool.flipImage(knockBack1);
            
            longDistanceFall1 = ImageIO.read(getClass().getResourceAsStream(path + "longDistanceFall.png"));
            longDistanceFall1 = Tool.scaleImage(longDistanceFall1, gamePanel.playerSize, gamePanel.playerSize);
            longDistanceFall2 = Tool.flipImage(longDistanceFall1);
            
            spaceHold = ImageIO.read(getClass().getResourceAsStream(path + "spaceHold.png"));
            spaceHold = Tool.scaleImage(spaceHold, gamePanel.playerSize, gamePanel.playerSize);
        }catch(IOException e){
            e.printStackTrace();
        }
       
   }
    @Override
   public void update(){
       updated = false;
      
       if(isGrounded()){
           handleKBInput();
           if(energy > 0 && keyBoardInPut.isSpacePressed() == false){
                isJumping = true;
            }
       }
        
        if(isJumping == true ){
            collisionOn = false;
            hitTop = false;
            collisionOn = gamePanel.collisionChecker.checkTile(this,"jump"); 
            if(!collisionOn){
                updated = true;
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
                    fallDistance = 0;
                }
            }else{
                if(!hitTop){
                    setKnockBackInfo();
                }    
            }
        }

        if(isFalling == true){
            fallDistance++;
            collisionOn = false;
            hitSide = false;
            collisionOn = gamePanel.collisionChecker.checkTile(this,"fall");
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
        
//        System.out.println("Energy " + energy);
//        System.out.println("Jumping " + isJumping);
//        System.out.println("Falling " + isFalling);
//        System.out.println("HitTop " + hitTop);
//        System.out.println("HitSide " + hitSide);
//        System.out.println("Direction " + direction);
//        System.out.println("Collision " + collisionOn);
   }
   private void setKnockBackInfo(){
       if(direction.equals("left"))     direction = "right";
       else if(direction.equals("right"))    direction = "left";
       
//       energy = 10;
   }
   private void handleKBInput(){
    if(keyBoardInPut.isSpacePressed() == true){
            if(energy < 50) {
                energy++;
            }
    }
    else if(keyBoardInPut.isLeftPressed() == true){
        direction = "left";
        fallDistance = 0;
        if(!gamePanel.collisionChecker.checkTile(this,"walk")){
            mapX -= entityWalkSpeed;
            isWalking = true;
        }     
    }
    else if(keyBoardInPut.isRightPressed() == true){
        direction = "right";
        fallDistance = 0;
        if(!gamePanel.collisionChecker.checkTile(this,"walk")){
            mapX += entityWalkSpeed;
            isWalking = true;
        }       
    }
    else if(keyBoardInPut.isUpPressed() == true){
        if(!"up".equals(direction)) lastDirection = direction;
        fallDistance = 0;
        direction = "up";
        isWalking = false;
    }
    else{
        isWalking = false;
    }    
   }
    @Override
   public void draw(Graphics g2){
       BufferedImage image = null;
           if(isFalling){
               image = getFallImage(direction);
           }else if(isJumping){
               image = getJumpImage(direction);
           }else if (isWalking){
               image = getWalkImage(direction, spriteNum);
           }else{
               if(keyBoardInPut.isSpacePressed() == true){
                   image = spaceHold;
               }else{
                   if(fallDistance > 60)    image = longDistanceFall1;
                   else
                        image = getWalkImage(direction, 0);
               }
                
           }
         
       g2.drawImage(image, mapX, mapY,  null);
      
   }
   private BufferedImage getWalkImage(String direction, int count){
       if("left".equals(direction)){
           return left[count];
       }else if("right".equals(direction)){
           return right[count];
       }else{
           if("left".equals(lastDirection))  return left[count];
           else return right[count];
       }
       
   }
   private BufferedImage getFallImage(String direction){
       if("left".equals(direction)){
           return fall2;
       }else if("right".equals(direction)){
           return fall1;
       }else{
           return getFallImage(lastDirection);
       }
       
   }
   private BufferedImage getJumpImage(String direction){
       if("left".equals(direction)){
           return jump2;
       }else if("right".equals(direction)){
           return jump1;
       }else{
           return getJumpImage(lastDirection);
       }
   }
   private boolean isGrounded(){
//       return isJumping == false && isFalling == false && isWalking == false;
        return isJumping == false && isFalling == false;
   }
   private void delay(){
       spriteCounter++;
            if(spriteCounter > 12 ){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                }else if(spriteNum == 3){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
   }
   public void resetYMapChange(String type){
       if(type.equals("next")){
           mapY += gamePanel.screenHeight;
       }else if(type.equals("previous")){
           mapY -= gamePanel.screenHeight;
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
//   public void setPlayerCollision(boolean bool){
//       collisionOn = bool;
//   }
   public int getYSpeed(){
       return ySpeed;
   }
   
}