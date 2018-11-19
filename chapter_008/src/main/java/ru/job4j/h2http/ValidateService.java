package ru.job4j.h2http;
import ru.job4j.h4jsp.DBStore;
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
        for (String s : list) {
            if (s.equals("")) {
                throw new NotExistedUserException("EmptyCreate");
            }
        }
        store.add(list);
    }

    /**
     * @param id   номер.
     * @param list список строк для изменения данных в хранилище.
     * @throws VersionUserException исключение.
     * @throws NotExistedUserException исключение.
     */
    public void update(int id, final List<String> list) throws NotExistedUserException {
        for (String s : list) {
            if (s.equals("")) {
                throw new NotExistedUserException("EmptyUpdate");
            }
        }
        store.update(id, list);
    }

    /**
     * @param id номер, по которому удаляется строка в хранилище.
     * @throws VersionUserException исключение.
     * @throws NotExistedUserException исключение.
     */
    @Override
    public void delete(int id) throws VersionUserException, NotExistedUserException {
        store.delete(id);
    }

    /**
     * @return список юзеров.
     */
    @Override
    public List<User> findAll() {
        return store.findAll();
    }

    /**
     * @param id номер.
     * @return юзера по его номеру.
     * @throws NotExistedUserException исключение.
     */
    @Override
    public User findById(int id) throws NotExistedUserException {
        return store.findById(id);
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