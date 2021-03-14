package home.denis;

import home.denis.task1.Ellipse;
import home.denis.task1.Figure;
import home.denis.task1.FigureComparator;
import home.denis.task1.geometric.exception.*;
import home.denis.task1.impl.*;
import home.denis.task1.io.FigureFileWriter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Figure> figures = new ArrayList<>();
        figures.add(new Parallelogram(new Point2D.Double(0, 2),
                new Point2D.Double(10, 0)));
        try {
            figures.add(new Parallelogram(new ArrayList<>(
                    Arrays.asList(new Point2D.Double(2, 5),
                            new Point2D.Double(4, 8),
                            new Point2D.Double(12, 8),
                            new Point2D.Double(10, 5)))));
            figures.add(new Triangle(new ArrayList<>(
                    Arrays.asList(new Point2D.Double(0, 2),
                            new Point2D.Double(1, 3),
                            new Point2D.Double(10, 1))
            )));
            figures.add(new RectangularTriangle(new ArrayList<>(
                    Arrays.asList(new Point2D.Double(10, 10),
                            new Point2D.Double(10, 15),
                            new Point2D.Double(20, 10)))));
//            figures.add(new EquilateralTriangle(new ArrayList<>(
//                    Arrays.asList(new Point2D.Double(10, 10),
//                            new Point2D.Double(10, 15),
//                            new Point2D.Double(20, 10)))));
            figures.add(new IsoscelesTriangle(new ArrayList<>(
                    Arrays.asList(new Point2D.Double(5, 5),
                            new Point2D.Double(10, 10),
                            new Point2D.Double(15, 5)))));

            figures.add(new Circle(new Point2D.Double(1,1), 10));
            figures.add(new Ellipse(new ArrayList<>(Arrays.asList(new Point2D.Double(5, 5),
                    new Point2D.Double(10, 10))), 4, 7));
            FigureFileWriter.writeFigureToFile("figures", new Triangle(new ArrayList<>(
                    Arrays.asList(new Point2D.Double(10, 10),
                            new Point2D.Double(10, 15),
                            new Point2D.Double(20, 10)))), true);
        } catch (TriangleException | RectangularTriangleException | IsoscelesException | ParallelogramException e) {
            e.printStackTrace();
        }
        figures.sort(new FigureComparator());
        System.out.println(figures);

    }
}
