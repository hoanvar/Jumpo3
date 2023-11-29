/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

import Tool.Tool;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;

public class About extends State {
    private BufferedImage image1, image2, image3;
    public About(GamePanel gamePanel){
        super(gamePanel);
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/Member/0.png"));
            image1 = Tool.scaleImage(image1, gamePanel.playerSize*2 + 24, gamePanel.playerSize*2 + 24);
            image2 = ImageIO.read(getClass().getResourceAsStream("/Member/1.png"));
            image2 = Tool.scaleImage(image2, gamePanel.playerSize*2 + 24, gamePanel.playerSize*2 + 24);
            image3 = ImageIO.read(getClass().getResourceAsStream("/Member/2.png"));
            image3 = Tool.scaleImage(image3, gamePanel.playerSize*2 + 24, gamePanel.playerSize*2 + 24);
        }catch(IOException e){
            e.printStackTrace();
        }
       
    }
    public void draw(Graphics2D g){
        g.setFont(arial_40);
        g.setColor(Color.red);
        String text = "About us";
        int x = getXCenter(text,g);
        int y = 100;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(Color.red);
        g.drawString(text,x,y);
        // Member1
        g.drawImage(image1, 100, 150,null );
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
        text = "Vũ Thanh Hoàn - B21DCCN382";
        x = getXCenter(text,g) + 100;
        g.drawString(text, x, 175);
        text = "Lớp D21CQCN-10B";
        x = getXCenter(text,g) + 45;
        g.drawString(text, x, 225);
        // Member2
        g.setColor(Color.GREEN);
        g.drawImage(image2, 100, 350,null );
        text = "Đỗ Thành Công - B21DCCN022";
        x = getXCenter(text,g) + 100;
        g.drawString(text, x, 375);
        text = "Lớp D21CQCN-10B";
        x = getXCenter(text,g) + 45;
        g.drawString(text, x, 425);
        //Member3
        g.setColor(Color.MAGENTA);
        g.drawImage(image3, 100, 550,null );
        text = "Trần Việt Hoàng - B21DCCN396";
        x = getXCenter(text,g) + 100;
        g.drawString(text, x, 575);
        text = "Lớp D21CQCN-10B";
        x = getXCenter(text,g) + 45;
        g.drawString(text, x, 625);
    }
    
}
