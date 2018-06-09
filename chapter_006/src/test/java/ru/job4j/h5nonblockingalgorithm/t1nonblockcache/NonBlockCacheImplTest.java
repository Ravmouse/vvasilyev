package ru.job4j.h5nonblockingalgorithm.t1nonblockcache;
import org.junit.Test;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Класс NonBlockCacheImplTest.
 */
public class NonBlockCacheImplTest {
    /**
     * Экземпляр класса на неблок.алгоритмах.
     */
    private NonBlockCacheImpl<Integer, Model> nbc = new NonBlockCacheImpl<>();
    /**
     * Атомарная пременная.
     */
    private AtomicInteger count = new AtomicInteger();

    /**
     * @return случайно сгенерированную строку.
     */
    private String generateString() {
        final Random rnd = new Random();
        int wordLen = rnd.nextInt(10) + 10; //Сгенерировать длину слова
        final String str = "abcdefghijklmnopqrstuvwxyz";
        final StringBuilder sb = new StringBuilder();
        char ch;
        int tmp;
        for (int i = 0; i < wordLen; i++) {
            tmp = rnd.nextInt(str.length()); //Сгенерировать число от 0 до 26
            ch = str.charAt(tmp);
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Метод каждого потока.
     */
    private void execute() {
        int n = 100;
        for (int i = 0; i < n; i++) {
            Model m = new Model(generateString());
            nbc.add(count.getAndIncrement(), m);
        }
    }

    /**
     * @throws InterruptedException в случае исключения.
     */
    @Test
    public void whenAddElementsToTheMapThenMapHasThemAll() throws InterruptedException {
        Thread t1 = new Thread(this::execute);
        Thread t2 = new Thread(this::execute);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(nbc.size(), is(200));
    }

    /**
     * @throws InterruptedException в случае исключения.
     */
    @Test
    public void whenUpdateElementsThenMapHasThemUpdated() throws InterruptedException {
        final NonBlockCacheImpl<Integer, Model> nbc = new NonBlockCacheImpl<>();
        nbc.add(0, new Model("test"));
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                nbc.update(0, new Model("help"));
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                nbc.update(0, new Model("drive"));
            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                nbc.update(0, new Model("fate"));
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}