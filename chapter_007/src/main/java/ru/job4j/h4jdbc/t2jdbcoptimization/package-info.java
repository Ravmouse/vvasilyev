/**
 * Приложение считывает данные с TXT файла config, подключается к БД и отправляет запрос на добавление данных в БД,
 * после чего все данные запрашиваются из БД и заносятся в List.
 * Далее осуществляется конвертация Java объекта в XML файл, потом используется XSLT для генерации XML файла другого
 * формата. И наконец используется SAX парсинг для считывания данных из XML файла и подсчета конечной суммы.
 */
package ru.job4j.h4jdbc.t2jdbcoptimization;