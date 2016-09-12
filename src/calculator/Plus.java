/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 * 加法 '+'
 *
 * @author Keyto
 */
public class Plus extends ArithmeticOperator {

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public double getValue() {
        return left.getValue() + right.getValue();
    }



}