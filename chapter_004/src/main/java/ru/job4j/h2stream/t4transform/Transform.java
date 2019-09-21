package ru.job4j.h2stream.t4transform;

import ru.job4j.h2stream.t1filterstudent.Student;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Transform {
    /**
     * @param students список студентов.
     * @return отображение; ключ - фамилия студента, значение - студент.
     */
    public static Map<String, Student> collectToMap(List<CollegeStudent> students) {
        return students.stream().collect(Collectors.toMap(CollegeStudent::getSurname, e -> e));
    }
}