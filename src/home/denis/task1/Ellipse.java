package home.denis.task1;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Ellipse extends Figure {
    private ArrayList<Point2D> focusPoints;
    private double eccentricity;
    private double paramA;
    private double paramB;

    public Ellipse(ArrayList<Point2D> focusPoints, double paramA, double paramB) {
        this.focusPoints = focusPoints;
        this.paramA = paramA;
        this.paramB = paramB;
        calculateEccentricity();
        calculateArea();
        calculatePerimeter();
    }

    public ArrayList<Point2D> getFocusPoints() {
        return focusPoints;
    }

    public void setFocusPoints(ArrayList<Point2D> focusPoints) {
        this.focusPoints = focusPoints;
    }

    public double getParamA() {
        return paramA;
    }

    public void setParamA(double paramA) {
        this.paramA = paramA;
    }

    public double getParamB() {
        return paramB;
    }

    public void setParamB(double paramB) {
        this.paramB = paramB;
    }

    private void calculateEccentricity() {
        eccentricity = 1 - Math.pow(Math.max(paramA, paramB) / Math.min(paramA, paramB), 2);
    }

    @Override
    protected void calculateArea() {
        area = Math.PI * paramA * paramB;
    }

    //Formula inaccuracy for ellipse ~0,63%
    @Override
    protected void calculatePerimeter() {
        perimeter = 4 * ((Math.PI * paramA * paramB + Math.pow(paramA - paramB, 2))/
                (paramA+paramB));
    }

    @Override
    public String toString() {
        return super.toString() + "Ellipse{" +
                "focusPoints=" + focusPoints +
                ", eccentricity=" + eccentricity +
                ", paramA=" + paramA +
                ", paramB=" + paramB +
                "}\n";
    }
}
