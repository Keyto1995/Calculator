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
 * 括号父类
 *
 * @author Keyto
 */
public class Bracket extends CalculatorImpl {

    CalculatorImpl value;
    private boolean isFinish = false;

    @Override
    public Fraction getValue() {
        return value.getValue();
    }

    /**
     * 封闭右半括号
     *
     * @return 封闭成功true，封闭失败false
     */
    public boolean rightFinish() {
        return (isFinish) ? false : (isFinish = true);
    }

    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public boolean isValue() {
        return isFinish && value != null && value.isValue();
    }

    @Override
    public CalculatorImpl put(Figure figure) throws Exception {
        if(isFinish){
            throw new Exception(" ')' 后需要运算符");
        }
        this.value = figure;
        figure.setParent(this);

        return figure;
    }

    @Override
    public CalculatorImpl put(Bracket bracket) throws Exception {
        if (isFinish) {
            put(new Multiply()).put(bracket);
        } else {

            this.value = bracket;
            bracket.setParent(this);

        }
        return bracket;
    }

    @Override
    public CalculatorImpl put(ArithmeticOperator operator) throws Exception {
        if (isFinish) {
            this.getParent().put(operator);
        } else if (this.value == null) {
            if (operator instanceof Minus) {
                put(new Figure(0)).put(operator);
                
            } else {
                throw new Exception("错误的符号组合!");
            }
        } else {

            this.value.setParent(operator);
            operator.setLeft(this.value);
            operator.setParent(this);
            this.value = operator;

        }
        return operator;
    }

}
