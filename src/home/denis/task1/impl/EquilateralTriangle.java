package home.denis.task1.impl;

import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.EquilateralTriangleException;
import home.denis.task1.geometric.exception.TriangleException;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class EquilateralTriangle extends Triangle{

    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public EquilateralTriangle(ArrayList<Point2D> vertexes) throws TriangleException,
            EquilateralTriangleException {
        super(vertexes);

        if (!isEquilateralTriangle()) {
            throw new EquilateralTriangleException("Equilateral triangle cannot be created!");
        }
    }

    private boolean isEquilateralTriangle() {
        var a = sidesLengths.get(0);
        var b = sidesLengths.get(1);
        var c = sidesLengths.get(2);
        return GeoHelper.equalsDouble(a, b) && GeoHelper.equalsDouble(a,c)
                && GeoHelper.equalsDouble(b, c);
    }
}
