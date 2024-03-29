package Bubble.game;

import javax.sound.sampled.*;
import java.io.File;

public class BGM {
    public BGM(){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();
            gainControl.setValue(-30.0f);
        } catch (Exception e){
            e.getMessage();
        }
    }
}
