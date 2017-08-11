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
        double aTob = a.distanceTo(b);
        double aToc = a.distanceTo(c);
        double bToc = b.distanceTo(c);
        double p = this.period(aTob, aToc, bToc);
        if (this.exist(aTob, aToc, bToc)) {
            result = Math.sqrt(p * (p - aTob) * (p - aToc) * (p - bToc));
        }
        return result;
    }
}