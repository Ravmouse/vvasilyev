package ru.job4j.generic.store;
import ru.job4j.generic.simplearray.SimpleArray;

/**
 * @param <T> is the name of type parameter.
 * Реализует интерфейс Store; здесь переопределяются все методы, которые объявляются в интерфейсе Store.
 */
public class AbstractStore<T extends Base> implements Store<T> {
    /**
     * SimpleArray class that contains the array of Object elements.
     */
    private SimpleArray<T> userArray;

    /**
     * @param size for SimpleArray class object.
     */
    public AbstractStore(int size) {
        this.userArray  = new SimpleArray<>(size);
    }

    /**
     * @param model to be added in the store.
     * @return
     */
    @Override
    public T add(T model) {
        if (model != null) {
            userArray.add(model);
        }
        return model;
    }

    /**
     * @param model to be updated in the store.
     * @return
     */
    @Override
    public T update(T model) {
        if (model != null) {
            for (int i = 0; i < userArray.getObjects().length; i++) {
                if (userArray.get(i).getId().equals(model.getId())) {
                    userArray.update(model, i);
                    return model;
                }
            }
        }
        return model;
    }

    /**
     * @param id of the model that must be deleted.
     * @return true if the deleting is completed successfully and false otherwise.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (id != null) {
            for (int i = 0; i < userArray.getObjects().length; i++) {
                if (userArray.get(i).getId().equals(id)) {
                    userArray.delete(i);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @return the reference to instance of SimpleArray class.
     */
    public SimpleArray<T> getUserArray() {
        return userArray;
    }
}