/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

import Tool.Tool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;

/**
 *
 * @author FPT-SHOP
 */
public class Intro extends State{
    private BufferedImage image;
    float alpha;
    int n;
    public Intro(GamePanel gamePanel){
        super(gamePanel);
        alpha = 0.0f; 
        n=0;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/UIImage/logo.png"));
            image = Tool.scaleImage(image, gamePanel.playerSize*10, gamePanel.playerSize*10);
            
        }catch(IOException e){
            e.printStackTrace();
        }
       
    }
    public void draw(Graphics2D g){
        n++;
        g.setColor(shadow);
        g.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g.setComposite(alphaComposite);
        
       
        g.drawImage(image,120, 130,null );
        
        g.setComposite(AlphaComposite.SrcOver);

        if (alpha < 0.99f ) {
            alpha += 0.01f; 
        }
    }
    public boolean finish(){
        return n > 200;
    }
}
