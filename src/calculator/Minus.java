/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 * 减法 '-'
 *
 * @author Keyto
 */
public class Minus extends ArithmeticOperator {

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public Fraction getValue() {
        return left.getValue().subtract(right.getValue());
    }
    
}
