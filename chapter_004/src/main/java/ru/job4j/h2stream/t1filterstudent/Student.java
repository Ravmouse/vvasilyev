package ru.job4j.h2stream.t1filterstudent;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Student {
    /**
     * Общий бал.
     */
    private final int score;

    /**
     * @param score значение для общего бала.
     */
    public Student(int score) {
        this.score = score;
    }

    /**
     * @return общий бал.
     */
    public int getScore() {
        return score;
    }
}