package ru.stqa.jt2020.sandbox;

public class Calculations {

    public static void main(String[] args) {

        System.out.println("Расчитываем Дистанцию между точками");
        Point toch1 = new Point(1,1);
        Point toch2 = new Point(1,-4);

        System.out.println("Distance by function ="+distance(toch1,toch2));
        System.out.println("Distance by method = "+toch1.distance(toch2));
    }

    public static double distance(Point p1, Point p2){
      return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
    }


}
