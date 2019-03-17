package ru.job4j.h1io.t2deletewords;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Rav, date: 02.03.2019
 * @version 1.0
 */
public class AbuseClassTest {
    /**
     * @throws IOException искл.
     */
    @Test
    public void testDropAbuses() throws IOException {
        InputStream in = new ByteArrayInputStream("good hacker programmer trojan".getBytes());
        OutputStream out = new ByteArrayOutputStream();
        String[] abuse = {"hacker", "trojan"};
        new AbuseClass().dropAbuses(in, out, abuse);
        assertThat(out.toString(), is("good programmer"));
    }
}