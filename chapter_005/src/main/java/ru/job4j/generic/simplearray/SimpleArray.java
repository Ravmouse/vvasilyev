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
     * Добавление элемента происходит в конец массива.
     */
    public void add(T value) {
        this.objects[index++] = value;
    }

    /**
     * @param value to be set.
     * @param position for newValue to be set.
     * Обновление элемента осуществляется при наличии значения и индекса, куда нужно положить новое значение.
     */
    public void update(T value, int position) {
        if ((position > -1) && (position < objects.length)) {
            objects[position] = value;
        }
    }

    /**
     * @param oldValue to be replaced.
     * @param newValue to be put in the array.
     * Этот метод можно использовать тогда, когда переопределен equals() для объекта типа Т,
     * т.е. по какому принципу (или по каким полям) oldValue д.б. найден среди элементов массива.
     */
    public void update(T oldValue, T newValue) {
        List<Object> list = Arrays.asList(objects);
        if (list.contains(oldValue)) {
            list.set(list.indexOf(oldValue), newValue);
        }
        objects = list.toArray();
    }

    /**
     * @param position of the element that should be deleted.
     * Если известна позиция элемента для удаления.
     */
    public void delete(int position) {
        System.arraycopy(objects, position + 1, objects, position, objects.length - position - 1);
        index--;
    }

    /**
     * @param value of the element that should be deleted.
     * Если позиция неизвестна, то элемент нужно сначала найти.
     */
    public void delete(T value) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(value)) {
                System.arraycopy(objects, i + 1, objects, i, objects.length - i - 1);
                index--;
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

    /**
     * @return the ref to the array of Object elements.
     */
    public Object[] getObjects() {
        return objects;
    }
}