package ru.job4j.h4waitnotifynotifyall.t1producerconsumer;
import org.junit.Test;

/**
 * Тестовый класс.
 * @author Rav, date: 29.03.2018
 * @version 1.0
 */
public class SimpleBlockingQueueTest {

    /**
     * Внутренний класс.
     */
    private final class BlockingTest implements Runnable {
        /**
         *  Ссылка на экземпляр блокирующей очереди.
         */
        private final SimpleBlockingQueue<Integer> sb;

        /**
         * @param sb - ссылка на экземпляр блокирующей очереди.
         */
        private BlockingTest(SimpleBlockingQueue<Integer> sb) {
            this.sb = sb;
        }

        /**
         * Любой поток, который сюда заходит, 10 раз вызывает метод chooseMethod().
         */
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    chooseMethod(i, sb);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * В зависимости от своего имени один из потоков становится Producer, а другой - Consumer.
         * @param value - значение, чтобы положить и взять из очереди.
         * @param sb - ссылка на экземпляр блокирующей очереди.
         * @throws InterruptedException в случае возникновения исключения.
         */
        private void chooseMethod(int value, SimpleBlockingQueue<Integer> sb) throws InterruptedException {
            if (Thread.currentThread().getName().equals("Thread-0")) {
                sb.offer(value);
            } else {
                sb.poll();
            }
        }
    }

    /**
     * Создает и запускает два потока.
     * @throws InterruptedException в случае возникновения исключения.
     */
    @Test
    public void whenProducerAddsElementsThenConsumerTakesThem() throws InterruptedException {
        SimpleBlockingQueue<Integer> sb = new SimpleBlockingQueue<>();
        Thread t1 = new Thread(new BlockingTest(sb));
        Thread t2 = new Thread(new BlockingTest(sb));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}