package org.codeforall.AfterMidnight.Objects;

import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;

public class Door extends GameObj {

    public static boolean isIsClosed() {
        return isClosed;
    }

    private static boolean isClosed = false;


    public void close(){
        isClosed = true;
        Game.delete();
        Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_closed.jpg");
    }

    public void open(){
        Game.delete();
        Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
        isClosed = false;
    }

    public void checkDoor(){
        if(MainChar.getWhereIsHe() == 1 && isClosed && MainChar.isIsSpawned()){
            Game.delete();
            Game.draw(-9, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_closedDoor.jpg");
            MainChar.setIsSpawned(false);
        }else{
            MainChar.jumpscare();
        }

    }

}
