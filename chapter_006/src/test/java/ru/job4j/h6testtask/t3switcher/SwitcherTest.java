package ru.job4j.h6testtask.t3switcher;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

/**
 * Класс SwitcherTest.
 */
public class SwitcherTest {
    /**
     * Проверяется по счетчику counter перекрывается ли один поток другим.
     */
    @Test
    public void whenAddAmountOfNumbersThenRetrieveThemAll() {
        Switcher switcher = new Switcher();
        try {
            switcher.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(switcher.getCounter().get(), greaterThan(0));
    }
}