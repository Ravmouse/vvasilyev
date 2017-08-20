package ru.job4j.drawfigure;

/**
 * Class Paint.
 */
public class Paint {
    /**
     * Calls the method pic() from an instance of Shape type interface.
     * @param shape of Shape interface.
     * @return The String.
     */
    public String draw(Shape shape) {
        return shape.pic();
    }
}