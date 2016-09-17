/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            if(null==this.right){
                throw new Exception("非法符号组合");
            }
            operator.setParent(this);
            right.setParent(operator);
            operator.setLeft(right);
            this.right = operator;

        }else{
            this.getParent().put(operator);
        }
        return operator;
    }

}
