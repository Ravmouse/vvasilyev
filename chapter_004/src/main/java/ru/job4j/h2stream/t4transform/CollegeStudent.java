package ru.job4j.h2stream.t4transform;

import ru.job4j.h2stream.t1filterstudent.Student;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CollegeStudent extends Student {
    /**
     * Фамилия.
     */
    private final String surname;

    /**
     * @param score значение для общего бала.
     * @param surname фамилия.
     */
    public CollegeStudent(int score, String surname) {
        super(score);
        this.surname = surname;
    }

    /**
     * @return фамилию.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("Surname: %s, score: %d", surname, getScore());
    }
}