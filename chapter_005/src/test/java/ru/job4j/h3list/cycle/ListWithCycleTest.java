package ru.job4j.h3list.cycle;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * ListWithCycleTest class.
 */
public class ListWithCycleTest {
    /**
     * Checks if the node of the list is referenced to the first element.
     */
    @Test
    public void cyclingOnTheFirstElementTest() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> secondNode = new Node<>(2);
        Node<Integer> thirdNode = new Node<>(3);
        Node<Integer> fourthNode = new Node<>(4);

        ListWithCycle<Integer> list = new ListWithCycle<>();
        list.first = firstNode;
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = fourthNode;
        fourthNode.next = firstNode;
        assertThat(list.hasCycle(), is(true));
    }

    /**
     * Checks if the node of the list is referenced to the previous element.
     */
    @Test
    public void cyclingOnTheMiddleElementTest() {
        Node<Integer> firstNode = new Node<>(10);
        Node<Integer> secondNode = new Node<>(20);
        Node<Integer> thirdNode = new Node<>(30);
        Node<Integer> fourthNode = new Node<>(40);

        ListWithCycle<Integer> list = new ListWithCycle<>();
        list.first = firstNode;
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = secondNode;
        fourthNode.next = null;
        assertThat(list.hasCycle(), is(true));
    }
}