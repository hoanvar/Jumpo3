
package Sound;

import InPut.KeyBoardInput;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import jumpo.Manager.GamePanel;

public class Sound {
    GamePanel gamePanel;
    Clip clip;
    URL[] soundURL = new URL[11];
    KeyBoardInput keyBoardInput;
    public Sound(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.keyBoardInput = gamePanel.keyBoardInput;
        soundURL[0] = getClass().getResource("/playerSE/jump.wav"); 
        soundURL[1] = getClass().getResource("/playerSE/bump.wav"); 
        soundURL[2] = getClass().getResource("/playerSE/land.wav"); 
        soundURL[3] = getClass().getResource("/playerSE/splat.wav");
        soundURL[4] = getClass().getResource("/playerSE/sad theme.wav");
        
        soundURL[5] = getClass().getResource("/OthersSE/bird.wav");
        soundURL[6] = getClass().getResource("/OthersSE/slime.wav");
        soundURL[7] = getClass().getResource("/OthersSE/chest2.wav");
        soundURL[8] = getClass().getResource("/OthersSE/winning.wav");
        
        soundURL[9] = getClass().getResource("/GameSound/start.wav");
        soundURL[10] = getClass().getResource("/GameSound/end.wav");

    }
    public void playSE(int i){
        if(keyBoardInput.isMCode()){
            try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void playMusic(){
        if(keyBoardInput.isMCode()){
            int i;
            if(gamePanel.gameState == gamePanel.endState){
                i = 10;
            }else if(gamePanel.gameState != gamePanel.playState && gamePanel.gameState != gamePanel.pauseState){
                i = 9;
            }

            try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[10]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        clip.stop();
    }
}
