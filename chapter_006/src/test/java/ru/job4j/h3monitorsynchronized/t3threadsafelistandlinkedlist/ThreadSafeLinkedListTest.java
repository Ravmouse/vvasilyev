package ru.job4j.h3monitorsynchronized.t3threadsafelistandlinkedlist;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Здесь тестируется многопоточность и методы класса ThreadSafeLinkedList.
 */
public class ThreadSafeLinkedListTest {
    /**
     * Внутренний класс для других потоков.
     */
    private final class LinkedListThread implements Runnable {
        /**
         * Счетчик для цикла.
         */
        private static final int N = 1_000_000;
        /**
         * Ссылка на класс-контейнер.
         */
        private final ThreadSafeLinkedList<Integer> tsll;

        /**
         * @param tsll - ссылка для конструктора.
         */
        LinkedListThread(final ThreadSafeLinkedList tsll) {
            this.tsll = tsll;
        }

        /**
         * Запускает новый поток.
         */
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                tsll.addFirst(10);
            }
        }
    }

    /**
     * Создаются два потока, каждый из них добавляет по N элементов.
     * В результате д.б. N*2 элементов.
     */
    @Test
    public void whenAddItemsIntoLLInTwoThreadsThenNumberOfItemsIsDefined() {
        ThreadSafeLinkedList<Integer> tsll = new ThreadSafeLinkedList<>();
        LinkedListThread llt = new LinkedListThread(tsll);
        Thread t1 = new Thread(llt);
        Thread t2 = new Thread(llt);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(tsll.getCount(), is(2_000_000));
    }

    /**
     * Тестируется итератор при получении следующего и предыдущего элементов.
     */
    @Test
    public void whenAddItemsThenIterateEachElementInTwoWays() {
        ThreadSafeLinkedList<Integer> ll = new ThreadSafeLinkedList<>();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);

        ThreadSafeLinkedList<Integer>.LinkedListIterator it = ll.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));

        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previous(), is(1));
        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previous(), is(2));
        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previous(), is(3));
        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previous(), is(4));
        assertThat(it.hasPrevious(), is(true));
        assertThat(it.previous(), is(5));
        assertThat(it.hasPrevious(), is(false));
    }
}