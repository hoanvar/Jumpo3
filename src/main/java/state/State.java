/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import jumpo.Manager.GamePanel;


public abstract class State {
    protected GamePanel gamePanel;
    protected Font arial_40;
    protected Color shadow,emphasisColor,normalColor;

    protected State(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_40 = new Font("ARIAL", Font.PLAIN, 100);
        emphasisColor = Color.red;
        shadow = Color.black;
        normalColor = Color.black;
        
    }
    public abstract void draw(Graphics2D g);
    protected int getXCenter(String text,Graphics2D g){
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        return gamePanel.screenWidth/2 - length/2;
        
    }
    protected int getYCenter(String text,Graphics2D g){
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getHeight();
        return gamePanel.screenHeight/2 - length/2;
        
    }
}
