/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 * 数字
 *
 * @author Keyto
 */
public class Figure extends CalculatorImpl {

    private static Fraction ten = new Fraction(10);

    Fraction value;

    int dot = -1;

    public Figure(int value) {
        this.value = new Fraction(value);
    }

    void merge(Figure figure) {
        Fraction tmp = figure.value;
        if (dot > -1) {
            dot += 1;

            for (int i = 0; i < dot; i++) {
                tmp = tmp.divide(ten);
            }

        } else {
            value = value.multiply(ten);
        }

        value = value.add(tmp);

    }

    void setDot() throws Exception {
        if (dot == -1) {
            dot = 0;
        } else {
            throw new Exception("一个数中存在多个小数点");
        }
    }

    @Override
    public Fraction getValue() {
        return value;
    }

    @Override
    public boolean isValue() {
        return true;
    }

    @Override
    public CalculatorImpl put(Figure figure) throws Exception {
        merge(figure);
        return this;
    }

    @Override
    public CalculatorImpl put(Bracket bracket) throws Exception {
        put(new Multiply()).put(bracket);
        return bracket;
    }

    @Override
    public CalculatorImpl put(ArithmeticOperator operator) throws Exception {
        this.getParent().put(operator);
        return operator;
    }

}
