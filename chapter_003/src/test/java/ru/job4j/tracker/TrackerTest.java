package ru.job4j.tracker;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import java.util.ArrayList;

/**
 * Class TrackerTest.
 */
public class TrackerTest {
    /**
     * Checks if the tracker has the added Item.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    /**
     * Checks if the tracker has the Item that is edited.
     */
    @Test
    public void whenAddNewItemAndEditThisItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item2 = new Item(item.getId(), "test2", "testDesc2", 456L);
        tracker.replace(item2);
        assertThat(tracker.findAll().get(0), is(item2));
    }

    /**
     * Checks if the tracker doesn't have deleted Item.
     */
    @Test
    public void whenAddTwoItemsAndDeleteOneOfThemThenTrackerDoesNotHaveThisItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test 1", "testDescription", 123L);
        Item item2 = new Item("test 2", "testDesc2", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.delete(item1);
        assertThat(tracker.findAll().get(0), is(item2));
    }

    /**
     * Checks if the tracker has all the Items that are added into the ArrayList as well.
     */
    @Test
    public void whenAddThreeItemsAndFindThemAllThenTrackerHasAllOfThem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test 1", "testDescription", 123L);
        Item item2 = new Item("test 2", "testDesc2", 456L);
        Item item3 = new Item("test 3", "testDesc3", 789L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        assertThat(tracker.findAll(), is(list));
    }

    /**
     * Checks if the tracker has Items with similar names which are added into the ArrayList.
     */
    @Test
    public void whenAddItemsAndFindSomeItemsByNameThenTrackerHasTheseItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test 1", "testDescription", 123L);
        Item item2 = new Item("test 1", "testDesc2", 456L);
        Item item3 = new Item("test 3", "testDesc3", 789L);
        Item item4 = new Item("test 1", "testDesc3", 789L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item4);
        assertThat(tracker.findByName("test 1"), is(list));
    }

    /**
     * Checks if the tracker has the Item which id is similar to new Item's id.
     */
    @Test
    public void whenAddOneItemAndFindItByIdThenTrackerHasThisItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test 1", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item(item1.getId(), "test 2", "testDesc2", 456L);
        assertThat(tracker.findById(item2.getId()), is(item1));
    }
}