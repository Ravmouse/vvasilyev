package ru.job4j.condition;
/**
 * Class Triangle.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Triangle {

    /**
     * The first point of the triangle.
     */
    private Point a;

    /**
     * The second point of the triangle.
     */
    private Point b;

    /**
     * The third point of the triangle.
     */
    private Point c;

    /**
     * The constructor of the object.
     * @param a variable of the Point type
     * @param b variable of the Point type
     * @param c variable of the Point type
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * The method calculates a distance between the left point and the right point.
     * @param left Left point
     * @param right Right point
     * @return The distance between the left point and the right point
     */
    public double distance(Point left, Point right) {
        double result = Math.sqrt((left.getX() - right.getX())
                                * (left.getX() - right.getX())
                                + (left.getY() - right.getY())
                                * (left.getY() - right.getY()));
        return result;
    }

    /**
     * The method calculates a perimeter of the triangle.
     * @param ab The distance between the first and the second points
     * @param ac The distance between the first and the third points
     * @param bc The distance between the second and the third points
     * @return Perimeter of a triangle.
     */
    public double period(double ab, double ac, double bc) {
        return ((ab + ac + bc) / 2);
    }

    /**
     * The method tests whether these distances could be the points of the triangle.
     * @param ab The distance between the first and the second points
     * @param ac The distance between the first and the third points
     * @param bc The distance between the second and the third points
     * @return True or false.
     */
    private boolean exist(double ab, double ac, double bc) {
        return (ab + ac) > bc;
    }

    /**
     * The method calculates the area of the triangle based on the given points.
     * @return The area of the triangle
     */
    public double area() {
        double result = -1;
        double ab = this.distance(this.a, this.b);
        double ac = this.distance(this.a, this.c);
        double bc = this.distance(this.b, this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            result = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return result;
    }
}