package ru.job4j.h2generic.store;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserStoreTest class.
 */
public class UserStoreTest {
    /**
     * Checks if the User's id can be changed.
     */
    @Test
    public void changingIdTest() {
        UserStore<User> userStore = new UserStore<>(10);
        userStore.add(new User("name", "123", 20));
        userStore.changeId(userStore.getUserArray().get(0), "987987");
        final String str = userStore.getUserArray().get(0).getId();
        assertThat(str, is("987987"));
    }
}