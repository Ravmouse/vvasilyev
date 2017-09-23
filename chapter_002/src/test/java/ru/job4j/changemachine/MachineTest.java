package ru.job4j.changemachine;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.List;

/**
 * MachineTest class.
 */
public class MachineTest {
    /**
     * Checks if the value of three could be exchanged.
     */
    @Test
    public void whenInsertThreeThenExchangeThree() {
        Machine m = new Machine();
        m.decompose(3);
        List<String> res = m.exchangeAll();
        List<String> list = new ArrayList<>();
        list.add(String.format("%8d", 111));
        assertThat(res, is(list));
    }

    /**
     * Checks if the value of five could be exchanged.
     */
    @Test
    public void whenInsertFiveThenExchangeFive() {
        Machine m = new Machine();
        m.decompose(5);
        List<String> res = m.exchangeAll();
        List<String> list = new ArrayList<>();
        list.add(String.format("%6d", 5));
        list.add(String.format("%6d", 11111));
        assertThat(res, is(list));
    }

    /**
     * Checks if the value of eight could be exchanged.
     */
    @Test
    public void whenInsertEightThenExchangeEight() {
        Machine m = new Machine();
        m.decompose(8);
        List<String> res = m.exchangeAll();
        List<String> list = new ArrayList<>();
        list.add(String.format("%6d%8d", 5, 111));
        list.add(String.format("%6d%8d", 11111, 111));
        assertThat(res, is(list));
    }

    /**
     * Checks if the value of ten could be exchanged.
     */
    @Test
    public void whenInsertTenThenExchangeTen() {
        Machine m = new Machine();
        m.decompose(10);
        List<String> res = m.exchangeAll();
        List<String> list = new ArrayList<>();
        list.add(String.format("%6d", 10));
        list.add(String.format("%6d%6d", 5, 5));
        list.add(String.format("%6d%6d", 5, 11111));
        list.add(String.format("%6d%6d", 11111, 11111));
        assertThat(res, is(list));
    }
}