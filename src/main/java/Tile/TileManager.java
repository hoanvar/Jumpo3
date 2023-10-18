
package Tile;

import Tool.Tool;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import jumpo.Manager.GamePanel;


public class TileManager {
    GamePanel gamePanel;
    private Tile[] tile;
    private int mapTileNum[][];
    
    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
        try {
            loadMap("/Map/Tile/Text/0.txt");
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
    
    private void loadMap(String path) throws IOException{
        File  file = new File("C:\\Users\\FPT-SHOP\\Desktop\\game java\\MyGame\\src\\main\\resources\\Map\\TileSrc\\Text\\0.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int row = 0;
        int col = 0;
        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            String line = br.readLine();
            while(col < gamePanel.maxScreenCol){
                String sNumber[] = line.split(" ");
                int number = Integer.parseInt(sNumber[col]);
                mapTileNum[row][col] = number;
                col++;
            }
            if(col == gamePanel.maxScreenCol){
                col = 0;
                row++;
            }
        }
        for(int i=0 ; i<12 ; i++){
            for(int j=0 ; j<24; j++){
                System.out.print(mapTileNum[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public void draw(Graphics2D g2){
//        System.out.println("tile manager draw com");
        int col = 0;
        int row = 0;
        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            int tileNum = mapTileNum[row][col];
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
    public int getMapTileNum(int row, int col){
//        System.out.println("row: " + row + " col " + col );
        if(col < 0 || col >= 24 ) return 1;
        return mapTileNum[row][col];
    }
    public boolean getTileCollision(int number){
        if (tile[number] != null)
            return tile[number].collision;
        else return false;
    }
}
