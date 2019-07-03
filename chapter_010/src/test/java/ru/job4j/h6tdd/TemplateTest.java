package ru.job4j.h6tdd;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 03.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TemplateTest {
    /**
     * Проверка генерации нужной строки.
     */
    @Test
    public void whenPassTemplateThenStringEqualsTemplate() {
        Template template = new SimpleGenerator();
        String text = "I am ${name}, who are ${subject}?";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Petr");
        data.put("subject", "you");
        String checked = "I am Petr, who are you?";

        String result = template.generate(text, data);
        assertThat(result, is(checked));
    }

    /**
     * Проверка генерации нужной строки.
     */
    @Test
    public void whenPassTemplateThenStringEqualsTemplateWithReplacement() {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = new HashMap<>();
        data.put("sos", "Aaa");
        String checked = "Help, Aaa, Aaa, Aaa";

        String result = template.generate(text, data);
        assertThat(result, is(checked));
    }

    /**
     * В карте нет ни одного значения - должно возникнуть исключение.
     */
    @Test(expected = RuntimeException.class)
    public void whenDataIsEmptyThenThrowsException() {
        Template template = new SimpleGenerator();
        String text = "Hello, ${name}.";
        Map<String, String> data = new HashMap<>();
        String checked = "Hello, Petr.";

        String result = template.generate(text, data);
        assertThat(result, is(checked));
    }
}