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
import java.util.Random;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;


public class Bird extends Entity {
    private int spriteCounterSub;
    private int spriteCounterSubNum;
    private int rev;
    private boolean discard;
    private int type;
    private BufferedImage flyLeft[];
    private BufferedImage flyRight[];
    public Bird(GamePanel gamePanel,int initMap,int mapRow,int mapCol){
        super(gamePanel);
        this.sound = gamePanel.sound;
        this.initMap = initMap;
        mapX = mapCol * gamePanel.tileSize;
        mapY = mapRow * gamePanel.tileSize;
        entityWalkSpeed = 5;
        
        // Generate random number
        int min = 1, max = 2;
        int randomInRange = new Random().nextInt(max - min + 1) + min;
        if(randomInRange == 1) this.direction = "left";
        else    this.direction = "right";
        min = 200; max = 400;
        spriteCounterSubNum = new Random().nextInt(max - min + 1) + min;
        min = 1; max = 2;
        type = new Random().nextInt(max - min + 1) + min;
//        type = 1;
        
        
        this.initMap = initMap;
        triggerOn = false;
        discard = false;
        seActivated = false;
//        routineCount = 0;

        solidArea = new Rectangle(8, 16, 32, 28);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        rev =0;
        spriteCounter=0;
        spriteNum = 0;
        spriteCounterSub = 0;
        left  = new BufferedImage[5];
        right = new BufferedImage[5];
        flyLeft = new BufferedImage[9];
        flyRight = new BufferedImage[9];
        getImage("/BirdImage/");
    }
    @Override
    protected void getImage(String path){
        try{
            // Left right
            for(int i=0 ; i<=4 ; i++){
                left[i] = ImageIO.read(getClass().getResourceAsStream(path + "left"+i+".png"));
                left[i] = Tool.scaleImage(left[i], gamePanel.playerSize - 10, gamePanel.playerSize - 10);
                right[i] = Tool.flipImage(left[i]);
            }
            
            // Fly
            for(int i=0 ; i<=7 ; i++){
                flyLeft[i] = ImageIO.read(getClass().getResourceAsStream(path + "fly"+i+".png"));
                flyLeft[i] = Tool.scaleImage(flyLeft[i], gamePanel.playerSize - 10, gamePanel.playerSize - 10);
                flyRight[i] = Tool.flipImage(flyLeft[i]);
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(){
        if(!discard)
            if(!triggerOn){
                if(spriteCounterSub >= spriteCounterSubNum){
                    if(rev == 0){
                        delay2();
                    }else{
                        delay3();
                    }
                    if(type == 2){
                        if(spriteCounterSub == spriteCounterSubNum + 50){
                        rev = 0;
        //                spriteCounterSub = 0;
                        }
                        if(spriteCounterSub >= spriteCounterSubNum + 100){
                            spriteCounterSub = 0;
                            rev = 0;
                        }
                        spriteCounterSub++;
                    }else{
                        if(spriteCounterSub >= spriteCounterSubNum + 50){
                            spriteCounterSub = 0;
                            rev = 0;
                        }
                        spriteCounterSub++;
                    }
                    
                }else{
                    delay();
                    spriteCounterSub++;
                }
            }else{
                if(direction.equals("left")){
                    mapX -= entityWalkSpeed;
                }else{
                    mapX += entityWalkSpeed;
                }
                mapY -= 1;
                if(mapX <= -10 || mapX >= gamePanel.screenWidth + 10){
                    discard = true;
                }
                spriteCounter++;
                if(spriteCounter > 10 ){
                    if(spriteNum < 7){
                        spriteNum ++;
                    }else{
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            }
//        System.out.println(spriteNum);
    }
    @Override
    protected void delay(){
        spriteCounter++;
            if(spriteCounter > 50 ){
                if(spriteNum <= 0){
                    spriteNum ++;
                }else{
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
    }
    private void delay2(){
          spriteCounter++;
            if(spriteCounter > 5 ){
                if(spriteNum <= 3){
                    spriteNum ++;
                }else{
                    rev=1;
                }
                spriteCounter = 0;
            }   
    }
    private void delay3(){
          spriteCounter++;
            if(spriteCounter > 5 ){
                if(spriteNum >= 1){
                    spriteNum --;
                }
                spriteCounter = 0;
            }   
    }
    public void trigger(String playerDirection){
        triggerOn = true;
        // Crow SE
        if(!seActivated){
            sound.playSE(5);
            seActivated = true;
        }
        
        direction = playerDirection;
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image ;
        if(!discard){
            if(!triggerOn){
                if(direction.equals("left")){
                    image = left[spriteNum];
                }else{
                    image = right[spriteNum];
                }
            }else{
                if(direction.equals("left")){
                    image = flyLeft[spriteNum];
                }else{
                    image = flyRight[spriteNum];
                }
            }
            g2.drawImage(image, mapX, mapY,  null);
        }
    }
    
}
