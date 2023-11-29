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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;


public class Record extends State{
    private BufferedImage characterImage;
    private Double time;
    public Record(GamePanel gamePanel){
        super(gamePanel);
        try {
            characterImage = ImageIO.read(getClass().getResourceAsStream("/Record/character.png"));
            characterImage = Tool.scaleImage(characterImage, gamePanel.playerSize*4 , gamePanel.playerSize*8);
            BufferedReader reader = new BufferedReader(new FileReader("text/record.txt"));
            time = Double.valueOf(reader.readLine());            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g){
        g.setFont(arial_40);
        String text = "Record";
        int x = getXCenter(text,g);
        int y = 100;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(Color.red);
        g.drawString(text,x,y);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
        g.setColor(Color.white);
        text = "Người xưa có câu:";
        g.drawString(text,100,170);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 25));
        text = "Văn vô đệ nhất, võ vô đệ nhị";
        g.setColor(Color.red);
        g.drawString(text,170,220);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20));
        text = "- Người xưa";
        g.setColor(Color.white);
        g.drawString(text,440,260);
        
        text = "Người chạm tay vào Quỳ Hoa Bảo Điển đầu tiên là:";
        g.drawString(text,100,300);
        g.drawImage(characterImage, 100, 310, null);
        
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 25));
        text = "Một vị thiếu hiệp giấu tên";
        g.drawString(text,330,400);
        
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
        text = "Với thời gian chỉ";
        g.drawString(text,330,450);
        g.setColor(Color.red);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 100));
        g.drawString(String.format("%.01f", time) + "s",350,600);

    }
    public void update(){
        time = Math.min(gamePanel.getPlayTime(),time);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("text/record.txt"))) {
            writer.write(String.valueOf(time));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
