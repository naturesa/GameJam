package org.codeforall.AfterMidnight.MovementHandler;

import org.academiadecodigo.simplegraphics.delete.Player;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Game.Music;
import org.codeforall.AfterMidnight.Game.Time;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flash;
import org.codeforall.AfterMidnight.Objects.Flashlight;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URI;
import java.net.URL;

public class MyKeyboardHandler implements KeyboardHandler {
    private Keyboard keyboard;
    private Flashlight flashlight;
    private Flash flash;
    private MainChar tz;

    private Door door;

    private boolean firstImage = false;

    private boolean secondImage = false;


    public MyKeyboardHandler(Flashlight flashlight, Flash flash, MainChar TZ, Door door) {
        keyboard = new Keyboard(this);
        setKeyboardEvents();
        this.flashlight = flashlight;
        this.flash = flash;
        this.tz = TZ;
        this.door = door;
    }

    private void setKeyboardEvents() {
        KeyboardEvent flashOn = new KeyboardEvent();
        KeyboardEvent flashOff = new KeyboardEvent();
        flashOn.setKey(KeyboardEvent.KEY_SHIFT);
        flashOff.setKey(KeyboardEvent.KEY_SHIFT);
        flashOn.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        flashOff.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(flashOn);
        keyboard.addEventListener(flashOff);

        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent left = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_SPACE);
        left.setKey(KeyboardEvent.KEY_SPACE);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(right);
        keyboard.addEventListener(left);

        KeyboardEvent doorClose = new KeyboardEvent();
        KeyboardEvent doorOpen = new KeyboardEvent();
        doorClose.setKey(KeyboardEvent.KEY_D);
        doorOpen.setKey(KeyboardEvent.KEY_D);
        doorClose.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        doorOpen.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(doorClose);
        keyboard.addEventListener(doorOpen);

        KeyboardEvent select = new KeyboardEvent();
        select.setKey(KeyboardEvent.KEY_ENTER);
        select.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(select);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SHIFT && Game.outOfMenu && !MainChar.getkilledPlayer()) {
            flashlight.activateFlashlight();
            //flashlight.checkIf();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE && Game.outOfMenu && !MainChar.getkilledPlayer()) {
            flash.activateFlash();
            //flash.check();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_D && Game.outOfMenu && Game.getPicX() >= -500 && !MainChar.getkilledPlayer() && MainChar.getWhereIsHe() == 1) {
            door.close();
            //door.checkDoor();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER && !Game.outOfMenu && !firstImage && !secondImage) {
            Game.draw(0,0,Game.PREFIX + "Images/Backgrounds/Nights_F_news.jpg");
            Music guide =  new Music(Game.PREFIX + "guide.wav");
            guide.play(true);
            //InputStream guide = getClass().getResource(Game.PREFIX + "Sounds/guide.wav");
            //File guide = new File(Game.PREFIX + "Sounds/guide.wav");
            //URL url = MyKeyboardHandler.class.getResource(Game.PREFIX + "Sounds/guide.wav");
            /*try {
                //AudioInputStream guideSound = AudioSystem.getAudioInputStream(url);
                AudioInputStream guideSound = AudioSystem.getAudioInputStream(guide);
                Clip clip = AudioSystem.getClip();
                clip.open(guideSound);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }*/
            firstImage = true;
        }else if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER && !Game.outOfMenu && firstImage && !secondImage) {
            Game.delete();
            Game.draw(0,0,Game.PREFIX + "Images/Backgrounds/IMG_3703.JPG");
            firstImage = false;
            secondImage = true;
        }else if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER && !Game.outOfMenu && secondImage) {
            Game.delete();
            Game.draw(-600, 0, Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            Game.outOfMenu = true;
            Flash.writeFlashes();
            openBackground();
            secondImage = false;
        }

    }

    private void openBackground(){

        //Game.outOfMenu = true;
        //Flash.writeFlashes();
        //Game.draw(-600, 0, Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
        Time time = new Time();
        time.timer(tz);
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_SHIFT && Game.outOfMenu){
            flashlight.deactivateFlashlight();
        }
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_D && Game.outOfMenu){
            door.open();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE && Game.outOfMenu){
            flash.deactivateFlash();
        }

    }

}


