package ru.job4j.tracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Tracker.
 */
public class Tracker {
    /**
     * The List of Item elements.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * The Random constant.
     */
    private static final Random RND = new Random();

    /**
     * Items getter.
     * @return The List of Items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Adds the Item into the List of Item elements.
     * @param item to be added in the Tracker.
     * @return added Item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Replaces the parameter Item in the List of Items.
     * @param item to be replaced in the Tracker.
     */
    public void replace(Item item) {
        for (Item value : items) {
            if (value != null && value.getId().equals(item.getId())) {
                final int index = this.items.indexOf(value);
                this.items.set(index, item);
                break;
            }
        }
    }

    /**
     * Deletes the Item in the List of Items.
     * @param item to be deleted in the Tracker.
     */
    public void delete(Item item) {
        for (Item value : items) {
            if (value != null && value.getId().equals(item.getId())) {
                final int index = this.items.indexOf(value);
                this.items.remove(index);
                break;
            }
        }
    }

    /**
     * Returns all Items in the List.
     * @return the List of Items.
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Finds the elements in the List of Items which names are matched to passed argument.
     * @param key that should be equals to name of the Item.
     * @return The new List of Items containing only elements matched to passed string type value.
     */
    public List<Item> findByName(String key) {
        List<Item> tmpList = new ArrayList<>();
        for (Item value : items) {
            if (value != null && value.getName().equals(key)) {
                tmpList.add(value);
            }
        }
        return tmpList;
    }

    /**
     * Returns the Item element that is matched to passed string parameter.
     * @param id that should be equals to id in the Item.
     * @return An Item element.
     */
    public Item findById(String id) {
        for (Item value : items) {
            if (value != null && value.getId().equals(id)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Generates unique Id number.
     * @return The Id number.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RND.nextInt());
    }
}