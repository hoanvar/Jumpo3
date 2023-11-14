package Entity;

import Tool.Tool;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;
public class OldMan extends Entity{
    private int initMap;
    private int routineCount;
    private BufferedImage stand1,stand2;
    public OldMan(GamePanel gamePanel,int initMap){
        super(gamePanel);
        mapX = 10 * gamePanel.tileSize;
        mapY = 22 * gamePanel.tileSize - 6;
        entityWalkSpeed = 1;
        direction = "right";
        this.initMap = initMap;
        routineCount = 0;
        spriteCounter=0;
        spriteNum = 0;
        left  = new BufferedImage[2];
        right = new BufferedImage[2];
        getImage("/OldManImage/");
    }
    @Override
    public void getImage(String path){
        try{
            right[0] = ImageIO.read(getClass().getResourceAsStream(path + "right1.png"));
            right[0] = Tool.scaleImage(right[0], gamePanel.playerSize - 10, gamePanel.playerSize - 10);
            left[0] = Tool.flipImage(right[0]);
            
            right[1] = ImageIO.read(getClass().getResourceAsStream(path + "right2.png"));
            right[1] = Tool.scaleImage(right[1], gamePanel.playerSize - 10, gamePanel.playerSize - 10);
            left[1] = Tool.flipImage(right[1]);
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(){
        if(routineCount>= 0 && routineCount <= 100){
            mapX += entityWalkSpeed;
            direction = "right";
            routineCount++;
        }else if(routineCount > 100 && routineCount <= 200){
            mapX -= entityWalkSpeed;
            direction = "left";
            routineCount++;
        }else{
            routineCount = 0;
        }
        
        delay();
        
    }
    @Override
    public void draw(Graphics g2){
        BufferedImage image = null;
            switch (direction){
                case "right":
                    image = right[spriteNum];
                    break;
                case "left":
                    image = left[spriteNum];
                    break;
            }
       
        
        g2.drawImage(image, mapX, mapY,  null);

    }
    public int getInitMap(){
        return initMap;
    }
    private void delay(){
       spriteCounter++;
            if(spriteCounter > 30 ){
                if(spriteNum == 0){
                    spriteNum = 1;
                }else if(spriteNum == 1){
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
   }
}
