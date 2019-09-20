package ru.job4j.h2stream.t1filterstudent;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 20.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class SchoolTest {
    /**
     * Общий список.
     */
    private final List<Student> students = new ArrayList<>();
    private final Student zero = new Student(0);
    private final Student one = new Student(11);
    private final Student two = new Student(21);
    private final Student three = new Student(37);
    private final Student four = new Student(43);
    private final Student five = new Student(56);
    private final Student six = new Student(63);
    private final Student seven = new Student(72);
    private final Student eight = new Student(84);
    private final Student nine = new Student(95);
    private final Student ten = new Student(100);

    /**
     * Добавление студентов в общий список.
     */
    @Before
    public void addAll() {
        students.addAll(Arrays.asList(zero, one, two, three, four, five, six, seven, eight, nine, ten));
    }

    /**
     * Тест на получение списка для А-класса.
     */
    @Test
    public void collectA_Class() {
        List<Student> list = School.collect(students, s -> s.getScore() >= 70 && s.getScore() <= 100);
        assertThat(list, is(Arrays.asList(seven, eight, nine, ten)));
    }

    /**
     * Тест на получение списка для Б-класса.
     */
    @Test
    public void collectB_Class() {
        List<Student> list = School.collect(students, s -> s.getScore() >= 50 && s.getScore() < 70);
        assertThat(list, is(Arrays.asList(five, six)));
    }

    /**
     * Тест на получение списка для В-класса.
     */
    @Test
    public void collectC_Class() {
        List<Student> list = School.collect(students, s -> s.getScore() >= 0 && s.getScore() < 50);
        assertThat(list, is(Arrays.asList(zero, one, two, three, four)));
    }
}