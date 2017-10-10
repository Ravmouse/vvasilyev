package ru.job4j.generic.store;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * AbstractStoreTest class.
 */
public class AbstractStoreTest {
    /**
     * Checks if the User element can be added.
     */
    @Test
    public void testAdding() {
        UserStore<User> uSt = new UserStore<>(10);
        uSt.add(new User("name", "123", 20));
        String res = uSt.getUserArray().get(0).getId();
        assertThat(res, is("123"));
    }

    /**
     * Checks if the User element can be updated.
     */
    @Test
    public void testUpdating() {
        UserStore<User> uSt = new UserStore<>(10);
        uSt.add(new User("name", "123", 30));
        uSt.add(new User("qwerty", "456", 40));
        uSt.add(new User("error", "000", 50));

        User u = new User("hard", "456", 25);
        uSt.update(u);

        String res = uSt.getUserArray().get(1).getId();
        assertThat(res, is("456"));
        res = uSt.getUserArray().get(1).getName();
        assertThat(res, is("hard"));
    }

    /**
     * Checks if the User element can be deleted.
     */
    @Test
    public void testDeleting() {
        UserStore<User> uSt = new UserStore<>(10);
        uSt.add(new User("name", "123", 30));
        uSt.add(new User("qwerty", "456", 40));
        uSt.add(new User("error", "000", 50));
        uSt.delete("123");

        String res = uSt.getUserArray().get(0).getId();
        assertThat(res, is("456"));
    }
}