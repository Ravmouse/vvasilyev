package ru.job4j.testtask;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;

/**
 * CodeDivisionTest class.
 */
public class CodeDivisionTest {
    /**
     * Checks whether newList is contained two string elements from expList after deleting all the strings from list.
     * Проверяется, будет ли содержаться в newList две строки из expList после удаления из newList
     * содержимого list.
     */
    @Test
    public void whenSomeStringsAreMissedThenAddThem() {
        CodeDivision cd = new CodeDivision();
        List<String> list = new ArrayList<>();
        list.add("K2");
        list.add("K1\\SK1\\SSK2");
        list.add("K1\\SK2");
        list.add("K1\\SK1\\SSK1");
        list.add("K2\\SK1\\SSK2");
        list.add("K2\\SK1\\SSK1");
        list.add("K1\\SK1");

        List<String> newList = new ArrayList<>(list);
        cd.checkList(newList);
        newList.removeAll(list);

        List<String> expList = new ArrayList<>();
        expList.add("K1");
        expList.add("K2\\SK1");

        assertThat(newList, is(expList));
    }

    /**
     * Checks increase sort order after adding two missed string elements.
     * Проверяется сортировка по возрастанию после добавления двух недостающих строк.
     */
    @Test
    public void whenUseIncreaseSortThenSorted() {
        CodeDivision cd = new CodeDivision();
        List<String> list = new ArrayList<>();
        list.add("K2");
        list.add("K1\\SK1\\SSK2");
        list.add("K1\\SK2");
        list.add("K1\\SK1\\SSK1");
        list.add("K2\\SK1\\SSK2");
        list.add("K2\\SK1\\SSK1");
        list.add("K1\\SK1");

        cd.checkList(list);
        list.sort(cd);

        List<String> expectedList = new ArrayList<>();
        expectedList.add("K1");
        expectedList.add("K1\\SK1");
        expectedList.add("K1\\SK1\\SSK1");
        expectedList.add("K1\\SK1\\SSK2");
        expectedList.add("K1\\SK2");
        expectedList.add("K2");
        expectedList.add("K2\\SK1");
        expectedList.add("K2\\SK1\\SSK1");
        expectedList.add("K2\\SK1\\SSK2");

        assertThat(list, is(expectedList));
    }

    /**
     * Checks decrease sort order after adding two missed string elements.
     * Проверяется сортировка по убыванию после добавления двух недостающих строк.
     */
    @Test
    public void whenUseDecreaseSortThenSorted() {
        CodeDivision cd = new CodeDivision();
        List<String> list = new ArrayList<>();
        list.add("K2");
        list.add("K1\\SK1\\SSK2");
        list.add("K1\\SK2");
        list.add("K1\\SK1\\SSK1");
        list.add("K2\\SK1\\SSK2");
        list.add("K2\\SK1\\SSK1");
        list.add("K1\\SK1");

        cd.checkList(list);
        list.sort(new ReverseComp());

        List<String> expectedList = new ArrayList<>();
        expectedList.add("K2");
        expectedList.add("K2\\SK1");
        expectedList.add("K2\\SK1\\SSK2");
        expectedList.add("K2\\SK1\\SSK1");
        expectedList.add("K1");
        expectedList.add("K1\\SK2");
        expectedList.add("K1\\SK1");
        expectedList.add("K1\\SK1\\SSK2");
        expectedList.add("K1\\SK1\\SSK1");

        assertThat(list, is(expectedList));
    }
}