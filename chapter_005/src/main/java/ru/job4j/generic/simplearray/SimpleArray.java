package ru.job4j.generic.simplearray;
import java.util.Arrays;
import java.util.List;

/**
 * @param <T> Class with generic type parameter.
 */
public class SimpleArray<T> {
    /**
     * An array of Object elements.
     */
    private Object[] objects;
    /**
     * An index of the last added element.
     */
    private int index;

    /**
     * @param size of the array of Object elements.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * @param value to be added.
     */
    public void add(T value) {
        this.objects[index++] = value;
    }

    /**
     * @param newValue to be set.
     * @param position for newValue to be set.
     */
    public void update(T newValue, int position) {
        if ((position > -1) && (position < objects.length)) {
            objects[position] = newValue;
        }
    }

    /**
     * @param oldValue to be found.
     * @param newValue to be set.
     */
    public void update(T oldValue, T newValue) {
        List<Object> list = Arrays.asList(objects);
        if (list.contains(oldValue)) {
            list.set(list.indexOf(oldValue), newValue);
        }
        objects = list.toArray();
    }

    /**
     * @param value to be deleted.
     */
    public void delete(T value) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(value)) {
                System.arraycopy(objects, i + 1, objects, i, objects.length - i - 1);
                break;
            }
        }
    }

    /**
     * @param position of the Object element in the array.
     * @return the element of the array.
     */
    public T get(int position) {
        return (T) objects[position];
    }
}