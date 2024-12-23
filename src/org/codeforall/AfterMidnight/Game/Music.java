package org.codeforall.AfterMidnight.Game;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Music {
    private Clip clip;

    public Music(String path){
        initClip("/" + path);
    }

    public void play(boolean fromStart){
        if(fromStart){
            clip.setFramePosition(0);
        }
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void initClip(String path){
        try{
            InputStream is = Music.class.getResourceAsStream(path);
            if(is == null) {
                throw new RuntimeException("Resource not found: " + path);
            }
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex){
            ex.printStackTrace();
        }
    }
}
