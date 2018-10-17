package ru.job4j.h2http;

import java.util.List;

/**
 * Синглтон. Класс с проверками.
 */
public class ValidateService implements Validate { //Logic
    /**
     * Статическое поле.
     */
    private final static ValidateService VALIDATE = new ValidateService();
    /**
     * Ссылка на класс MemoryStore, где хранятся данные.
     */
    final MemoryStore store = MemoryStore.getInstance();

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
     * Создание нового User'а с параметрами запроса.
     * @param id id.
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     */
    @Override
    public void add(String id, String name, String login, String email, String createDate) {
        int userId = Integer.parseInt(id);
        store.add(new User(userId, name, login, email, createDate));
    }

    /**
     * По id находится User, затем у него изменяются все остальные его поля. Создание нового User не нужно.
     * @param id id.
     * @param name имя.
     * @param login логин.
     * @param email эл.почта.
     * @param createDate дата создания.
     * @throws VersionUserException исключение.
     */
    @Override
    public void update(String id, String name, String login, String email, String createDate)
            throws VersionUserException, NotExistedUserException {
        int userId = Integer.parseInt(id);
        final User user = this.findById(userId);
        checkUserVersion(userId);
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setCreateDate(createDate);
        store.update(userId, user);
    }

    /**
     * @param id id User'а, которого нужно удалить.
     * @throws VersionUserException исключение для 2-х потоков.
     * @throws NotExistedUserException если User'а с таким id не существует.
     */
    @Override
    public void delete(String id) throws VersionUserException, NotExistedUserException {
        int userId = Integer.parseInt(id);
        final User user = this.findById(userId); //Если User не найден, то пробрасывается исключение.
        checkUserVersion(userId);
        store.delete(user);
    }

    /**
     * Возвращается массив User'ов, чтобы было проще отобразить его с помощью Arrays.toString().
     * @return массив User'ов.
     */
    @Override
    public User[] findAll() {
        final List<User> usersList = store.findAll();
        final User[] arr = new User[usersList.size()];
        int i = 0;
        for (User u : usersList) {
            arr[i++] = u;
        }
        return arr;
    }

    /**
     * @param id номер User'а.
     * @return ссылку на найденного User'а.
     * @throws NotExistedUserException если User'а с таким id не существует.
     */
    @Override
    public User findById(int id) throws NotExistedUserException {
        User user = store.findById(id);
        if (user == null) {
            throw new NotExistedUserException("The user with such id is not existed!");
        }
        return user;
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
        int expect = store.users.get(id).getVersion().get();
        boolean done = store.users.get(id).getVersion().compareAndSet(expect, ++expect);
        if (!done) {
            throw new VersionUserException("The thread is not allowed to perform any actions on the user!");
        }
    }
}