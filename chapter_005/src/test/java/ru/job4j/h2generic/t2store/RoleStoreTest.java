package ru.job4j.h2generic.t2store;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * RoleStoreTest class.
 */
public class RoleStoreTest {
    /**
     * Checks if string object can be added to User's id.
     */
    @Test
    public void addingIdToExistedString() {
        RoleStore<Role> roleStore = new RoleStore<>(10);
        roleStore.add(new Role("name", "123"));
        roleStore.addIdToExisted(roleStore.getUserArray().get(0), "456789");
        final String str = roleStore.getUserArray().get(0).getId();
        assertThat(str, is("123456789"));
    }
}