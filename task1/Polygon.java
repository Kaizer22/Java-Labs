package home.denis.task1;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Polygon extends Figure {
    protected ArrayList<Point2D> vertexes;
    protected ArrayList<Double> sidesLengths;

    protected void calculateSidesLengths() {
        if (!vertexes.isEmpty()) {
            sidesLengths.clear();
            Point2D currentPoint, buffer = vertexes.get(0);
            for (int i = 1; i < vertexes.size(); i++) {
                currentPoint = vertexes.get(i);
                sidesLengths.add(buffer.distance(currentPoint));
                buffer = currentPoint;
            }
            sidesLengths.add(buffer.distance(vertexes.get(0)));
        }
    }

    @Override
    protected void calculatePerimeter() {
        for (Double side: sidesLengths) {
            perimeter += side;
        }
    }

    public ArrayList<Point2D> getVertexes() {
        return vertexes;
    }

    public ArrayList<Double> getSidesLengths() {
        return sidesLengths;
    }

    /**
    @param vertexes - polygon's 2D points which placed in a clockwise direction
     */
    public Polygon(ArrayList<Point2D> vertexes) {
        this.vertexes = vertexes;
        this.sidesLengths = new ArrayList<>();
        calculateSidesLengths();
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Polygon{" +
                "vertexes=" + vertexes +
                ", sidesLengths=" + sidesLengths +
                "}\n";
    }
}
