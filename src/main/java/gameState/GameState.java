
package gameState;

import InPut.KeyBoardInput;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import jumpo.Manager.GamePanel;

// tạm thời 0 = game State
//          1 = pause State
public class GameState {
    private GamePanel gamePanel ;
    private int gameStateNum;
    private KeyBoardInput keyBoardInput;
    
    public GameState(GamePanel gamePanel, KeyBoardInput keyBoardInput){
        this.gamePanel = gamePanel;
        gameStateNum = 0;
        this.keyBoardInput =  keyBoardInput;
    }
    public void pauseState(){
        if(keyBoardInput.isPCode()){
            gameStateNum = 1;
        }
        if(keyBoardInput.isPCode() == false){
            gameStateNum = 0;
        }
    }
    public int getGameStateNum(){
        return gameStateNum;
    }
    // tam thoi viet ham ve pause state o day
    public void draw(Graphics g2){
        int frameWidth = gamePanel.screenWidth;
        int frameHeight = gamePanel.screenHeight;
        FontMetrics fontMetrics = g2.getFontMetrics();
        int textWidth = fontMetrics.stringWidth("Pause");
        int textHeight = fontMetrics.getHeight();
        
        int x = (frameWidth - textWidth) /2;
        int y = (frameHeight - textHeight) /2;
        
        g2.drawString("Pause", x, y);
    }
}
