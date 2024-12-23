package org.codeforall.AfterMidnight.Game;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.MovementHandler.MyMouseHandler;

import javax.management.monitor.CounterMonitor;

public class Game {

    public static boolean outOfMenu = false;

    private static int picX;

    private static int picY;

    private static String path;

    public static String getLastImg() {
        return lastImg;
    }

    public static String lastImg;

    public static Picture back;

    public static final String PREFIX = "" ;

    private MainChar TZ = new MainChar();

    public static int getPicX() {
        return picX;
    }

    public static int getPicY() {
        return picY;
    }

    public static String getPath() {
        return path;
    }

    public static void setPicY(int picY) {
        Game.picY = picY;
    }
    Time time = new Time();

    /*public void exitMenu(){
        outOfMenu = true;
        init();
    }*/

    public static void setPicX(int picX) {
        Game.picX = picX;
    }



    public void run() {
       /* // Creating new thread to manage spawn chance
        new Thread(() -> {
            while (Game.outOfMenu) { // Certifique-se de prefixar com `Game.` se a variável é estática
                if (TZ != null) { // Verifica se TZ foi inicializado
                    try {
                        TZ.spawnChance();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.err.println("TZ não foi inicializado. Certifique-se que o init() foi chamado.");
                    break; // Sai do loop para evitar um loop infinito
                }
            }
        }).start(); // Inicia a nova thread*/
    }

    public static void draw(int X, int Y, String caminho){
        picX = X;
        picY = Y;
        lastImg = path;
        path = caminho;
        back = new Picture(X, Y, path);
        back.draw();
    }

    public static void reset(){
        back.delete();
        back = new Picture(picX, picY, PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
        back.draw();
    }

    public static void delete(){
        back.delete();
    }


}
