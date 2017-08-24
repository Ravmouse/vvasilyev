package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.item.Item;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class StubInputTest.
 */
public class StubInputTest {
    /**
     * Checks if the existed Item in the Tracker has the same name.
     */
    @Test
    public void whenAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "Fedor", "TestDescription", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Fedor"));
    }

    /**
     * Checks if the existed Item in the Tracker after replacing it by new Item has the same name.
     */
    @Test
    public void whenUpdateItemThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[] {"1", item.getId(), "Ivan", "Desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getId(), is(item.getId()));
    }

    /**
     * Checks if the Tracker has the same name after deleting a previous(or next) Item.
     */
    @Test
    public void whenDeleteThenTrackerDoesNotHaveThisItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[] {"0", "Fedor", "Desc1", "2", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Fedor"));
    }

    /**
     * Checks if the Tracker has all these Items after adding them.
     */
    @Test
    public void whenAddTwoItemsThenTrackerShowsAllItems() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "Fedor", "Desc1", "0", "Ivan", "Desc2", "3", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Fedor"));
        assertThat(tracker.findAll()[1].getName(), is("Ivan"));
    }

    /**
     * Checks if the Tracker has the Item with the same name when you need to find it by name.
     */
    @Test
    public void whenAddOneItemAndFindItByNameThenTrackerHasThisItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Fedor", "Desc1", 123L));
        Input input = new StubInput(new String[] {"4", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is(item.getName()));
    }

    /**
     * Checks if the Tracker has the Item with the same id when you need to find it by id.
     */
    @Test
    public void whenAddOneItemAndFindItByIdThenTrackerHasThisItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Ivan", "Desc1", 123L));
        Input input = new StubInput(new String[] {"5", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getId(), is(item.getId()));
    }
}