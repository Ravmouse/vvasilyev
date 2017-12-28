package ru.job4j.h6tree.t2isbinary;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тестовый класс.
 */
public class TreeBinCheckTest {
    /**
     * Проверяет, является ли дерево бинарным.
     */
    @Test
    public void whenCreateBinaryTreeThenIdentifyIt() {
        TreeBinCheck<Integer> treeBinCheck = new TreeBinCheck<>();
        treeBinCheck.add(1, 2);
        treeBinCheck.add(1, 3);
        treeBinCheck.add(3, 4);
        treeBinCheck.add(3, 5);
        assertThat(treeBinCheck.isBinary(), is(true));
    }

    /**
     * Проверяет, является ли дерево НЕбинарным.
     */
    @Test
    public void whenCreateNotBinaryTreeThenIdentifyIt() {
        TreeBinCheck<Integer> treeBinCheck = new TreeBinCheck<>();
        treeBinCheck.add(1, 3);
        treeBinCheck.add(1, 4);
        treeBinCheck.add(4, 5);
        treeBinCheck.add(4, 6);
        treeBinCheck.add(4, 7);
        assertThat(treeBinCheck.isBinary(), is(false));
    }
}