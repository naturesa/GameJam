package org.codeforall.AfterMidnight.Game;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flash;
import org.codeforall.AfterMidnight.Objects.Flashlight;

public class Time {
    final boolean[] running = {true};

    private long count = 0;
    long gameTimeInMillis =6 * 60 * 1000; //// 6 minutes in milliseconds



    public void timer(MainChar tz) {

       /* // Creating new thread to manage spawn chance
        new Thread(() -> {
            while (Game.outOfMenu) { // Certifique-se de prefixar com `Game.` se a variável é estática
                if (tz != null) { // Verifica se TZ foi inicializado
                    try {
                        tz.spawnChance();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.err.println("TZ não foi inicializado. Certifique-se que o init() foi chamado.");
                    break; // Sai do loop para evitar um loop infinito
                }
            }
        }).start(); // Inicia a nova thread*/


        // Creating a new thread to manage the game time
        new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                while (running[0]) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long remainingTime = (gameTimeInMillis - elapsedTime) / 1000; // Remaining time in seconds
                        if(!MainChar.isIsSpawned()) {
                            tz.spawnChance();
                        }else{
                            counter();
                        }

                    if(remainingTime % 60 == 0 && remainingTime < 360){
                        tz.increaseDif();
                        System.out.println(tz.getDifficulty());
                    }

                    if (elapsedTime >= gameTimeInMillis) {
                        Game.outOfMenu = false;
                        Game.draw(0, 0, Game.PREFIX + "Images/Backgrounds/Last.jpg");
                        running[0] = false;
                        break;
                    }

                    // Optionally, you can print the remaining time to the console
                    //long remainingTime = (gameTimeInMillis - elapsedTime) / 1000; // Remaining time in seconds
                    System.out.println("Time left: " + remainingTime + " seconds");
                    Thread.sleep(1000); // Sleep for 1 second to update every second
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public void counter(){
        long fivesec = 5;
        long sevenSec = 7;
        if(defenseSuccess()){
            count = 0;
        }else if(count == fivesec){
            MainChar.jumpscare();
            count++;
        }else if(count >= sevenSec) {
            gameOver();
        }else{
            count++;
        }
    }

    public void gameOver(){
        Picture over = new Picture(0,0, Game.PREFIX + "Images/Backgrounds/ded.png");
        over.draw();
    }


    public boolean defenseSuccess(){
        if(MainChar.getWhereIsHe() == 1 && Door.isIsClosed()){
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(-9, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            return true;
        }
        if(MainChar.getWhereIsHe() == 2 && Flash.isFlashUsed()) {
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            return true;
        }
        if(MainChar.getWhereIsHe() == 3 && Flashlight.isFlashlightOn()){
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(-599, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            return true;
        }
        return false;
    }

}

