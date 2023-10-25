
package Tile;

import Tool.Tool;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;


public class BackGround {
    private BufferedImage[] backGroundImage;
    private GamePanel gamePanel;
    public BackGround(GamePanel gamePanel) {
        backGroundImage = new BufferedImage[10];
        this.gamePanel = gamePanel;
        getImage();
    }
    public void getImage(){
        try{
            backGroundImage[0] = ImageIO.read(getClass().getResourceAsStream("/BackGround/0.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        backGroundImage[0] = Tool.scaleImage(backGroundImage[0], gamePanel.tileSize*gamePanel.maxScreenCol, gamePanel.tileSize*gamePanel.maxScreenRow);
    }
    public void draw(Graphics2D g2){
        g2.drawImage(backGroundImage[0], 0, 0,  null);
    }
    
}
