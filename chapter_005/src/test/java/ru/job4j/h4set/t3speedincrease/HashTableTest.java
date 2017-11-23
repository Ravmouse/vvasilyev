package ru.job4j.h4set.t3speedincrease;
import org.junit.Test;
import java.util.Random;

/**
 * Тестовый класс.
 */
public class HashTableTest {
    /**
     * Сравниваются 2 экз.класса: один - на основе обычного массива, другой - на основе хеш-таблицы.
     */
    @Test
    public void testPerformance() {
        int ii, n = 10_000_000;
        long startFast, startSlow;
        long finFast, finSlow;
        long resFast = 0, resSlow = 0;
        Random rand = new Random();
        FastSimpSet<Integer> fast = new FastSimpSet<>(n);
        SlowSimpSet<Integer> slow = new SlowSimpSet<>(n);
        for (int i = 0; i < n; i++) {
            ii = rand.nextInt(100);

            startFast = System.currentTimeMillis();
            fast.add(ii);
            finFast = System.currentTimeMillis();
            resFast += (finFast - startFast);

            startSlow = System.currentTimeMillis();
            slow.add(ii);
            finSlow = System.currentTimeMillis();
            resSlow += (finSlow - startSlow);
        }
        System.out.println("fast = " + resFast + ", slow = " + resSlow);
    }
}