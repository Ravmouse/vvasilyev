package ru.job4j.tracker;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class StubInputTest.
 */
public class StubInputTest {
    /**
     * Checks if the tracker has the added Item.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("Problem");
        list.add("TestDescription");
        list.add("6");
        Input input = new StubInput(list);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("Problem"));
    }

    /**
     * Checks if the tracker has the Item that was edited.
     */
    @Test
    public void whenAddNewItemAndEditThisItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDesc", 123L);
        tracker.add(item);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add(item.getId());
        list.add("4");
        list.add("Error");
        list.add("testDescription2");
        list.add("commentTest");
        list.add("6");
        Input input = new StubInput(list);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("Error"));
        assertThat(tracker.findAll().get(0).getDescription(), is("testDescription2"));
    }

    /**
     * Checks if the tracker doesn't have deleted Item.
     */
    @Test
    public void whenAddTwoItemsAndDeleteOneOfThemThenTrackerDoesNotHaveThisItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDesc", 123L);
        Item item1 = new Item("test1", "testDesc1", 123L);
        tracker.add(item);
        tracker.add(item1);
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add(item.getId());
        list.add("6");
        Input input = new StubInput(list);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test1"));
    }

    /**
     * Checks if the tracker has all the Items.
     */
    @Test
    public void whenAddTwoItemsAndFindThemAllThenTrackerHasAllOfThem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDesc", 123L);
        Item item1 = new Item("test1", "testDesc1", 123L);
        tracker.add(item);
        tracker.add(item1);
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("6");
        Input input = new StubInput(list);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test"));
        assertThat(tracker.findAll().get(1).getName(), is("test1"));
    }

    /**
     * Checks if the tracker finds Items by name & returns them as a List.
     */
    @Test
    public void whenAddAFewItemsAndFindOneOfThemByNameThenTrackerShowsTheList() {
        Tracker tracker = new Tracker();
        Item item = new Item("home", "testDesc", 123L);
        Item item1 = new Item("test1", "testDesc1", 123L);
        Item item2 = new Item("home", "testDesc2", 123L);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        List<String> list = new ArrayList<>();
        list.add("6");
        Input input = new StubInput(list);
        new StartUI(input, tracker).init();
        List<Item> checkList = new ArrayList<>();
        checkList.add(item);
        checkList.add(item2);
        assertThat(tracker.findByName(item.getName()), is(checkList));
    }

    /**
     * Checks if the tracker finds Item by id & returns it.
     */
    @Test
    public void whenAddOneItemAndFindItByIdThenTrackerShowsThisItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("home", "testDesc", 123L);
        tracker.add(item);
        List<String> list = new ArrayList<>();
        list.add("6");
        Input input = new StubInput(list);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getId(), is(item.getId()));
    }
}