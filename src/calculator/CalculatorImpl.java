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
public abstract class CalculatorImpl implements CalculatorInterface {

    private CalculatorImpl parent;

    public CalculatorImpl getParent() {
        return parent;
    }

    public void setParent(CalculatorImpl parent) {
        this.parent = parent;
    }

    public abstract CalculatorImpl put(Figure figure) throws Exception;

    public abstract CalculatorImpl put(Bracket bracket) throws Exception;

    public abstract CalculatorImpl put(ArithmeticOperator operator) throws Exception;

}
