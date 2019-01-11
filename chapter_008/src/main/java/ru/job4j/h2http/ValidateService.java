package ru.job4j.h2http;

import ru.job4j.h7mockito.DBStore;
import ru.job4j.h6filter.Role;
import java.util.List;

/**
 * Синглтон. Класс с проверками.
 */
public class ValidateService implements Validate { //Logic
    /**
     * Статическое поле - ссылка на конструктор  этого класса.
     */
    private static final ValidateService VALIDATE = new ValidateService();
    /**
     * Ссылка на класс, откуда осуществляется подключение к БД.
     */
    private final Store store = DBStore.getInstance();

    /**
     * Приватный конструктор.
     */
    private ValidateService() {
    }

    /**
     * @return возвращает ссылку на экземпляр этого класса.
     */
    public static ValidateService getInstance() {
        return VALIDATE;
    }

    /**
     * @param list список строк для добавления данных в хранилище.
     */
    @Override
    public void add(final List<String> list) {
        store.add(new User(list.get(0),
                           list.get(1),
                           list.get(2),
                           list.get(3),
                           list.get(4),
                           list.get(5),
                           list.get(6),
                           new Role(list.get(7))));
    }

    /**
     * @param id   номер юзера. Id проверять не надо, т.к. юзер всегда присутствует на экране.
     * @param list список строк для изменения данных в хранилище.
     */
    @Override
    public void update(int id, final List<String> list) {
        store.update(new User(id, list.get(0),
                                  list.get(1),
                                  list.get(2),
                                  list.get(3),
                                  list.get(4),
                                  list.get(5),
                                  list.get(6),
                                  new Role(list.get(7))));
    }

    /**
     * @param id номер, по которому удаляется строка из хранилища. Id проверять не надо, т.к. номер юзера всегда > 0.
     */
    @Override
    public void delete(int id) {
        store.delete(new User(id));
    }

    /**
     * @return список юзеров.
     */
    @Override
    public List<User> findAll() {
        return store.findAll();
    }

    /**
     * @param id номер юзера для нахождения в хранилище.
     * @return юзера по его номеру.
     */
    @Override
    public User findById(int id) {
        return store.findById(id);
    }

    /**
     * @return список ролей.
     */
    @Override
    public List<Role> findAllRoles() {
        return store.findAllRoles();
    }

    /**
     * @param login    логин юзера.
     * @param password пароль юзера.
     * @return роль юзера.
     */
    @Override
    public Role findRole(String login, String password) {
        Role result;
        result = store.findRole(new User(login, password));
        return result;
    }

    /**
     * 1. Сначала берется значение AtomicInteger и сохраняется в локальной переменной expect.
     * 2. Потом еще раз берется значение AtomicInteger и сравнивается со значением лок.переменной expect.
     * 3. Если значения совпадают, то в AtomicInteger заносится инкрементированное значение expect и возвращ. true.
     * 4. Если значения не совпадают, то возвращ. false и генерируется исключение.
     * @param id значение User'а, которого нужно атомарно проверить.
     * @throws VersionUserException если 1-ый поток уже обновил значение User.
     */
    private void checkUserVersion(int id) throws VersionUserException {
        int expect = store.findById(id).getVersion().get();
        boolean done = store.findById(id).getVersion().compareAndSet(expect, ++expect);
        if (!done) {
            throw new VersionUserException("The thread is not allowed to perform any actions on the user!");
        }
    }
}