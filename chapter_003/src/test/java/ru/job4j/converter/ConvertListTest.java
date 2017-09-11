package ru.job4j.converter;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.List;
import java.util.ArrayList;

/**
 * Class ConvertListTest.
 */
public class ConvertListTest {
    /**
     * Test of conversion an array into List.
     */
    @Test
    public void whenConvertArrayThenListInterfaceIsCreated() {
        ConvertList convertList = new ConvertList();
        int[][] testArray = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            integerList.add(i);
        }
        assertThat(convertList.toList(testArray), is(integerList));
    }

    /**
     * Test of conversion List into an array.
     */
    @Test
    public void whenConvertListThenArrayIsCreated() {
        ConvertList convertList = new ConvertList();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            integerList.add(i);
        }
        int rows = 3;
        assertThat(convertList.toArray(integerList, rows), is(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 0, 0}}));
    }
}