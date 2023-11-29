
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

public class EndState extends State{
    BufferedImage image,image2;
    float alpha;
    public EndState(GamePanel gamePanel){
        super(gamePanel);
        alpha = 0.0f; 
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/UIImage/end.png"));
            image = Tool.scaleImage(image, gamePanel.tileSize*gamePanel.maxScreenCol, gamePanel.tileSize*gamePanel.maxScreenRow);   
            image2 = ImageIO.read(getClass().getResourceAsStream("/map/backGround/14.png"));
            image2 = Tool.scaleImage(image2, gamePanel.tileSize*gamePanel.maxScreenCol, gamePanel.tileSize*gamePanel.maxScreenRow);   
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g){
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g.setComposite(alphaComposite);

        g.drawImage(image2, 0, 0, null);
        g.drawImage(image, 0, 0, null);


        
        String text = "Quỳ Hoa";
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 60));
        int x = getXCenter(text,g);
        int y = 150;
        g.setColor(shadow);
        g.drawString(text,x+3,y+3);
        g.setColor(emphasisColor);
        g.drawString(text,x,y);
        
        text = "Lời nói đầu:";
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 25));
        x = 120;
        y = 200;
        g.setColor(shadow);
        g.drawString(text,x+1,y+1);
        g.setColor(emphasisColor);
        g.drawString(text,x,y);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 25));
        
        text = "Dục luyện thần công, dẫn đao tự cung";
        x = getXCenter(text,g);
        y = 250;
        g.setColor(shadow);
        g.drawString(text,x+1,y+1);
        g.setColor(Color.white);
        g.drawString(text,x,y);
        text = "Thiên địa nghịch chuyển, hóa thần tân sinh";
        x = getXCenter(text,g);
        y = 300;
        g.setColor(shadow);
        g.drawString(text,x+1,y+1);
        g.setColor(Color.white);
        g.drawString(text,x,y);
        
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 23));
        g.setColor(normalColor);
        
        text = "Với ta, thiên hạ võ công, vô kiên bất tồi";
        x = 150;
        y = 370;
        g.drawString(text,x,y);
        
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 25));
        text = "Duy Khoái Bất Phá";
        x = getXCenter(text,g);
        y = 420;
        g.setColor(shadow);
        g.drawString(text,x+1,y+1);
        g.setColor(Color.white);
        g.drawString(text,x,y);
        
        text =  "Võ công của ta - nhanh nhất thiên hạ!!!";
        x = getXCenter(text,g);
        y = 490;
        g.setColor(Color.black);
        g.drawString(text,x,y);
        
        
        text = "Silver P Kato";
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
        x = 420;
        y = 550;
        g.setColor(Color.black);
        g.drawString(text,x+1,y+1);
        g.setColor(emphasisColor);
        g.drawString(text,x,y);
        
        g.setComposite(AlphaComposite.SrcOver);

        if (alpha < 0.99f) {
            alpha += 0.01f; 
        }

    }
    
}
