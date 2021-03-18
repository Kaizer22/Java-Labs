package home.denis.task1.impl;

import home.denis.task1.Ellipse;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class Circle extends Ellipse {
    private final double radius;
    private final double diameter;

    public Circle(Point2D center, double radius) {
        super(new ArrayList<>(Collections.singleton(center)),
                radius, radius);
        this.radius = radius;
        this.diameter = radius * 2;
        calculateArea();
        calculatePerimeter();
    }

    public Circle(Point2D center, Point2D pointOnCircle) {
        super(new ArrayList<>(Collections.singleton(center)),
                center.distance(pointOnCircle), center.distance(pointOnCircle));
        this.radius = center.distance(pointOnCircle);
        this.diameter = radius * 2;
        calculateArea();
        calculatePerimeter();
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Circle{" +
                "radius=" + radius +
                ", diameter=" + diameter +
                "}\n";
    }
}
