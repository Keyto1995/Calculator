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
