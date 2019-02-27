package ru.job4j.h1io;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Rav, date: 27.02.2019
 * @version 1.0
 */
public class CheckByteStreamTest {
    /**
     * @throws IOException искл.
     */
    @Test
    public void whenNumberIsEvenThenReturnsTrue() throws IOException {
        CheckByteStream cbs = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(new byte[]{2, 4})) {
            assertThat(cbs.isNumber(in), is(true));
        }
    }

    /**
     * @throws IOException искл.
     */
    @Test
    public void whenNumberIsNotEvenThenReturnsFalse() throws IOException {
        CheckByteStream cbs = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(new byte[]{1, 2, 3})) {
            assertThat(cbs.isNumber(in), is(false));
        }
    }
}