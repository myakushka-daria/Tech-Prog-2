package model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Vector")
public class Vector {
    @Element(name = "startPoint")
    private Point startPoint;
    @Element(name = "endPoint")
    private Point endPoint;
    @Element(name = "force")
    private double force;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public double getForce() {
        return force;
    }

    public double getXPower() {
        return force * ((endPoint.getX() - startPoint.getX()) / Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2.0)
                + Math.pow(endPoint.getY() - startPoint.getY(), 2.0))); // force * cos
    }

    public double getYPower() {
        return force * ((endPoint.getY() - startPoint.getY()) / Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2.0)
                + Math.pow(endPoint.getY() - startPoint.getY(), 2.0))); // force * sin
    }

    public Vector() {
        startPoint = new Point();
        endPoint = new Point();
        force = 0.0;
    }

    public Vector(double newx, double newy, double newx1, double newy1, double newpower) {
        startPoint = new Point(newx, newy);
        endPoint = new Point(newx1, newy1);
        force = newpower;
    }

    public Vector(Point point1, Point point2, double newpower) {
        startPoint = point1;
        endPoint = point2;
        force = newpower;
    }

    @Override
    public String toString() {
        return "Vector: (startPoint: " + startPoint.toString() + " endPoint: " + endPoint.toString() + " absForce = " + force + ")";
    }
}
