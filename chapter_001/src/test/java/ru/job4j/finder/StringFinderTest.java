package ru.job4j.finder;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 * @author Vitaly Vasilyev (rav.energ@rambler.ru)
 * @version $Id$
 * @since 0.1
 */
public class StringFinderTest {
    /**
     * Test of the string value that should contain substring value.
     */
    @Test
    public void whenStringContainsSubstringThenReturnTrue() {
        StringFinder finder = new StringFinder();
        assertThat(finder.contains("Привет", "иве"), is(true));
    }
}