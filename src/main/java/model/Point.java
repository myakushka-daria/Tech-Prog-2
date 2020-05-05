package model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Point")
public class Point {

    @Element(name = "x")
    private double x;

    @Element(name = "y")
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // 2 закон Ньютона F = ma. m = 1кг => F = 1a. Точка получает ускорение на время t
    // положение точки рассчитывается как x = x0 + V0*t + (a*t^2)/2
    public Point movePoint(Vector forceVector, double timeS) {
        x = x + forceVector.getXPower() * Math.pow(timeS, 2.0) / 2.0;
        y = y + forceVector.getYPower() * Math.pow(timeS, 2.0) / 2.0;
        return this;
    }

    public Point() {
        x = 0.0;
        y = 0.0;
    }

    public Point(Point oldPoint) {
        x = oldPoint.getX();
        y = oldPoint.getY();
    }

    public Point(double newx, double newy) {
        x = newx;
        y = newy;
    }

    @Override
    public String toString() {
        return "Point: (" + "x: " + x + ", y: " + y + ")";
    }
}

