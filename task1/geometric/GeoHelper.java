package home.denis.task1.geometric;

import java.awt.geom.Point2D;

public class GeoHelper {
    public static final double GLOBAL_ACCURACY = 0.000001;
    public static boolean isParallel(Point2D line1Point1, Point2D line1Point2,
                                  Point2D line2Point1, Point2D line2Point2) {
        var k1 = (line1Point2.getY() - line1Point1.getY()) /
                (line1Point2.getX() - line1Point1.getX());
        var k2 =  (line2Point2.getY() - line2Point1.getY()) /
                (line2Point2.getX() - line2Point1.getX());
        System.out.println(k1 +" " + k2);
        return equalsDouble(k1, k2, GLOBAL_ACCURACY);
    }

    public static boolean equalsDouble(double val1, double val2, double accuracy) {
        double buf = val1 - val2;
        return Math.abs(buf) <= accuracy;
    }
    public static boolean equalsDouble(double val1, double val2) {
        double buf = val1 - val2;
        return Math.abs(buf) <= GLOBAL_ACCURACY;
    }

    public static double angleBetweenLinesInDegrees(Point2D line1Point1, Point2D line1Point2,
                                           Point2D line2Point1, Point2D line2Point2){
        var k1 = (line1Point2.getY() - line1Point1.getY()) /
                (line1Point2.getX() - line1Point1.getX());
        var k2 = (line2Point2.getY() - line2Point1.getY()) /
                (line2Point2.getX() - line2Point1.getX());
        var angle = Math.toDegrees(Math.atan((k2 - k1)/(1 + k1*k2)));
        if (angle < 0) {
            return angle + 180 ;
        }
        return angle;
    }
}
