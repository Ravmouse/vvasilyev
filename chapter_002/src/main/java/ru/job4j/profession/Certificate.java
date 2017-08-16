package ru.job4j.profession;

/**
 * Class Certificate.
 */
public class Certificate {
    /**
     * The degree.
     */
    private String degree;

    /**
     * The constructor.
     * @param degree degree.
     */
    public Certificate(String degree) {
        this.degree = degree;
    }

    /**
     * The getter.
     * @return String.
     */
    public String getDegree() {
        return degree;
    }

    /**
     * The toString method.
     * @return String.
     */
    @Override
    public String toString() {
        return degree;
    }
}
