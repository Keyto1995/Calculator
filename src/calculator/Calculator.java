/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Scanner;

/**
 *
 * @author Keyto
 */
public final class Calculator {

    String formula = "";

    boolean dirtySet;

    CalculatorImpl root;
    CalculatorImpl now;

    public Calculator() {
        init();
    }

    public Calculator(String formula) {
        setFormula(formula);
        init();
    }

    public void setFormula(String formula) {
        this.formula = formula;
        init();
    }

    private void init() {
        now = root = new RootCal();
        dirtySet = true;
    }

    private void buildTree() throws Exception {
        char[] charArray = formula.toCharArray();
        //确定式子不为空
        if (charArray.length == 0) {
            throw new Exception("请输入有效算数式子");
        }

        //开始建树
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '0':
                    put(new Figure(0));
                    break;
                case '1':
                    put(new Figure(1));
                    break;
                case '2':
                    put(new Figure(2));
                    break;
                case '3':
                    put(new Figure(3));
                    break;
                case '4':
                    put(new Figure(4));
                    break;
                case '5':
                    put(new Figure(5));
                    break;
                case '6':
                    put(new Figure(6));
                    break;
                case '7':
                    put(new Figure(7));
                    break;
                case '8':
                    put(new Figure(8));
                    break;
                case '9':
                    put(new Figure(9));
                    break;
                case '.':
                    if (now instanceof Figure) {
                        Figure nowFigure = (Figure) now;
                        nowFigure.setDot();
                    } else {
                        throw new Exception("小数点位置错误");
                    }
                    break;
                case '(':
                    put(new Bracket());
                    break;
                case ')':
                    if (now instanceof Bracket) {
                        Bracket bracket = (Bracket) now;
                        if (!bracket.isFinish()) {
                            throw new Exception("存在空括号对!");
                        }
                    }

                    while (true) {
                        CalculatorImpl parent = findParent();

                        if (null != parent) {
                            setNow(parent);
                            if (parent instanceof Bracket) {
                                Bracket bracket = (Bracket) parent;
                                if (bracket.rightFinish()) {
                                    break;
                                }
                            }
                        } else {
                            throw new Exception("存在未封闭括号");
                        }
                    }
                    break;
                case '+':
                    put(new Plus());
                    break;
                case '-':
                    put(new Minus());
                    break;
                case '*':
                    put(new Multiply());
                    break;
                case '/':
                    put(new Div());
                    break;
                case '^':
                    put(new Pow());
                    break;

                default:
                    throw new Exception("不识别字符： " + charArray[i]);
            }
        }

        dirtySet = false;
    }

    private void put(Figure figure) throws Exception {
        CalculatorImpl newNow = now.put(figure);
        setNow(newNow);
    }

    private void put(Bracket bracket) throws Exception {
        CalculatorImpl newNow = now.put(bracket);
        setNow(newNow);
    }

    private void put(ArithmeticOperator operator) throws Exception {
        CalculatorImpl newNow = now.put(operator);
        setNow(newNow);
    }

    private void setNow(CalculatorImpl cal) {
        this.now = cal;
    }

    private CalculatorImpl findParent() {
        return (isHead()) ? null : now.getParent();
    }

    private boolean isHead() {
        return now == root;
    }

    private boolean isValue() {
        return root.isValue();
    }

    public double doubleValue() throws Exception{
        return getValue().doubleValue();
    }

    public Fraction getValue() throws Exception {
        if (dirtySet) {
            buildTree();
            if (!isValue()) {
                throw new Exception("算式格式错误");
            }
        }
        return root.getValue();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator cal = new Calculator();
        while (true) {
            System.out.println("请输入算式：");
            String formula = sc.nextLine();
            cal.setFormula(formula);
            try {
                System.out.println(">> " + cal.doubleValue());
            } catch (Exception ex) {
                System.out.println(ex);
//                ex.printStackTrace();
            }
            System.out.println();

        }
    }

}
