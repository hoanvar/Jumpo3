
package Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

abstract class Entity {
    
    public int  mapX, mapY;
    public int entityWalkSpeed;
    
    public BufferedImage left, right;
    public String direction;
    
    int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea ;
    public boolean collisionOn = false;
    
//    public abstract void getImage(String path);
    public abstract void update();
    public abstract void draw(Graphics g2);
}
