package ru.job4j.h2stream.t5matrixtolist;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TransformMatrixTest {
    /**
     *
     */
    private final Integer[][] matrix = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};

    /**
     *
     */
    @Test
    public void collectFromMatrixToListTest() {
        List<Integer> result = TransformMatrix.collectFromMatrixToList(matrix);
        assertThat(result, is(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }
}