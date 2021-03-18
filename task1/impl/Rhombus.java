package home.denis.task1.impl;

import home.denis.task1.Polygon;
import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.ParallelogramException;
import home.denis.task1.geometric.exception.RhombusException;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static java.lang.Double.NaN;

public class Rhombus extends Parallelogram {


    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public Rhombus(ArrayList<Point2D> vertexes) throws RhombusException, ParallelogramException {
        super(vertexes);

        if (isRhombusCorrect()) {
            throw new RhombusException("Rhombus cannot be created!");
        }
    }

    private boolean isRhombusCorrect() {
        if (vertexes.size() == 4 && sidesLengths.size() == 4) {
            var a = sidesLengths.get(0);
            var b = sidesLengths.get(1);
            var c = sidesLengths.get(2);
            var d = sidesLengths.get(3);

            var p1 = vertexes.get(0);
            var p2 = vertexes.get(1);
            var p3 = vertexes.get(2);
            var p4 = vertexes.get(3);
            return GeoHelper.equalsDouble(a,b) && GeoHelper.equalsDouble(a,c) &&
                    GeoHelper.equalsDouble(a,d) && GeoHelper.equalsDouble(b,c) &&
                    GeoHelper.equalsDouble(b,d) && GeoHelper.equalsDouble(d,c) ||
                    Double.isNaN(GeoHelper.angleBetweenLinesInDegrees(p1, p3, p2, p4));
        }
        return false;
    }

    public Rhombus(Point2D center, double horizontalDiagLen, double verticalDiagLen) throws ParallelogramException {
        super(new ArrayList<>());

        vertexes.add(new Point2D.Double(center.getX()-horizontalDiagLen/2, center.getY()));
        vertexes.add(new Point2D.Double(center.getX(), center.getY()+verticalDiagLen/2));
        vertexes.add(new Point2D.Double(center.getX()+horizontalDiagLen/2, center.getY()));
        vertexes.add(new Point2D.Double(center.getX(), center.getY()-verticalDiagLen/2));
        calculateSidesLengths();
        calculatePerimeter();

        calculateArea();


    }
}
