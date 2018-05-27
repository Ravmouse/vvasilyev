/**
 * Игра бомбермен.
 * 1. Есть игровое поле. Представляющее из себя массив ReentrantLock[][] board. Есть две нити. Нить эмулирует
 * поведение героя. То есть герой стоит на клетке board. Клетка в этом случае должна быть заблокирована lock.lock();
 * Герой должен каждую секунду двигаться на новую клетку. При движении надо занять новую клетку, т.е. tryLock() - если
 * не получилось в течении 500 мс. то изменить движение на другую клетку. Избежать появление deadlock. Поле board при
 * движении героя не должно блокироваться целиком. Блокируется только ячейка. В коде использовать только immutable
 * объекты, т.е. все поле обозначить как final.
 * @version 1.0
 */
package ru.job4j.h6testtask.t1bomberman;