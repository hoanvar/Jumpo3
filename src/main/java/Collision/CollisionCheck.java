
package Collision;

import Entity.Player;
import java.awt.Rectangle;
import jumpo.Manager.GamePanel;

public class CollisionCheck {
    GamePanel gamePanel;
    public CollisionCheck(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    public boolean checkTile(Player player, String type){
        Rectangle rect = player.getSolidArea();
        int playerLeftX = player.getMapX() + rect.x;
        int playerRightX = player.getMapX() + rect.x + rect.width;
        int playerTopY = player.getMapY() + rect.y;
        int playerBottomY = player.getMapY() + rect.y + rect.height;
        
        int playerLeftCol = playerLeftX / gamePanel.tileSize;
        int playerRightCol = playerRightX / gamePanel.tileSize;
        int playerTopRow = playerTopY / gamePanel.tileSize;
        int playerBottomRow = playerBottomY / gamePanel.tileSize;
        int tileNum1,tileNum2;
        switch(type){
            case "jump" :
                playerTopRow = (playerTopY - player.getJumpSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNum(playerTopRow, playerLeftCol);
                tileNum2 = gamePanel.tileManager.getMapTileNum(playerTopRow, playerRightCol);
                if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                    player.setIsFalling();
                    player.setIsJumping();
                    player.setHitTop();
                    return true;
                }
                switch(player.getDirection()){
                    case "up":
        
                    case "left":
                        playerLeftCol = (playerLeftX - player.getJumpSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(playerTopRow, playerLeftCol);
//                        tileNum2 = gamePanel.tileManager.getMapTileNum(playerBottomRow, playerLeftCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true){
                                return true;
                        }
                        break;
                    case "right":
                        playerRightCol = (playerRightX + player.getJumpSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(playerTopRow, playerRightCol);
//                        tileNum2 = gamePanel.tileManager.getMapTileNum(playerBottomRow, playerRightCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true ){
                            return true;
                        }
                        break;
                }
                break;
            case "fall":
                
                playerBottomRow = (playerBottomY + player.getFallSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNum(playerBottomRow, playerLeftCol);
                tileNum2 = gamePanel.tileManager.getMapTileNum(playerBottomRow, playerRightCol);
                if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                    player.setIsFalling();
//                    System.out.println("check fall");
//                  player.setHitBot();
                    return true;
                }
                switch(player.getDirection()){
                    case "left":
                        playerLeftCol = (playerLeftX - player.getFallSpeed()) / gamePanel.tileSize;
//                        tileNum1 = gamePanel.tileManager.getMapTileNum(playerTopRow, playerLeftCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(playerBottomRow, playerLeftCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            player.setHitSide();
                            return true;
                        }
                        break;
                    case "right":
                        playerRightCol = (playerRightX + player.getFallSpeed()) / gamePanel.tileSize;
//                        tileNum1 = gamePanel.tileManager.getMapTileNum(playerTopRow, playerRightCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(playerBottomRow, playerRightCol);
                        if( gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            player.setHitSide();
                            return true;
                        }
                        break;
                }
              
                break;
                
        }  
        return false;
    }
}
