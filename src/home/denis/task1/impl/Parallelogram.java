package home.denis.task1.impl;

import home.denis.task1.Polygon;
import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.ParallelogramException;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.util.ArrayList;

public class Parallelogram extends Polygon {
    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public Parallelogram(ArrayList<Point2D> vertexes) throws ParallelogramException {
        super(vertexes);
        if (!vertexes.isEmpty()) {
            if (!isParallelogramCorrect()) {
                throw new ParallelogramException("Parallelogram cannot be created!");
            }
            calculateArea();
            calculatePerimeter();
        }

    }

    //Creates rectangle
    public Parallelogram(Point2D topLeft, Point2D rightBottom) {
        super(new ArrayList<>());
        double a = rightBottom.getX() - topLeft.getX();
        double b = topLeft.getY() - rightBottom.getY();
        vertexes.add(topLeft);
        vertexes.add(new Point2D.Double(topLeft.getX() + a, topLeft.getY()));
        vertexes.add(rightBottom);
        vertexes.add(new Point2D.Double(topLeft.getX(), topLeft.getY() - b));
        calculateSidesLengths();

        calculateArea();
        calculatePerimeter();
    }

    //todo
    @Override
    protected void calculateArea() {
        double a = sidesLengths.get(0);
        double b = sidesLengths.get(1);

        var p1 = vertexes.get(0);
        var p2 = vertexes.get(1);
        var p3 = vertexes.get(2);
        var p4 = vertexes.get(3);


        double angle = GeoHelper.angleBetweenLinesInDegrees(p1, p2, p2, p3);

        area = a * b * Math.sin(Math.toRadians(angle));
    }

    private boolean isParallelogramCorrect() {
        if (vertexes.size() == 4 && sidesLengths.size() == 4) {
            var p1 = vertexes.get(0);
            var p2 = vertexes.get(1);

            var p3 = vertexes.get(2);
            var p4 = vertexes.get(3);

            System.out.println(GeoHelper.isParallel(p1,p2, p3, p4));
            System.out.println(GeoHelper.isParallel(p2,p3,p4,p1));

            return GeoHelper.isParallel(p1,p2, p3, p4)
                    && GeoHelper.isParallel(p2,p3,p4,p1);
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "Parallelogram{} \n";
    }
}
