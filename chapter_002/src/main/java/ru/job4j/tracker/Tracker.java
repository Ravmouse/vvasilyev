package ru.job4j.tracker;
import java.util.Random;
import ru.job4j.tracker.item.Item;

/**
 * Class Tracker.
 */
public class Tracker {
    /**
     * The array of Item variables.
     */
    private Item[] items = new Item[100];
    /**
     * The position of current Item element in the array.
     */
    private int position = 0;
    /**
     * The Random constant.
     */
    private static final Random RND = new Random();

    /**
     * The method adds the Item into array of Item variables.
     * @param item An argument of Item type.
     * @return ...
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * The method replaces the Item that is found in the array of Item variables.
     * @param item An argument of Item type to be replaced in the array.
     */
    public void update(Item item) {
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * The method deletes the Item that is found in the array of Item variables.
     * @param item An argument of Item type to be deleted in the array.
     */
    public void delete(Item item) {
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = null;
                if (i != position - 1) {
                    System.arraycopy(items, i + 1, items, i, position - (i + 1));
                    position--;
                    break;
                } else {
                    position--;
                }
            }
        }
    }

    /**
     * The method returns all Item elements in the array of Item variables.
     * @return New array of Item variables.
     */
    public Item[] findAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * The method finds the elements in the array of Item variables which names are similar to passed argument.
     * @param key A string type variable.
     * @return The new array containing only elements matched passed string type value.
     */
    public Item[] findByName(String key) {
        Item[] result = null;
        int[] tmpArr = new int[position];
        int i, count = 0;
        for (i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                tmpArr[count++] = i; //Должен пройти по всему массиву items.
            }                        //Не делать break после первого нахождения key,
        }                            //потому что похожие key могут быть найдены далее.
        result = new Item[count];
        for (i = 0; i < count; i++) {
            result[i] = items[tmpArr[i]];
        }
        return result;
    }

    /**
     * The method returns the Item element that is matched passed string type value.
     * @param id A string type variable.
     * @return An Item element.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * The method generates unique Id number.
     * @return The Id number.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RND.nextInt());
    }
}