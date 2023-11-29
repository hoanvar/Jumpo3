/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import jumpo.Manager.GamePanel;

public class Tutorial extends State{
    public Tutorial(GamePanel gamePanel){
        super(gamePanel);
    }
    public void draw(Graphics2D g){
        g.setFont(arial_40);
        g.setColor(Color.red);
        String text = "Tutorial";
        int x = getXCenter(text,g);
        int y = 100;
        g.setColor(Color.black);
        g.drawString(text, x+3 , y+3);
        g.setColor(Color.red);
        g.drawString(text,x,y);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
        text = "Di chuyển:";
        x = 70;
        y = 200;
        g.drawString(text,x,y);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
        text = "A W D : Bấm để chuyển hướng nhân vật thành ";
        g.drawString(text,x,y+50);
        text = "sang trái, lên trên, sang phải.";
        g.drawString(text,x + 110,y + 100);
        text = "Space : Bấm để nhảy.";
        g.drawString(text,x,y+150);
        
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
        g.setColor(Color.red);
        text = "Khác:";
        g.drawString(text,x,450);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
        text = "ESC : Bấm để trở về Game Menu.";
        g.drawString(text,x,500);
        text = "P : Bấm để tạm dừng / hủy tạm dừng game.";
        g.drawString(text,x,550);
        text = "M : Bấm để bật / tắt âm thanh game.";
        g.drawString(text,x,600);
        text = "C : Bấm để bật / tắt cheat game.";
        g.drawString(text,x,650);
   
    }
    
}
