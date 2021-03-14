package home.denis.task1.impl;

import home.denis.task1.Polygon;
import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.GeoException;
import home.denis.task1.geometric.exception.TriangleException;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Triangle extends Polygon {

    private final double[] angles;

    private final double[] heights;
    private final double[] medians;
    private final double[] bisectors;

    private double inscribedCircleRadius;
    private double circumscribedCircleRadius;


    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public Triangle(ArrayList<Point2D> vertexes) throws TriangleException {
        super(vertexes);
        calculateSidesLengths();

        if (!isTriangleCorrect()) {
            throw new TriangleException("Triangle cannot be created!");
        }
        calculatePerimeter();
        calculateArea();

        heights = new double[3];
        angles = new double[3];
        medians = new double[3];
        bisectors = new double[3];
        calculateHeights();
        calculateBisectors();
        calculateMedians();
        calculateAngles();

        calculateMedians();
        calculateBisectors();
        calculateInscribedCircleRadius();
        calculateCircumscribedCircleRadius();
    }

    private void calculateCircumscribedCircleRadius() {
        circumscribedCircleRadius = (sidesLengths.get(0) * sidesLengths.get(1) * sidesLengths.get(2))
                / (4 * area);
    }

    private void calculateInscribedCircleRadius() {
        inscribedCircleRadius = area / (perimeter / 2);
    }


    private void calculateBisectors() {
        double a = sidesLengths.get(0);
        double b = sidesLengths.get(1);
        double c = sidesLengths.get(2);
        
        bisectors[0] = calculateBisector(a, b, c);
        bisectors[1] = calculateBisector(b, a, c);
        bisectors[2] = calculateBisector(c, b, a);

    }

    private double calculateBisector(double supportSide, double side1, double side2) {
        return Math.sqrt(side1 * side2 * perimeter * (side1 + side2 - supportSide))
                / (side1 + side2);
    }

    private void calculateMedians() {
        double a = sidesLengths.get(0);
        double b = sidesLengths.get(1);
        double c = sidesLengths.get(2);

        medians[0] = calculateMedian(a, b, c);
        medians[1] = calculateMedian(b, a, c);
        medians[2] = calculateMedian(c, b, a);
    }

    private double calculateMedian(double supportSide, double side1, double side2) {
        return Math.sqrt(2 * (Math.pow(side1, 2) + Math.pow(side2, 2)) - Math.pow(supportSide,2))
                / 2;
    }

    private void calculateAngles() {
        var p1 = vertexes.get(0);
        var p2 = vertexes.get(1);
        var p3 = vertexes.get(2);
        angles[0] = GeoHelper.angleBetweenLinesInDegrees(p3, p1, p1, p2);
        angles[1] = GeoHelper.angleBetweenLinesInDegrees(p1, p2, p2, p3);
        angles[2] = GeoHelper.angleBetweenLinesInDegrees(p2, p3, p3, p1);
    }

    private void calculateHeights() {
        int i = 0;
        for (Double side: sidesLengths) {
            heights[i] = 2 * area / side;
            i++;
        }
    }

    @Override
    protected void calculateArea() {
        double hP = perimeter/2;
        double a = sidesLengths.get(0);
        double b = sidesLengths.get(1);
        double c = sidesLengths.get(2);

        area = Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));
    }

    private boolean isTriangleCorrect() {
        if (vertexes.size() == 3 && sidesLengths.size() == 3) {
            double a = sidesLengths.get(0);
            double b = sidesLengths.get(1);
            double c = sidesLengths.get(2);

            return (a+b > c) && (a+c > b) && (b+c > a);
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "Triangle{" +
                "angles=" + Arrays.toString(angles) +
                ", heights=" + Arrays.toString(heights) +
                ", medians=" + Arrays.toString(medians) +
                ", bisectors=" + Arrays.toString(bisectors) +
                ", inscribedCircleRadius=" + inscribedCircleRadius +
                ", circumscribedCircleRadius=" + circumscribedCircleRadius +
                "}\n";
    }
}
