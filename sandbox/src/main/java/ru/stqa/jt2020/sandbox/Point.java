package ru.stqa.jt2020.sandbox;

public class Point {
    double x;
    double y;
    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point th){
        return Math.sqrt(Math.pow(this.x - th.x,2) + Math.pow(this.y - th.y,2));
    }

}
