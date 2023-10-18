
package Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

abstract class Entity {
    
    protected int  mapX, mapY;
    protected int entityWalkSpeed;
    
    protected BufferedImage left, right;
    protected String direction;
    
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    
    protected Rectangle solidArea ;
    protected boolean collisionOn = false;
    
    protected abstract void getImage(String path);
    public abstract void update();
    public abstract void draw(Graphics g2);
}
