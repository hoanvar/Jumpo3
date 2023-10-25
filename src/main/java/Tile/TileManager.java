
package Tile;

import Tool.Tool;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;


public class TileManager {
    GamePanel gamePanel;
    private Tile[] tile;
    //         [mapNum][row][col]
    private int mapTileNum[][][];
    
    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[10][gamePanel.maxScreenRow+1][gamePanel.maxScreenCol];
        getTileImage();
        try {
            for(int i = 0 ; i<= 1 ; i++){
                loadMap(i);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void getTileImage(){
        try{
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Map/TileSrc/TileImage/1.png"));
            tile[1].image = Tool.scaleImage(tile[1].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[1].collision = true;
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    private void loadMap(int mapNumber) throws IOException{
        String abPath = "C:\\Users\\FPT-SHOP\\Desktop\\game java\\MyGame\\src\\main\\resources\\Map\\TileSrc\\Text\\";
//        File file = new File("/Other Sources/src/Map/TileSrc/Text/0.txt");
        File file = new File(abPath + String.valueOf(mapNumber) + ".txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        int row = 0;
        int col = 0;
        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            String line = br.readLine();
            while(col < gamePanel.maxScreenCol){
                String sNumber[] = line.split(" ");
                int number = Integer.parseInt(sNumber[col]);
                mapTileNum[mapNumber][row][col] = number;
                col++;
            }
            if(col == gamePanel.maxScreenCol){
                col = 0;
                row++;
            }
        }
        for(int i = 0 ; i < 48 ; i++){
            mapTileNum[mapNumber][24][i] = 0;
        }                
    }
    public void draw(Graphics2D g2,int mapNum){
        int col = 0;
        int row = 0;
        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            int tileNum = mapTileNum[mapNum][row][col];
            if(tileNum != 0){
                g2.drawImage(tile[tileNum].image, col*gamePanel.tileSize, row*gamePanel.tileSize , null);
            }
            col++;
            if(col == gamePanel.maxScreenCol){
                col = 0;
                row++;
            }
                
        }
    }
    public int getMapTileNum(int mapNum, int row, int col){
//        System.out.println("mapnum " + mapNum + " row " + row + " col " + col);
        if(row < 0) return 0;
        if(col < 0 || col >= 48 ) return 1;
        return mapTileNum[mapNum][row][col];
    }
    public boolean getTileCollision(int number){
        if (tile[number] != null)
            return tile[number].collision;
        else return false;
    }
    class Tile {
        public BufferedImage image;
        public boolean collision = false;
    }
}
