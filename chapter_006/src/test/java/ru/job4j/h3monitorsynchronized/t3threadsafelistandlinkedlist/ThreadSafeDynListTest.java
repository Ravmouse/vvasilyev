package ru.job4j.h3monitorsynchronized.t3threadsafelistandlinkedlist;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Здесь тестируется многопоточность и методы класса ThreadSafeDynList.
 */
public class ThreadSafeDynListTest {
    /**
     * Внутренний класс для других потоков.
     */
    private final class DynListThread implements Runnable {
        /**
         * Счетчик для цикла.
         */
        private static final int N = 1_000_000;
        /**
         * Ссылка на класс-контейнер.
         */
        private final ThreadSafeDynList<Integer> tsdl;

        /**
         * @param tsdl - ссылка для конструктора.
         */
        private DynListThread(final ThreadSafeDynList<Integer> tsdl) {
            this.tsdl = tsdl;
        }

        /**
         * Запускает новый поток.
         */
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                tsdl.add(10);
            }
        }
    }

    /**
     * Создаются два потока, каждый из них добавляет по N элементов.
     * В результате д.б. N*2 элементов.
     */
    @Test
    public void whenAddItemsInTwoThreadsThenAllTheItemsAreAdded() {
        ThreadSafeDynList<Integer> tsdl = new ThreadSafeDynList<>();
        DynListThread dlt = new DynListThread(tsdl);
        Thread t1 = new Thread(dlt);
        Thread t2 = new Thread(dlt);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(tsdl.getCount(), is(DynListThread.N * 2));
    }
}