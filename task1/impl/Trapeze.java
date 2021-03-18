package home.denis.task1.impl;

import home.denis.task1.Polygon;
import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.TrapezeException;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Trapeze extends Polygon {
    private final double base1;
    private final double base2;
    private final double side1;
    private final double side2;

    private double height;

    public Trapeze(Point2D bottomBaseLeft, Point2D bottomBaseRight, Point2D upperBaseLeft, Point2D upperBaseRight) throws TrapezeException {
        super(new ArrayList<>());
        if (!GeoHelper.isParallel(bottomBaseLeft, bottomBaseRight, upperBaseLeft, upperBaseRight)) {
            throw new TrapezeException("Trapeze bases are not parallel!");
        } else {
            vertexes.add(bottomBaseLeft);
            vertexes.add(upperBaseLeft);
            vertexes.add(upperBaseRight);
            vertexes.add(bottomBaseRight);
            calculateSidesLengths();

            side1 = sidesLengths.get(0);
            base1 = sidesLengths.get(1);
            side2 = sidesLengths.get(2);
            base2 = sidesLengths.get(3);
            calculateHeight();

            calculateArea();
            calculatePerimeter();
        }
    }

    private void calculateHeight() {
        var c2 = Math.pow(side1, 2);
        var d2 = Math.pow(side2, 2);
        height = Math.sqrt( c2 +
                Math.pow((Math.pow(base1-base2,2)+ c2 - d2)
                        / (2*(base1-base2)),2));
    }


    @Override
    protected void calculateArea() {
        area = 0.5 * height * (base1 + base2);
    }

    public double getBase1() {
        return base1;
    }

    public double getBase2() {
        return base2;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Trapeze{" +
                "base1=" + base1 +
                ", base2=" + base2 +
                ", side1=" + side1 +
                ", side2=" + side2 +
                ", height=" + height +
                "}\n";
    }
}
