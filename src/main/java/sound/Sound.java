
package Sound;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[20];
    public Sound(){
        soundURL[0] = getClass().getResource("/playerSE/jump.wav"); 
        soundURL[1] = getClass().getResource("/playerSE/bump.wav"); 
        soundURL[2] = getClass().getResource("/playerSE/land.wav"); 
        soundURL[3] = getClass().getResource("/playerSE/splat.wav"); 
    }
    public void playSE(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){
            
        }
    }
}
