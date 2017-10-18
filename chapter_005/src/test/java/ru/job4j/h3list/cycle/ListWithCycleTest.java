package ru.job4j.h3list.cycle;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * ListWithCycleTest class.
 */
public class ListWithCycleTest {
    /**
     * Checks if the list has elements inside which are circled.
     */
    @Test
    public void cyclingTest() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> secondNode = new Node<>(2);
        Node<Integer> thirdNode = new Node<>(3);
        Node<Integer> fourthNode = new Node<>(4);

        ListWithCycle<Integer> list = new ListWithCycle<>();
        list.first = firstNode;
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = fourthNode;
        fourthNode.next = list.first;
        assertThat(list.hasCycle(), is(true));

        secondNode.next = firstNode;
        fourthNode.next = null;
        assertThat(list.hasCycle(), is(true));

        secondNode.next = thirdNode;
        assertThat(list.hasCycle(), is(false));
    }
}