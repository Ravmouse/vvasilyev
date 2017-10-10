package ru.job4j.generic.store;

/**
 * @param <T> is the name of type parameter.
 * Обобщенный класс, который расширяет класс AbstractStore; в нем хранятся экз.класса Role.
 */
public class RoleStore<T extends Base> extends AbstractStore<T> {

    /**
     * @param size of the RoleStore.
     */
    public RoleStore(int size) {
        super(size);
    }

    /**
     * По id находится нужная модель (User) и к ее id добавляется idAdd.
     * @param model that is consisted in the UserStore.
     * @param idAdd that should be added to the User's existed id.
     */
    public void addIdToExisted(T model, String idAdd) {
        for (int i = 0; i < this.getUserArray().getObjects().length; i++) {
            if (this.getUserArray().get(i).getId().equals(model.getId())) {
                final String str = this.getUserArray().get(i).getId();
                StringBuilder sb = new StringBuilder(str);
                sb.append(idAdd);
                this.getUserArray().get(i).setId(sb.toString());
                break;
            }
        }
    }
}