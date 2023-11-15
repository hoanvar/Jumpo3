package Entity;

import Tool.Tool;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;
public class OldMan extends Entity{
    private int initMap;
    private int routineCount;
    private BufferedImage stand1,stand2;
    private OldManDialog oldManDialog;
    public OldMan(GamePanel gamePanel,int initMap){
        super(gamePanel);
        oldManDialog = new OldManDialog();
        mapX = 5 * gamePanel.tileSize;
        mapY = 22 * gamePanel.tileSize - 6;
        entityWalkSpeed = 1;
        direction = "right";
        this.initMap = initMap;
        routineCount = 0;
        spriteCounter=0;
        spriteNum = 0;
        left  = new BufferedImage[2];
        right = new BufferedImage[2];
        getImage("/OldManImage/");
    }
    @Override
    public void getImage(String path){
        try{
            right[0] = ImageIO.read(getClass().getResourceAsStream(path + "right1.png"));
            right[0] = Tool.scaleImage(right[0], gamePanel.playerSize - 10, gamePanel.playerSize - 10);
            left[0] = Tool.flipImage(right[0]);
            
            right[1] = ImageIO.read(getClass().getResourceAsStream(path + "right2.png"));
            right[1] = Tool.scaleImage(right[1], gamePanel.playerSize - 10, gamePanel.playerSize - 10);
            left[1] = Tool.flipImage(right[1]);
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(){
        if(routineCount>= 0 && routineCount <= 150){
            mapX += entityWalkSpeed;
            direction = "right";
            routineCount++;
        }else if(routineCount > 100 && routineCount <= 300){
            mapX -= entityWalkSpeed;
            direction = "left";
            routineCount++;
        }else{
            routineCount = 0;
        }
        oldManDialog.updateDialog();
        delay();
        
    }
    @Override
    public void draw(Graphics g2){
        BufferedImage image = null;
            switch (direction){
                case "right":
                    image = right[spriteNum];
                    break;
                case "left":
                    image = left[spriteNum];
                    break;
            }
       
        oldManDialog.draw(g2);
        g2.drawImage(image, mapX, mapY,  null);

    }
    public int getInitMap(){
        return initMap;
    }
    @Override
    protected void delay(){
       spriteCounter++;
            if(spriteCounter > 40 ){
                if(spriteNum == 0){
                    spriteNum = 1;
                }else if(spriteNum == 1){
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
   }
    private class OldManDialog{
        public String dialog[];
        private Font font ;
        private int triangleSize;
        private int rectWidth;
        private int rectHeight;
        // Split dialog text
        private String dialogText,dialogTextSub;
        private int dialogCheck;
        // Shut down dialog
        private int shutDialog,shutDialogCounter;
        // Update text
        private int currentIndex;
        private int currentDialog;
        private int counter;
        private OldManDialog(){
            triangleSize = 10;
            rectWidth = 255;
            dialogText = "";
            dialogTextSub = "";
            dialogCheck = 0;
            shutDialog = 0;
            shutDialogCounter = 0;
            rectHeight = 50;
            currentIndex = 0;
            currentDialog = 0;
            counter = 0;
            font = new Font("Arial", Font.PLAIN, 14);
            dialog = new String[3];
            dialog[0] = "Ho Ho Ho, ta nghe nói trên đỉnh nơi/này có một kho báu rất lớn";
            dialog[1] = "Nó thuộc về một sơn tặc huyền thoại/ông có tên là Silver P Kato";
            dialog[2] = "Giá như tuổi trẻ trở lại thì ta đã có thể/trở thành Gold D PenGu, Ho Ho Ho...";
        }
        private void draw(Graphics g2){
            if(shutDialog == 1){
                int rectX = mapX - rectWidth / 2 + 60;  // Đặt hộp chữ nhật trên đầu nhân vật
                int rectY = mapY - rectHeight - 10;

                // Rectagle
                g2.setColor(Color.WHITE);
                g2.fillRect(rectX, rectY, rectWidth, rectHeight);

                g2.setColor(Color.BLACK);
                g2.drawRect(rectX, rectY, rectWidth, rectHeight);
                // Text
                g2.setFont(font);
                g2.setColor(Color.BLACK);
                g2.drawString(dialogText, rectX + 10, rectY + 20);
                g2.drawString(dialogTextSub, rectX + 10, rectY + 40);
                // Triangle 
                int[] xPoints = {rectX + rectWidth / 2 - triangleSize / 2 - 35, rectX + rectWidth / 2 - 35, rectX + rectWidth / 2 + triangleSize / 2 - 35};
                int[] yPoints = {rectY + rectHeight, rectY + rectHeight + triangleSize, rectY + rectHeight};
                g2.setColor(Color.WHITE);
                g2.fillPolygon(xPoints, yPoints, 3);
                g2.setColor(Color.BLACK);
                g2.drawPolygon(xPoints, yPoints, 3);
            }
        }
        private void updateDialog() {
            if(shutDialog == 1){
                if(currentDialog < 3){
                   counter ++;
    //                System.out.println(dialogText);
                   if(counter == 7){
                        if (currentIndex < dialog[currentDialog].length()) {
                            if(dialog[currentDialog].charAt(currentIndex) == '/'){
                                dialogCheck = 1;
                                currentIndex++;
                            }
                            if(dialogCheck == 0){
                                dialogText += dialog[currentDialog].charAt(currentIndex);
                            }else{
                                dialogTextSub += dialog[currentDialog].charAt(currentIndex);
                            }
                            currentIndex++;

                        }else{
                            if(currentIndex == 100){
                               dialogText = "";
                                dialogTextSub = "";
                                currentIndex = 0;
                                currentDialog ++;
                                dialogCheck = 0; 
                            }else
                                currentIndex++;
                        }
                        counter = 0;
                   }
                }else{
                    currentDialog = 0;
                    shutDialog = 0;
                }
            }else{
                if(shutDialogCounter == 200){
                    shutDialog = 1;
                    shutDialogCounter = 0;
                }else{
                    shutDialogCounter++;
                }
            }
        }
    }
}
