package ru.job4j.h2generic.simplearray;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleArrayTest class.
 */
public class SimpleArrayTest {
    /**
     * Checks creating and adding a string element into the SimpleArray object.
     */
    @Test
    public void whenStringTypeIsEnteredThenReturnTheSameType() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test");
        String s = simpleArray.get(0);
        assertThat(s, is("test"));
    }

    /**
     * Checks creating and adding an int element into the SimpleArray object.
     */
    @Test
    public void whenIntTypeIsEnteredThenReturnTheSameType() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(10);
        int s = simpleArray.get(0);
        assertThat(s, is(10));
    }

    /**
     * Checks replacing existed element with a new element by its index.
     * And also replacing the old value with the new value.
     */
    @Test
    public void testUpdateMethod() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test");
        simpleArray.update("123", 0);
        assertThat(simpleArray.get(0), is("123"));

        simpleArray.update("123", "555");
        assertThat(simpleArray.get(0), is("555"));
    }

    /**
     * Checks deleting existed element in the SimpleArray object.
     */
    @Test
    public void testDeleteMethod() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test");
        simpleArray.add("123");
        simpleArray.delete("test");
        assertThat(simpleArray.get(0), is("123"));
    }

    /**
     * Checks getting an element by its position number.
     */
    @Test
    public void testGetMethod() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test");
        simpleArray.add("123");
        simpleArray.add("45");
        simpleArray.add("78");
        assertThat(simpleArray.get(2), is("45"));
    }
}