package ru.job4j.h2generic.store;

/**
 * @param <T> is the name of type parameter.
 */
public interface Store<T extends Base> {
    /**
     * @param model to be added in the store.
     * @return added model.
     */
    T add(T model);

    /**
     * @param model to be updated in the store.
     * @return updated model.
     */
    T update(T model);

    /**
     * @param id of the model that should be deleted in the store.
     * @return true or false.
     */
    boolean delete(String id);
}