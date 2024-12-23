package org.codeforall.AfterMidnight.MovementHandler;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Game.Time;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flashlight;

public class MyMouseHandler implements MouseHandler {

    private static final int CLICK_REGION_X1 = 646;
    private static final int CLICK_REGION_Y1 = 633;
    private static final int CLICK_REGION_X2 = 1185;
    private static final int CLICK_REGION_Y2 = 706;
    private static final int MOVE_RIGHT_BOUNDARY = 246;
    private static final int MOVE_LEFT_BOUNDARY = 1517;

    private final Flashlight light;

    private final Mouse mouse;
    private int mouseXClick, mouseYClick, mouseXMove, mouseYMove;

    public MyMouseHandler(Flashlight flashlight){
        this.light = flashlight;
        mouse = new Mouse(this);
        addMouseListeners();
    }

    private void addMouseListeners() {
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //updateMouseClickCoordinates(mouseEvent);
        /*if (isInClickRegion(mouseXClick, mouseYClick) && !Game.outOfMenu) {
            openBackground();
        }
        if (isInDoorRegion(mouseXClick, mouseYClick) && Game.getPicX() == -10 && Game.outOfMenu){
            closeDoor();
        }

        System.out.println(mouseEvent);*/
    }

    /*private void updateMouseClickCoordinates(MouseEvent mouseEvent) {
        mouseXClick = (int) mouseEvent.getX();
        mouseYClick = (int) mouseEvent.getY();
    }*/

    private void updateMouseMoveCoordinates(MouseEvent mouseEvent) {
        mouseXMove = (int) mouseEvent.getX();
        mouseYMove = (int) mouseEvent.getY();
    }

    /*private boolean isInClickRegion(int x, int y) {
        return x > CLICK_REGION_X1 && y > CLICK_REGION_Y1 && x < CLICK_REGION_X2 && y < CLICK_REGION_Y2;
    }*/

    /*private boolean isInDoorRegion(int x, int y) {
        return x > 389 && y > 373 && x < 427 && y < 620;
    }*/


    /*private void openBackground() {
        Game.draw(0,0,Game.PREFIX + "Images/Backgrounds/IMG_3703.JPG");
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Game.draw(-600, 0, Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
        Game.outOfMenu = true;
        Time time = new Time();
        time.timer(TZ);
    }*/

    /*public static void closeDoor(){
        Door.setIsClosed(true);
        if(MainChar.getWhereIsHe()==1) {
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_closedDoor.jpg");
        }else{
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_closed.jpg");
        }
    }*/

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        updateMouseMoveCoordinates(mouseEvent);
        if (Game.outOfMenu) {
            handleMenuMovement();
        }
        System.out.println(mouseEvent);
    }

    private void handleMenuMovement() {
        if (mouseXMove < MOVE_RIGHT_BOUNDARY && !MainChar.getkilledPlayer()) {
            moveBackgroundRight();
        } else if (mouseXMove > MOVE_LEFT_BOUNDARY && !MainChar.getkilledPlayer()) {
            moveBackgroundLeft();
        }
    }

    private void moveBackgroundRight() {
        if(Game.back.getX() < -10) {
            Game.setPicX(Game.getPicX()+10);
            Game.back.translate(10, 0);
            light.moveFlashlight(10);
        }
    }

    private void moveBackgroundLeft() {
        if(Game.back.getX() > -600) {
            Game.setPicX(Game.getPicX()-10);
            Game.back.translate(-10, 0);
            light.moveFlashlight(-10);
        }
    }

}

