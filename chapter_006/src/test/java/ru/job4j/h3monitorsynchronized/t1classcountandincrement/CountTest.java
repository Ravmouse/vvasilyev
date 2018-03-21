package ru.job4j.h3monitorsynchronized.t1classcountandincrement;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * A CountTest class.
 */
public class CountTest {
    /**
     * An inner class.
     */
    private class ThreadCount extends Thread {
        /**
         * A ref. to the Count instance.
         */
        private final Count count;

        /**
         * @param count to be assigned.
         */
        private ThreadCount(final Count count) {
            this.count = count;
        }

        /**
         * Starts another thread.
         */
        @Override
        public void run() {
            this.count.increment();
        }
    }

    /**
     * @throws InterruptedException in this method.
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}