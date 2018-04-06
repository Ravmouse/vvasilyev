package ru.job4j.h3monitorsynchronized.t2userstorage;
/**
 * Интерфейс.
 */
public interface UserStorage {
    /**
     * @param user - элемент для добавления.
     * @return true, если элемент удалось добавить, и false, если - нет.
     */
    boolean add(User user);

    /**
     * @param user - элемент для изменения.
     * @param amount - новая сумма для элемента user.
     * @return true, если такой элемент существует, и его удалось изменить, и false, если - нет.
     */
    boolean update(User user, int amount);

    /**
     * @param user - элемент для удаления.
     * @return true, если такой элемент существует, и его удалось удалить, и false, если - нет.
     */
    boolean delete(User user);

    /**
     * @param fromId - id пользователя, с которого нужно перевести сумму.
     * @param toId - id пользователя, на которого нужно перевести сумму.
     * @param amount - сумма для перевода.
     * @return true, если удалось осуществить перевод и false, если - нет.
     * @throws InterruptedException в случае возникновения исключения.
     */
    boolean transfer(int fromId, int toId, int amount) throws InterruptedException;
}