package ru.job4j.h5map.t8myownhashmap;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестовый класс.
 */
public class HashMapArrayTest {
    /**
     * Проверяет, что метод insert() вставляет объекты за исключением дубликатов.
     */
    @Test
    public void insertTest() {
        HashMapArray<Integer, String> hma = new HashMapArray<>(2);
        assertThat(hma.insert(23, "a"), is(true));
        assertThat(hma.insert(33, "ab"), is(true));
        assertThat(hma.insert(43, "abc"), is(true));
        assertThat(hma.insert(23, "abcd"), is(false));
    }

    /**
     * Проверяет, что метод get() по ключу возвращает значение.
     */
    @Test
    public void getTest() {
        HashMapArray<Integer, String> hma = new HashMapArray<>(2);
        hma.insert(48, "asdf");
        assertThat(hma.get(48), is("asdf"));
    }

    /**
     * Проверяет, что метод delete() удаляет объекты по ключу, но только те,
     * что есть в массиве.
     */
    @Test
    public void deleteTest() {
        HashMapArray<Integer, String> hma = new HashMapArray<>(2);
        hma.insert(8, "hello");
        hma.insert(18, "qwerty");
        hma.insert(28, "job");
        assertThat(hma.delete(18), is(true));
        assertThat(hma.delete(19), is(false));
    }
}