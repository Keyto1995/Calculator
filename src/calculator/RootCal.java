/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

public class RootCal extends CalculatorImpl {

    private CalculatorImpl value;

    @Override
    public CalculatorImpl getParent() {
        assert true : "在Root节点寻找parent";
        return null;
    }

    @Override
    public void setParent(CalculatorImpl parent) {
        assert true : "在Root节点设置parent";
    }

    public double doubleValue(){
        return getValue().doubleValue();
    }

    @Override
    public Fraction getValue() {
        return value.getValue();
    }

    @Override
    public boolean isValue() {
        return null != value && value.isValue();
    }

    @Override
    public CalculatorImpl put(Figure figure) throws Exception {
        value = figure;
        figure.setParent(this);
        return figure;
    }

    @Override
    public CalculatorImpl put(Bracket bracket) throws Exception {
        value = bracket;
        bracket.setParent(this);
        return bracket;
    }

    @Override
    public CalculatorImpl put(ArithmeticOperator operator) throws Exception {
        if (null == value) {
            if (operator instanceof Minus) {
                this.value = operator;
                operator.setParent(this);
                Figure zero = new Figure(0);
                zero.setParent(operator);
                operator.setLeft(zero);

            } else {
                throw new Exception("第一个运算符号错误!");
            }
        } else {
            operator.setParent(this);
            operator.setLeft(value);
            value.setParent(operator);
            this.value = operator;

        }
        return operator;
    }

}
