package chapter1.staticmethod;

public abstract class Polygon implements Shape {

    double[] sides;

    public Polygon(double ... sides) {
        this.sides = sides;
    }
}
