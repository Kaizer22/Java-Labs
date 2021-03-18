package home.denis.task1.impl;

import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.EquilateralTriangleException;
import home.denis.task1.geometric.exception.IsoscelesException;
import home.denis.task1.geometric.exception.TriangleException;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class IsoscelesTriangle extends Triangle{
    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public IsoscelesTriangle(ArrayList<Point2D> vertexes) throws TriangleException,
            IsoscelesException {
        super(vertexes);

        if (!isIsoscelesTriangle()) {
            throw new IsoscelesException("Isosceles triangle cannot be created!");
        }
    }

    private boolean isIsoscelesTriangle() {
        var a = sidesLengths.get(0);
        var b = sidesLengths.get(1);
        var c = sidesLengths.get(2);
        return GeoHelper.equalsDouble(a, b) || GeoHelper.equalsDouble(b,c)
                || GeoHelper.equalsDouble(c, a);
    }

    @Override
    public String toString() {
        return super.toString() + "IsoscelesTriangle {} \n";
    }
}
