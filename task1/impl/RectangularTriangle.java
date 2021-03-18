package home.denis.task1.impl;

import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.RectangularTriangleException;
import home.denis.task1.geometric.exception.TriangleException;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RectangularTriangle extends Triangle{
    private final double catheter1;
    private final double catheter2;
    private final double hypotenuse;
    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public RectangularTriangle(ArrayList<Point2D> vertexes) throws TriangleException,
            RectangularTriangleException {
        super(vertexes);
        Collections.sort(sidesLengths);
        hypotenuse = sidesLengths.get(2);
        catheter1 = sidesLengths.get(1);
        catheter2 = sidesLengths.get(0);
        if (!isRectangularTriangleCorrect()) {
            throw new RectangularTriangleException("Rectangular triangle cannot be created!");
        }

    }

    public RectangularTriangle(Point2D cornerPoint, double catheter1, double catheter2) throws TriangleException {
        super(new ArrayList<>());
        this.catheter1 = catheter1;
        this.catheter2 = catheter2;

        vertexes.add(cornerPoint);
        vertexes.add(new Point2D.Double(cornerPoint.getX() + catheter1, cornerPoint.getY()));
        vertexes.add(new Point2D.Double(cornerPoint.getX(), cornerPoint.getY() + catheter2));
        calculateSidesLengths();
        hypotenuse = sidesLengths.get(1);

        calculateArea();
        calculatePerimeter();
    }

    private boolean isRectangularTriangleCorrect() {
        return GeoHelper.equalsDouble(Math.pow(catheter1, 2) + Math.pow(catheter2, 2),
                Math.pow(hypotenuse, 2));
    }

    @Override
    public String toString() {
        return super.toString() +
                "RectangularTriangle{" +
                "catheter1=" + catheter1 +
                ", catheter2=" + catheter2 +
                ", hypotenuse=" + hypotenuse +
                "}\n";
    }
}
