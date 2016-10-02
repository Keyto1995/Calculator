/*
 * Copyright (C) 2016 Keyto
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * E-mail: Keyto1995@Outlook.com
 */
package calculator;

/**
 *
 * @author Keyto
 */
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

    public double doubleValue() {
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
