package ru.job4j.converter;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConverterTest class.
 */
public class ConverterTest {
    /**
     * Checks if return value of convert() method can iterate through all the elements within the iterator
     * of the iterator.
     */
    @Test
    public void whenThereAreElementsToIterateThenReturnThem() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        Iterator<Integer> it1 = list1.iterator();

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        Iterator<Integer> it2 = list2.iterator();

        List<Iterator<Integer>> itList = new ArrayList<>();
        itList.add(it1);
        itList.add(it2);
        Iterator<Iterator<Integer>> sIt = itList.iterator();

        Iterator<Integer> iterInt = new Converter().convert(sIt);
        iterInt.next();
        iterInt.next();
        iterInt.next();
        final Integer result = iterInt.next();
        assertThat(result, is(4));
    }

    /**
     * Checks if the hasNext() method returns true when ends one inner iterator and starts another inner iterator
     * within the iterator of the iterator.
     */
    @Test
    public void whenUsingHasNextMethodThenReturnTrueOrFalse() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        Iterator<Integer> it1 = list1.iterator();

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        Iterator<Integer> it2 = list2.iterator();

        List<Iterator<Integer>> itList = new ArrayList<>();
        itList.add(it1);
        itList.add(it2);
        Iterator<Iterator<Integer>> sIt = itList.iterator();

        Iterator<Integer> iterInt = new Converter().convert(sIt);
        iterInt.next();
        iterInt.next();
        boolean result = iterInt.hasNext();
        assertThat(result, is(true));
        iterInt.next();
        iterInt.next();
        result = iterInt.hasNext();
        assertThat(result, is(false));
    }
}