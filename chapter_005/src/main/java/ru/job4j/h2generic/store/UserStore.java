package ru.job4j.h2generic.store;

/**
 * @param <T> is the name of type parameter.
 * Обобщенный класс, который расширяет класс AbstractStore; в нем хранятся экз.класса User.
 */
public class UserStore<T extends Base> extends AbstractStore<T> {

    /**
     * @param size of the UserStore.
     */
    public UserStore(int size) {
        super(size);
    }

    /**
     * По id находится нужная модель (User) и в ней изменяется id.
     * @param model that the UserStore contains.
     * @param id to be replaced.
     */
    public void changeId(T model, String id) {
        for (int i = 0; i < this.getUserArray().getObjects().length; i++) {
            if (this.getUserArray().get(i).getId().equals(model.getId())) {
                this.getUserArray().get(i).setId(id);
                break;
            }
        }
    }
}