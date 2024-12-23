package org.codeforall.AfterMidnight.Objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;

public class Flashlight {

    private Picture flashlight;

    public static boolean isFlashlightOn() {
        return flashlightOn;
    }

    private static boolean flashlightOn = false;

    public Flashlight(){
        flashlight = new Picture(Game.getPicX()-600,Game.getPicY(),
                Game.PREFIX + "Images/Utils/beam_of_light.png");

    }

    public void checkIf(){
        if(MainChar.getWhereIsHe() == 3 && flashlightOn) {
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(-599, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_windowFlash.jpg");
            Game.reset();
        }else{
            MainChar.jumpscare();
        }
    }

    public void activateFlashlight() {
        flashlightOn = true;
        flashlight.draw();
    }

    public void deactivateFlashlight() {
        flashlightOn = false;
        flashlight.delete();
    }

    public void moveFlashlight(int move){
        flashlight.translate(move, 0);
    }

}
