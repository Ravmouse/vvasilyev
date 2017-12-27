package ru.job4j.h3list.t4cycle;
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
     * Checks if the node of the list is referenced to the first element or the element that is located
     * in the middle of the list.
     */
    @Test
    public void cyclingOnTheMiddleElementTest() {
        Node<Integer> firstN = new Node<>(10);
        Node<Integer> secondN = new Node<>(20);
        Node<Integer> thirdN = new Node<>(30);
        Node<Integer> fourthN = new Node<>(40);
        Node<Integer> fifthN = new Node<>(50);
        Node<Integer> sixthN = new Node<>(60);
        Node<Integer> seventhN = new Node<>(70);

        ListWithCycle<Integer> list = new ListWithCycle<>();
        list.first = firstN;
        firstN.next = secondN;
        secondN.next = thirdN;
        thirdN.next = fourthN;
        fourthN.next = fifthN;
        fifthN.next = sixthN;
        sixthN.next = secondN;
        seventhN.next = null;
        System.out.println(list.hasCycle());
        assertThat(list.hasCycle(), is(true));
    }
}