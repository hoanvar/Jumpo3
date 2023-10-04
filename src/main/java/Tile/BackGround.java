
package Tile;

import Tool.Tool;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BackGround {
    private BufferedImage[] backGroundImage;
    public BackGround() {
        backGroundImage = new BufferedImage[10];
        getImage();
    }
    public void getImage(){
        try{
            backGroundImage[0] = ImageIO.read(getClass().getResourceAsStream("/BackGround/0.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        backGroundImage[0] = Tool.scaleImage(backGroundImage[0], 48*24, 48*12);
    }
    public void draw(Graphics2D g2){
        g2.drawImage(backGroundImage[0], 0, 0,  null);
    }
    
}
