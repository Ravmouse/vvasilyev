package ru.job4j.calculator;
/**
* ʊ���� Calculator.
*
* @author Vitaly Vasilyev (rav.energ@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class Calculator {
    /**
    * ����� �������� ��������� ����������.
    */
    private double result;

    /**
    * �������� ���� �����.
    * @param first - first
    * @param second - second
    */
    void add(double first, double second) {
        this.result = first + second;
    }

    /**
    * ��������� ���� �����.
    * @param first - first
    * @param second - second
    */
    void sub(double first, double second) {
        this.result = first - second;
    }

    /**
    * ������� ������� ����� �� ������ �����.
    * @param first - first
    * @param second - second
    */
    void div(double first, double second) {
        this.result = first / second;
    }

    /**
    * ��������� ������� ����� �� ������ �����.
    * @param first - first
    * @param second - second
    */
    void mult(double first, double second) {
        this.result = first * second;
    }

    /**
    * ���������� ��������� �������������� ��������.
    * @return double
    */
    public double getResult() {
        return this.result;
    }
}