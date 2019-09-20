package ru.job4j.h2stream.t1filterstudent;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class School {
    /**
     * @param students общий список студентов для распределения.
     * @param predicate условие.
     * @return распределенный список студентов.
     */
    public static List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }
}