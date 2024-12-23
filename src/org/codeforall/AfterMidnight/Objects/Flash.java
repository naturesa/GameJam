package org.codeforall.AfterMidnight.Objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;

import static java.lang.Thread.*;

public class Flash {

    private static int remainingFlashes = 30;

    public static boolean isFlashUsed() {
        return flashUsed;
    }

    private static boolean flashUsed = false;


    private static Text flashes;

    public int getRemainingFlashes() {
        return remainingFlashes;
    }

    public void activateFlash() {
        if (remainingFlashes > 0) {
            Game.delete();
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Utils/teste flash.jpg");
            flashUsed = true;
            System.out.println("FLASH");
        }
    }

    public void deactivateFlash(){
        remainingFlashes--;
        writeFlashes();
        Game.delete();
        Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Opened.jpg");

    }

    public static void writeFlashes(){
        if(remainingFlashes <= 0){
            flashes = new Text(75, 10, "0 Flashes");
        } else {
            flashes = new Text(75, 10, remainingFlashes + " Flashes");
        }
        flashes.grow(50,20);
        flashes.setColor(Color.WHITE);
        flashes.draw();
    }

    public void check(){
        if(MainChar.isIsSpawned() && MainChar.getWhereIsHe() == 2 && flashUsed){
            flashUsed = false;
            Game.delete();
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            MainChar.setIsSpawned(false);
        }else {
            MainChar.jumpscare();
        }

    }

}
