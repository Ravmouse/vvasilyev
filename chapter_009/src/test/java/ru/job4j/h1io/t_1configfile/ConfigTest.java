package ru.job4j.h1io.t_1configfile;

import org.junit.Test;
import ru.job4j.utils.Utils;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Rav, date: 03.04.2019
 * @version 1.0
 */
public class ConfigTest {
    /**
     * @throws IOException искл.
     */
    @Test
    public void whenLoadToMapThenRetrieveFromMap() throws IOException {
        Config c = new Config(Utils.getResourcePath("ru/job4j/h1io/t_1configfile/app.properties"));
        c.load();
        assertThat(c.value("hibernate.connection.username"), is("postgres"));
    }
}