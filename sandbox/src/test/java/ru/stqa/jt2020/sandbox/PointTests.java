package ru.stqa.jt2020.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDestination12() {

        Point p1 = new Point(-5,5);
        Point p2 = new Point(5,5);

        Assert.assertEquals(p1.distance(p2), 10.0);
    }

    @Test
    public void testDestination13() {

        Point p1 = new Point(-1,1);
        Point p2 = new Point(-5,-2);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestination14() {

        Point p1 = new Point(-1,1);
        Point p2 = new Point(2,-3);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestination23() {

        Point p1 = new Point(1,2);
        Point p2 = new Point(-3,-1);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestination1X() {

        Point p1 = new Point(-1,3);
        Point p2 = new Point(3,0);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestination1Y() {

        Point p1 = new Point(-1,1);
        Point p2 = new Point(0,1);

        Assert.assertEquals(p1.distance(p2), 1.0);
    }

    @Test
    public void testDestinationX2() {

        Point p1 = new Point(7,0);
        Point p2 = new Point(4,4);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestinationY2() {

        Point p1 = new Point(0,10);
        Point p2 = new Point(3,6);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestination02() {

        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestination20() {

        Point p1 = new Point(40,30);
        Point p2 = new Point(0,0);

        Assert.assertEquals(p1.distance(p2), 50.0);
    }

    @Test
    public void testDestinationSS() {

        Point p1 = new Point(-17,17);
        Point p2 = new Point(-17,17);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDestination00() {

        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDestination22() {

        Point p1 = new Point(8,9);
        Point p2 = new Point(4,6);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDestinationFR() {

        Point p1 = new Point(1,1);
        Point p2 = new Point(3,3);

        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }
}
