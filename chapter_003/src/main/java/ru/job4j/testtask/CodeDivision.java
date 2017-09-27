package ru.job4j.testtask;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * CodeDivision class.
 */
public class CodeDivision implements Comparator<String> {
    /**
     * @param o1 - string to be compared.
     * @param o2 - string to be compared.
     * @return 0 if the o1 is equal to o2, a value greater than 0 the o1 is greater than o2, a value less than 0 if
     * the o1 is less than o2.
     */
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    /**
     * @param list that should contains all the missed strings in case backslash is within the string element.
     */
    public void checkList(List<String> list) {
        int pos, start;
        String subStr;
        Set<String> set = new TreeSet<>();

        for (String str : list) {
            start = 0; // start для каждого str в list должен начинаться с нуля
            while (str.indexOf('\\', start) != -1) { // Проверка, есть ли в str символ '\', начиная с начала строки.
                                                     // Далее start будет уже равен символу, следующему за символом '\'
                pos = str.indexOf('\\', start); // В pos кладется позиция '\' в первый раз с нуля, а далее с символа,
                                                // идущего за пред.символом '\'
                subStr = str.substring(0, pos); // Получаем подстроку от 0 до 1-го символа '\', до 2-го символа '\' и т.д.
                set.add(subStr); // Добавляем в Set. Set нам нужен, т.к. в нем не будет храниться повторов.
                start = pos + 1; // Увеличиваем start с позиции текущего '\' до след. за ним символа
            }                    // чтобы при след.итерации проверять наличие 2-го '\', 3-го '\' и т.д.
        }
        if (set.isEmpty()) {
            return;
        }
        for (String sStr : set) {
            if (!list.contains(sStr)) {
                list.add(sStr);
            }
        }
    }
}