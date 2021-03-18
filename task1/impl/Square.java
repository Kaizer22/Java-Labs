package home.denis.task1.impl;

import home.denis.task1.geometric.GeoHelper;
import home.denis.task1.geometric.exception.ParallelogramException;
import home.denis.task1.geometric.exception.SquareException;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Square extends Parallelogram{

    /**
     * @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public Square(ArrayList<Point2D> vertexes) throws SquareException, ParallelogramException {
        super(vertexes);
        calculateSidesLengths();
        if (!isCorrectSquare()) {
            throw new SquareException("Square cannot be created!");
        }
        calculatePerimeter();
        calculateArea();
    }

    public Square(Point2D topLeftCorner, double a) throws ParallelogramException {
        super(new ArrayList<>());
        vertexes.add(topLeftCorner);
        vertexes.add(new Point2D.Double(topLeftCorner.getX()+a, topLeftCorner.getY()));
        vertexes.add(new Point2D.Double(topLeftCorner.getX()+a, topLeftCorner.getY()+a));
        vertexes.add(new Point2D.Double(topLeftCorner.getX(), topLeftCorner.getY()+a));
        calculateSidesLengths();

        calculatePerimeter();
        calculateArea();
    }

    private boolean isCorrectSquare(){
        if (vertexes.size() == 4 && sidesLengths.size() == 4) {
            var a = sidesLengths.get(0);
            var b = sidesLengths.get(1);
            var c = sidesLengths.get(2);
            var d = sidesLengths.get(3);
            return GeoHelper.equalsDouble(a,b) && GeoHelper.equalsDouble(a,c) &&
                    GeoHelper.equalsDouble(a,d) && GeoHelper.equalsDouble(b,c) &&
                    GeoHelper.equalsDouble(b,d) && GeoHelper.equalsDouble(d,c);
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "Square{} \n";
    }
}
