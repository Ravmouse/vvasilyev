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
     *
     */
    @Test
    public void testFindAndReplace() {
        String origin = "go back go home live there go left go right go";
        String word = "go";
        String something = AbuseClass.findAndReplace(origin, word);
        assertThat(something, is("back home live there left right"));
    }

    /**
     * @throws IOException искл.
     */
    @Test
    public void testDropAbuses() throws IOException {
        InputStream in = new ByteArrayInputStream("good hacker programmer trojan".getBytes());
        OutputStream out = new ByteArrayOutputStream();
        String[] abuse = {"hacker", "trojan"};
        AbuseClass.dropAbuses(in, out, abuse);
        assertThat(out.toString(), is("good programmer"));
    }
}