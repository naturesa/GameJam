package org.codeforall.AfterMidnight.Enemies;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Game.Music;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flash;
import org.codeforall.AfterMidnight.Objects.Flashlight;

public class MainChar extends Enemy{
    public static boolean isIsSpawned() {
        return isSpawned;
    }

    private static boolean killedPlayer = false;

    public static boolean getkilledPlayer(){
        return killedPlayer;
    }

    public static void setIsSpawned(boolean isSpawned) {
        MainChar.isSpawned = isSpawned;
    }

    private static boolean isSpawned = false;

    private static int whereIsHe; // 1 = door, 2 = vent, 3 = window

    public static int getWhereIsHe() {
        return whereIsHe;
    }

    public void spawnChance() {
        if(!isSpawned) {
            switch(difficulty){
                case 1:
                    if(Math.ceil(Math.random()*200) <= 15){
                        isSpawned = true;
                        spawn();
                    }
                    break;
                case 4:
                    if(Math.ceil(Math.random()*100) <= 15){
                        isSpawned = true;
                        spawn();
                    }
                    break;
                case 8:
                    if(Math.ceil(Math.random()*100) <= 30){
                        isSpawned = true;
                        spawn();
                    }
                    break;
                case 12:
                    if(Math.ceil(Math.random()*100) <= 45){
                        isSpawned = true;
                        spawn();
                    }
                    break;
                case 16:
                    if(Math.ceil(Math.random()*100) <= 60){
                        isSpawned = true;
                        spawn();
                    }
                    break;
                case 20:
                    if(Math.ceil(Math.random()*100) <= 75){
                        isSpawned = true;
                        spawn();
                    }
                    break;
            }
        }
    }

    public void spawn(){
        switch((int)Math.ceil(Math.random() * 3)) {
            case 1: // If 1 door
                whereIsHe = 1;
                drawSpawnImage(whereIsHe);
                break;
            case 2: // If 2 Vent
                whereIsHe = 2;
                drawSpawnImage(whereIsHe);
                break;
            default: // If 3 Window
                whereIsHe = 3;
                drawSpawnImage(whereIsHe);
                break;
        }
    }

    private void drawSpawnImage(int where){
        switch(where){
            case 1: // door
                Music foot = new Music(Game.PREFIX + "footsteps.wav");
                foot.play(true);
                Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_openedDoor.jpg");
                break;
            case 2: // vent
                Music vent = new Music(Game.PREFIX + "ventcue.wav");
                vent.play(true);
                Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_Vent.jpg");
                break;
            case 3: // window
                Music window = new Music( Game.PREFIX + "windowcue.wav");
                window.play(true);
                Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_window.jpg");
                break;
        }
    }

    /*public void attackMode() {
        switch (whereIsHe){
            case 1: // door
                    if (Door.isIsClosed()) {
                        Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
                        break;
                    }
                jumpscare();
                break;
            case 2: // vent
                    if(Flash.isFlashUsed()) {
                        break;
                    }
                jumpscare();
                break;
            case 3: // window
                    if(Flashlight.isFlashlightOn() && Game.getPicX()==-600){
                        Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_windowFlash.jpg");
                        try {
                            wait(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
                        break;
                    }
                break;
        }
    }*/

    public static void jumpscare(){
            killedPlayer = true;
            Game.delete();
            Music soundJump = new Music(Game.PREFIX + "Fnaf 1 Full Jumpscare Sound.wav");
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            soundJump.play(true);
            Picture jump = new Picture(300, 100, Game.PREFIX + "Images/Backgrounds/jumpscare FZ.png");
            jump.draw();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public void increaseDif(){
        if(difficulty == 1){
            difficulty += 3;
        }else{
            difficulty += 4;
        }
    }

}
