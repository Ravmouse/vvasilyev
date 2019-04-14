package ru.job4j.h3monitorsynchronized.t2userstorage;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * A test class.
 */
public class UserStorageImplTest {
    /**
     * The for loop's counter.
     */
    private static final int N = 10000;

    /**
     * An inner class.
     */
    private class ThreadStorage implements Runnable {

        /**
         * The ref. to the container class.
         */
        private final UserStorageImpl usi;

        /**
         * @param usi to be assigned.
         */
        ThreadStorage(final UserStorageImpl usi) {
            this.usi = usi;
        }

        /**
         * Adds Users in the loop.
         */
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                usi.add(new User());
            }
        }
    }

    /**
     * @throws InterruptedException if any thread has interrupted the current thread.
     */
    @Test
    public void whenCreateNUsersInTwoThreadsThenCountTheNumberOfCreatedUsers() throws InterruptedException {
        UserStorageImpl usi = new UserStorageImpl();
        Thread t1 = new Thread(new ThreadStorage(usi));
        t1.start();
        for (int i = 0; i < N; i++) {
            usi.add(new User());
        }
        t1.join();
        System.out.println(usi.getList().size());
    }
//----------------------------------------------------------------------------------------------------------------------
    /**
     * Tests updating a user in the storage.
     */
    @Test
    public void whenUpdateUserStorageThenAmountIsChanged() {
        final UserStorageImpl us = new UserStorageImpl();
        final User one = new User(1, 20);
        final User two = new User(2, 30);
        final User three = new User(3, 40);
        us.add(one);
        us.add(two);
        us.add(three);
        us.update(two, 333);
        assertThat(us.getList().get(1).getAmount(), is(333));
    }

    /**
     * Tests deleting a user in the storage.
     */
    @Test
    public void whenDeleteUserFromStorageThenUserIsDeleted() {
        final UserStorageImpl us = new UserStorageImpl();
        final User one = new User(1, 20);
        final User two = new User(2, 30);
        final User three = new User(3, 40);
        us.add(one);
        us.add(two);
        us.add(three);
        assertThat(us.getList().get(0).getAmount(), is(20));
        us.delete(one);
        assertThat(us.getList().get(0).getAmount(), is(30));
    }
}