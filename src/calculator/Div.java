/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 * 除法 '/'
 *
 * @author Keyto
 */
public class Div extends ArithmeticOperator {

    @Override
    public int getWeight() {
        return 2;
    }

    @Override
    public double getValue() {
        return left.getValue() / right.getValue();
    }


}
