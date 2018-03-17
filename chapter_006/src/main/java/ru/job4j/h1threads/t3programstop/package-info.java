/**
* В этом пакете создан класс с двумя потоками, один из которых отсчитывает время с момента начала работы приложения.
* Если время истекает, то этот поток останавливает работу 2-го потока, который в это время подсчитывает кол-во
* символов в тексте, введенным пользователем. Но, если нужно выйти из потока раньше отведенного времени, то 2-ой
* поток может остановить 1-ый поток, и тогда работа приложения завершится.
* ----------------------------------------------------------------------------------------------------------------------
* There's the class with two additional threads, one of them counts the time that past from the start of the app.
* If the time's up, the first thread stops the second thread's work. The second thread counts the number of symbols
* in a text is entered by a user. But if there's a need to quit the app's work before the defined time, then the second
* thread can stop the first thread's work.
* @author Vitaly Vasilyev (rav.energ@rambler.ru).
* @version 1.0
*/
package ru.job4j.h1threads.t3programstop;