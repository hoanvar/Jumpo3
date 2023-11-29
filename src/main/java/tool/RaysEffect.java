/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import jumpo.Manager.GamePanel;

public class RaysEffect {
    private GamePanel gamePanel;
    private int count,count2;
    private int mapX, mapY;
    private boolean triggerOn ,seAc;
    private static final int NUM_RAYS = 3000;
    private static final int MAX_WIDTH = 500;
    private static final int RAYS_PER_BATCH = 50; // Số lượng tia vẽ 1 lúc
    private List<Ray> rays = new ArrayList<>();
    private int maxWidth = 0;
    private int raysDrawnInCurrentBatch = 0;

    public RaysEffect(GamePanel gamePanel,int mapX, int mapY) {
        this.gamePanel = gamePanel;
        triggerOn = false;
        this.mapX = mapX + 25;
        this.mapY = mapY - 13;
        count = 0;
        count2 = 0;
        seAc = false;
    }
    public void update(){
        if(triggerOn){
            if(count> 15){
                if(!seAc){
                    seAc = true;
                        // Winning SE
                        gamePanel.sound.playSE(8);
                }
                if (rays.size() < NUM_RAYS) {
                    drawNextBatch();
                }else{
                    count2++;
                    if(count2> 20){
                        gamePanel.ui.record.update();
                        gamePanel.gameState = gamePanel.endState;
                        gamePanel.sound.playMusic();
                    }
                }
                    
            }else count++;
        }
    }
    public void trigger(){
        if(!triggerOn){
            triggerOn = true;

        }
    }
    private void drawNextBatch() {
        for (int i = 0; i < RAYS_PER_BATCH && rays.size() < NUM_RAYS; i++) {
            addRandomRay();
            raysDrawnInCurrentBatch++;
        }

        // Nếu đã vẽ đủ số lượng tia trong một chu kỳ, tăng độ rộng
        if (raysDrawnInCurrentBatch == RAYS_PER_BATCH) {
            if(maxWidth < MAX_WIDTH)
                maxWidth += 5; 
            raysDrawnInCurrentBatch = 0;
        }
    }

    private void addRandomRay() {
    Random random = new Random();
    int angle = random.nextInt(360);
    double radian = Math.toRadians(angle);
    
    int xEnd = (int) (mapX + ( maxWidth) * Math.cos(radian));
    int yEnd = (int) (mapY + ( maxWidth) * Math.sin(radian));

    Line2D rayLine = new Line2D.Double(mapX , mapY , xEnd, yEnd);
    Ray ray = new Ray(rayLine, 1);

    rays.add(ray);

}
    public void draw(Graphics2D g2) {
        if(triggerOn){
        // Vẽ các tia 
            for (Ray ray : rays) {
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(ray.getWidth()));
                g2.draw(ray.getLine());
            }
        }
    }

    // Lớp để lưu trữ thông tin về tia 
    private static class Ray {
        private Line2D line;
        private float width;

        public Ray(Line2D line, float width) {
            this.line = line;
            this.width = width;
        }

        public Line2D getLine() {
            return line;
        }

        public float getWidth() {
            return width;
        }
    }
}