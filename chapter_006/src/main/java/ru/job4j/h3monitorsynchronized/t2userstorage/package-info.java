/**
 * 2. Класс хранилища пользователей UserStorage [#1104].
 * Создать класс - структуру данных для хранение пользователей UserStorage.
 * Хранилище должно иметь методы boolean add (User user), boolean update(User user), boolean delete(User user)
 * и особый метод transfer(int fromId, int toId, int amount).
 * Структура данных должна быть потокобезопасная; в структуре User Должны быть поля int id, int amount.
 * amount - это сумма денег на счете пользователя.
 * @version 1.0
 */
package ru.job4j.h3monitorsynchronized.t2userstorage;