package ru.job4j.greedyalgorithm;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Vitaly Vasilyev, date: 23.02.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class GreedyAlgorithm {
    /**
     * Максимальный вес одного блина.
     */
    private int maxOne = 20;
    /**
     * Максимальное кол-во блинов в массиве.
     */
    private int count = 1000;
    /**
     * Максимальный вес всех блинов.
     */
    private int maxAll = 10_000;
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param weights массив весов.
     * @return максимальный вес для данного массива.
     */
    public int countMaxWeight(int[] weights) {
        Arrays.sort(weights);                       //Сначала сортируем массив.
        if (weights[weights.length -1] > maxOne) {  //Смотрим вес последнего элемента.
            LOGGER.warn(String.format("Вес одного из блинов превышает %d фунтов", maxOne));
            return -1;
        }
        if (weights.length > count) {               //Смотрим кол-во всех элементов в массиве.
            LOGGER.warn(String.format("Кол-во элементов превышает %d блинов", count));
            return -1;
        }
        int sum = IntStream.of(weights).sum();      //Получаем сумму всех элементов в массиве.
        if (sum > maxAll) {                         //Смотрим на общую сумму всех весов.
            LOGGER.warn(String.format("Общий вес превышает %d фунтов", maxAll));
            return -1;
        }

        List<Integer> wPlates = IntStream.of(weights).boxed().collect(Collectors.toList()); //Преобразуем массив к List.

        int index = 0, tempSum = sum;   //Доп. переменная tempSum нужна чтобы получить новую ЧЕТНУЮ сумму эл-тов, если сумма нечетная.
        int removed = 0;                //Для удаленного эл., чтобы потом его можно было добавить в список "невошедших".
        while (tempSum % 2 != 0) {
            tempSum = sum - wPlates.get(index++);   //Если сумма нечетная, последовательно отнимаем от нее эл-ты, начиная
        }                                           //с самого малого.
        if (index != 0) {
            removed = wPlates.remove(--index);      //Как только сумма становится четной, удаляем ненужный эл.
        }
        int oneSideWeight = tempSum / 2; //Теперь есть новая сумма - четная, если ранее была нечетная. Получаем половину.
        if (wPlates.get(wPlates.size() - 1) > oneSideWeight) {
            LOGGER.warn("Невозможно разделить имеющееся кол-во блинов!");
            return -1;
        }

        List<Integer> left = new ArrayList<>();
        int leftHalfMaxWeight = getElementsForOneSide(oneSideWeight, wPlates, left);

        List<Integer> right = new ArrayList<>();
        int rightHalfMaxWeight = getElementsForOneSide(oneSideWeight, wPlates, right);

        if (removed != 0) {
            wPlates.add(removed);
        }
        LOGGER.info("Блины, не вошедшие в разделение: " + wPlates);
        LOGGER.info("Слева: " + left);
        LOGGER.info("Справа: " + right);
        return leftHalfMaxWeight + rightHalfMaxWeight;
    }

    /**
     * @param oneSide макс. вес для одной стороны после деления пополам.
     * @param total все весы для штанги.
     * @return весы для одной из сторон.
     */
    private int getElementsForOneSide(int oneSide, List<Integer> total, List<Integer> side) {
        int result = 0;
        int i = total.size();
        while ((i != 0) && (result != oneSide)) {   //Идем от конца к началу. Или пока сумма с конца не будет равна oneSide.
            int k = total.get(--i);
            result += k;                            //Каждый раз увеличиваем сумму с конца на значение каждого эл.
            if (result > oneSide) {                 //Проверяем, сумма с конца не должна превышать oneSide.
                result -= total.get(i);             //Если превышает, то отнимаем последний эл.
            } else {
                side.add(k);                        //Если не превышает, то заполняем переданный список side
                total.remove(i);                    //и удаляем эл. из основного списка total.
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] array = {3,4,3,3,2};
//        int[] array = {2,2,4,4,5,6};
//        int[] array = {1,2,3,4,5,6};
//        int[] array = {1,1,3,4,4,5,5,7,9,9};
//        int[] array = {1,2};
//        int[] array = {1,3,4,4,5,7,8,8,9};
        int[] array = {2,2,2,4,4,4,7,8};
//        int[] array = {1,2,3,6};
        int max = new GreedyAlgorithm().countMaxWeight(array);
        LOGGER.info("Максимально доступный вес: " + max);
    }
}