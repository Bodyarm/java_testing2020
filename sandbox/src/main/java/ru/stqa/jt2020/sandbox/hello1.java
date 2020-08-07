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

        Square s= new Square(6);
        System.out.println("Площадь через функцию со стороной "+ s.l + " = "+ area(s));

        Rectangle r = new Rectangle(4,8);
        System.out.println("Площать прямоугольника со стороныами " + r.a + " и " + r.b +" = " + area(r));


    }

    public static void hello(String somebody){

        System.out.println("Hello, world! " + somebody);
    }

    public static double area(Square s){
        return s.l*s.l;
    }
    public static double area(Rectangle r){
        return r.a*r.b;
    }
}

