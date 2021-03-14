package home.denis.task1;

public abstract class Figure implements Comparable<Figure> {
    protected double area;
    protected double perimeter;

    @Override
    public int compareTo(Figure o) {
        return Double.compare(area, o.area);
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    protected abstract void calculateArea();
    protected abstract void calculatePerimeter();

    @Override
    public String toString() {
        return "--------------------\n"+
                "Figure{" +
                "area=" + area +
                ", perimeter=" + perimeter +
                "}\n";
    }
}
