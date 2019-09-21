package ru.job4j.h2stream.t4transform;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.h2stream.t1filterstudent.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TransformTest {
    /**
     * Список студентов.
     */
    private final List<CollegeStudent> students = new ArrayList<>();
    /**
     * Карта.
     */
    private final Map<String, Student> expected = new HashMap<>();

    /**
     * Создание студентов и заполнение списка и карты.
     */
    @Before
    public void init() {
        final CollegeStudent one = new CollegeStudent(50, "Mike");
        final CollegeStudent two = new CollegeStudent(60, "Pete");
        final CollegeStudent three = new CollegeStudent(90, "John");
        final CollegeStudent four = new CollegeStudent(30, "Carl");
        students.addAll(Arrays.asList(one, two, three, four));

        expected.put(one.getSurname(), one);
        expected.put(two.getSurname(), two);
        expected.put(three.getSurname(), three);
        expected.put(four.getSurname(), four);
    }

    /**
     * Проверка.
     */
    @Test
    public void collectToMapTest() {
        final Map<String, Student> result = Transform.collectToMap(students);
        assertThat(result, is(expected));
    }
}