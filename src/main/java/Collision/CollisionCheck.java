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
                playerTopRow = (playerTopY - player.getYSpeed()) / gamePanel.tileSize;
                if(playerTopRow < 0 )   {
                    gamePanel.mapNum++;
                    player.resetYMapChange("next");
                    playerTopY = player.getMapY() + rect.y;
                    playerBottomY = player.getMapY() + rect.y + rect.height;
                    playerTopRow = (playerTopY - player.getYSpeed()) / gamePanel.tileSize;
                    playerBottomRow = playerBottomY / gamePanel.tileSize;
                }
                tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerLeftCol);
                tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerRightCol);
                if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                    player.setIsFalling();
                    player.setIsJumping();
                    player.setHitTop();
                    return true;
                }
                switch(player.getDirection()){
                    case "up":
        
                    case "left":
                        if(playerLeftX - player.getXSpeed() < 0) return true;
                        playerLeftCol = (playerLeftX - player.getXSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerLeftCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerLeftCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                                return true;
                        }
                        break;
                    case "right":
                        playerRightCol = (playerRightX + player.getXSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerRightCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerRightCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true ||gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            return true;
                        }
                        break;
                }
                break;
            case "fall":
                playerBottomRow = (playerBottomY + player.getYSpeed()) / gamePanel.tileSize;
                if(playerBottomRow > 11){
                    gamePanel.mapNum--;
                    player.resetYMapChange("previous");
                    playerTopY = player.getMapY() + rect.y;
                    playerBottomY = player.getMapY() + rect.y + rect.height;
                    playerTopRow = (playerTopY - player.getYSpeed()) / gamePanel.tileSize;
                    playerBottomRow = (playerBottomY + player.getYSpeed()) / gamePanel.tileSize;
                }
                tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerLeftCol);
                tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerRightCol);
                if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                    player.setIsFalling();
                    return true;
                }
                switch(player.getDirection()){
                    case "left":
                        if(playerLeftX - player.getXSpeed() < 0) {player.setHitSide();return true;}
                        playerLeftCol = (playerLeftX - player.getXSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerLeftCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerLeftCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            player.setHitSide();
                            return true;
                        }
                        break;
                    case "right":
                        playerRightCol = (playerRightX + player.getXSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerRightCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerRightCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            player.setHitSide();
                            return true;
                        }
                        break;
                }
              
                break;
            case "walk":
                
//    
                switch(player.getDirection()){
                    case "left":
                        if(playerLeftX - player.getWalkSpeed() < 0) return true;
                        playerLeftCol = (playerLeftX - player.getWalkSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerLeftCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerLeftCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            return true;
                        }
                        break;
                    case "right":
                        playerRightCol = (playerRightX + player.getWalkSpeed()) / gamePanel.tileSize;
                        tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerTopRow, playerRightCol);
                        tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerRightCol);
                        if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                            return true;
                        }
                        break;
                }
                playerBottomRow = (playerBottomY + player.getYSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerLeftCol);
                tileNum2 = gamePanel.tileManager.getMapTileNum(gamePanel.mapNum, playerBottomRow, playerRightCol);
                if(gamePanel.tileManager.getTileCollision(tileNum1) == true || gamePanel.tileManager.getTileCollision(tileNum2) == true){
                    player.setIsFalling();
                    return false;
                }
                break;
                
        }  
        return false;
    }
}