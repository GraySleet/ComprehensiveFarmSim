package FarmSim;

import java.awt.*;
import java.awt.event.InputEvent;

public class Farmer {

    private long runningTimer;

    //after we go through a cycle, we want to send out- the time elapsed. The number of seeds we have. The number of crops we have.


    public Farmer() throws AWTException {

        Robot myRobot = new Robot();
        double myRandom;
        int myX;
        int myY;
        myRandom = Math.random();
        runningTimer += ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));
        //Harvest All Crops. (259,205) --> (876,237)

        myRandom = Math.random();
        myX = (int) (259 + (876 - 259) * myRandom);
        myY = (int) (205 + (237 - 205) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        runningTimer += ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));


        //Click Plant All Selected. (889,205 --> 1512, 237)
        myRandom = Math.random();
        myX = (int) (889 + (1512 - 889) * myRandom);
        myY = (int) (205 + (237 - 205) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        runningTimer += ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));

        //Click "yes". (732,944 --> 1184, 972)
        myRandom = Math.random();
        myX = (int) (732 + (1184 - 732) * myRandom);
        myY = (int) (944 + (972 - 944) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        runningTimer += ((int) (1000 + 1000 * myRandom));

    }



    public long getRunningTimer() {
        return runningTimer;
    }

    public void setRunningTimer(long timer) {
        runningTimer = timer;
    }

}
