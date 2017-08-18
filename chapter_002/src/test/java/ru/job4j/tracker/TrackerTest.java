package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TrackerTest.
 */
public class TrackerTest {
    /**
     * The test of adding a new Item method.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * The test of updating an exist Item method.
     */
    @Test
    public void whenAddNewItemAndUpdateThisItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item2 = new Item(item.getId(), "test2", "testDesc2", 456L);
        tracker.update(item2);
        assertThat(tracker.findAll()[0], is(item2));
    }

    /**
     * The test of deleting an exist Item method.
     */
    @Test
    public void whenAddTwoItemsAndDeleteOneOfThemThenTrackerDoesNotHaveSomeItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDesc2", 123L);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll()[0], is(item1));
        assertThat(tracker.findAll()[1], is(item2));
        Item item3 = new Item(item1.getId(), "test3", "testDesc3", 456L);
        tracker.delete(item3);
        assertThat(tracker.findAll()[0], is(item2));
    }

    /**
     * The test of finding all Items method.
     */
    @Test
    public void whenAddTwoItemsAndFingThemAllThenTrackerHasThemAll() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDesc2", 456L);
        Item item3 = new Item("test3", "testDesc3", 789L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] items = {item1, item2, item3};
        assertThat(tracker.findAll(), is(items));
    }

    /**
     * The test of finding the Item by name method.
     */
    @Test
    public void whenAddOneItemAndFindItByNameThenTrackerHasThisItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test1", "testDesc2", 456L);
        Item item3 = new Item("test3", "testDesc3", 789L);
        Item item4 = new Item("test1", "testDesc3", 789L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        Item[] items = {item1, item2, item4};
        assertThat(tracker.findByName("test1"), is(items));
    }

    /**
     * The test of finding the Item by id method.
     */
    @Test
    public void whenAddOneItemAndFindItByIdThenTrackerHasThisItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item(item1.getId(), "test2", "testDesc2", 456L);
        assertThat(tracker.findById(item2.getId()), is(item1));
    }
}