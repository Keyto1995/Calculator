/*
 * Copyright (C) 2016 Keyto.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *
 * E-mail: Keyto1995@outlook.com
 */
package calculator;

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
