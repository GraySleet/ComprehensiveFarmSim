package FarmSim;

import java.awt.*;

public class myCrop{
    private Point firstPoint;
    private Point secondPoint;
    private int scrollAmount;
    private long cropTime;


    public myCrop(){

    }

    public myCrop(Point fb, Point sb, int sA, long Ct) {
        firstPoint = fb;
        secondPoint = sb;
        scrollAmount = sA;
        cropTime = Ct;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public long getCropTime() {
        return cropTime;

    }

    public int getScrollAmount() {
        return scrollAmount;

    }

    public void setCropTime(long a){
        cropTime = a;

    }

}
