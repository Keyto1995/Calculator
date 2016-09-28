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

public abstract class ArithmeticOperator extends CalculatorImpl {

    protected CalculatorImpl left;
    protected CalculatorImpl right;

    public abstract int getWeight();

    /**
     * @return the left
     */
    public CalculatorImpl getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(CalculatorImpl left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public CalculatorImpl getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(CalculatorImpl right) {
        this.right = right;
    }

    @Override
    public boolean isValue() {
        return getLeft() != null && getRight() != null && getLeft().isValue() && getRight().isValue();
    }

    @Override
    public CalculatorImpl put(Figure figure) throws Exception {
        figure.setParent(this);
        this.right = figure;
        return figure;
    }

    @Override
    public CalculatorImpl put(Bracket bracket) throws Exception {
        bracket.setParent(this);
        this.right = bracket;
        return bracket;
    }

    @Override
    public CalculatorImpl put(ArithmeticOperator operator) throws Exception {
        if (operator.getWeight() > this.getWeight()) {
            if (null == this.right) {
                throw new Exception("非法符号组合");
            }
            operator.setParent(this);
            right.setParent(operator);
            operator.setLeft(right);
            this.right = operator;

        } else {
            this.getParent().put(operator);
        }
        return operator;
    }

}
