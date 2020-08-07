package ru.stqa.jt2020.sandbox;

public class hello1 {

    public static void main(String[] args) {
        hello("Karlos");
        hello("Jane");

        System.out.println(2 / 2);
        System.out.println(1.0 / 2.0);
        int storona = 5;
        int sq =storona * storona;
        System.out.println("Площадь квадрата со стороной "+storona+ "=" + sq);
        double lne = 5;
        System.out.println("Площадь через функцию = "+ area(lne));
        System.out.println("Площать прямоугольника ="+ area(7,3));


    }

    public static void hello(String somebody){

        System.out.println("Hello, world! " + somebody);
    }

    public static double area(double l){
        return l*l;
    }
    public static double area(double a,double b){
        return a*b;
    }
}

