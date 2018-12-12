package ru.job4j.h2http;
import ru.job4j.h4jsp.DBStore;
import ru.job4j.h6filter.Role;
import java.util.List;

/**
 * Синглтон. Класс с проверками.
 */
public class ValidateService implements Validate { //Logic
    /**
     * Статическое поле.
     */
    private static final ValidateService VALIDATE = new ValidateService();
    /**
     * Ссылка на класс MemoryStore, где хранятся данные.
     * Ссылка на класс, откуда осуществляется подключение к БД.
     */
//    private final Store store = MemoryStore.getInstance();
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
     * @throws NotExistedUserException исключение.
     */
    @Override
    public void add(final List<String> list) throws NotExistedUserException {
        if (list != null) {
            store.add(list, getRoleNumber(list));
        }
    }

    /**
     * @param id   номер юзера.
     * @param list список строк для изменения данных в хранилище.
     * @throws NotExistedUserException исключение.
     */
    @Override
    public void update(int id, final List<String> list) throws NotExistedUserException {
        if (list != null) {
            store.update(id, list, getRoleNumber(list));
        }
    }

    /**
     * Проверяет, нет ли пустых строк в list. Если есть, то пробрасывется исключение.
     * В зависимости от того какое название роли в последней строке list, возвращается порядковый номер роли из БД roles.
     * @param list список строк для изменения данных в хранилище.
     * @return порядковый номер роли.
     * @throws NotExistedUserException исключение.
     */
    private int getRoleNumber(final List<String> list) throws NotExistedUserException {
        int i = 0; //Счетчик количества элементов в list.
        int number = 0; //Номер для таблицы users, которая ссылается на таблицу roles.
        for (String s : list) {
            if (s.equals("")) {
                throw new NotExistedUserException("EmptyCreate");
            }
            if (i++ == 5) {
                switch (s) {
                    case "Administrator": number = 1;
                        break;
                    case "Anonymous": number = 2;
                        break;
                    case "Moderator": number = 3;
                        break;
                    default: number = 4;
                        break;
                }
            }
        }
        list.remove(i - 1); //Удалить из list последний элемент с названием роли.
        return number;
    }

    /**
     * @param id номер, по которому удаляется строка из хранилища.
     * @throws VersionUserException исключение.
     * @throws NotExistedUserException исключение.
     */
    @Override
    public void delete(int id) throws VersionUserException, NotExistedUserException {
        if (id > 0) {
            store.delete(id);
        }
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
     * @throws NotExistedUserException исключение.
     */
    @Override
    public User findById(int id) throws NotExistedUserException {
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
     * @throws NotExistedUserException исключение.
     */
    @Override
    public Role findRoleByLoginPassword(String login, String password) throws NotExistedUserException {
        Role result;
        if (login != null && password != null) {
            result = store.findRoleByLoginPassword(login, password);
        } else {
            throw new NotExistedUserException("Invalid login and (-or) password");
        }
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