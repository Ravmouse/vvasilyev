package ru.job4j.h1io.t7analysis;

import org.junit.Test;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Rav, date: 05.04.2019
 * @version 1.0
 */
public class AnalysisTest {
    /**
     * @throws IOException искл.
     */
    @Test
    public void whenCreatesOutputThenOutputBytesEqual() throws IOException {
        Analysis a = new Analysis();
        a.unavailable("ru/job4j/h1io/t7analysis/server.log", "unavailable.csv");
        try (InputStream in = new BufferedInputStream(new FileInputStream("unavailable.csv"));
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int k;
            while ((k = in.read()) != -1) {
                out.write(k);
            }
            out.flush();
            assertThat(a.read().getBytes(), is(out.toByteArray()));
        }
    }
}