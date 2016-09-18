/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

public class Pow extends ArithmeticOperator {

    @Override
    public int getWeight() {
        return 3;
    }

    @Override
    public Fraction getValue() {
        return left.getValue().pow(right.getValue()) ;
    }

}
