/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 * 乘法 '*'
 *
 * @author Keyto
 */
public class Multiply extends ArithmeticOperator {

    @Override
    public int getWeight() {
        return 2;
    }

    @Override
    public Fraction getValue() {
        return left.getValue().multiply(right.getValue());
    }

}
