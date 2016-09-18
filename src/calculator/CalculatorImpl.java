/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
