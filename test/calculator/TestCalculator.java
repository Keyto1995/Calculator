package calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCalculator {

    static Calculator calculator = new Calculator();

    String expected = "";
    String input = "";

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"12.0", "12"},
            {"1.1", "1.1"},
            {"-13.0", "-13"},
            {"83.0", "91-8"},
            {"15.0", "-(-15)"},
            {"54.0", "(36-9)(-4+6)"},
            {"100.0", "25(6-2)"},
            {"-4.0", "12-8*2"},
            {"9.0", "12*3/4"},
            {"-10.0", "-(2+3)*2"},
            {"-2.0", "(-(-(2-3)*2)*(3-2))"},
            {"-30.0", "-5*6"},
            {"0.5", "5/10"},
            {"-0.5", "-1/2"},
            {"14.4", "1.2*12"},
            {"14.4", "12*12/10"},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
//            {"", ""},
            {"java.lang.Exception: 非法符号组合", "9+*8"},
            {"java.lang.Exception: 算式格式错误", "9+-8"},
            {"java.lang.Exception: 算式格式错误", "-(-9"},
            {"java.lang.Exception: 一个数中存在多个小数点", "1.3.5"},
            {"java.lang.Exception: 请输入有效算数式子", ""}
        });
    }

    public TestCalculator(String expected, String input) {
        this.expected = expected;
        this.input = input;
    }

    @Test
    public void testResult() {
        String result = getStrResult();

        assertEquals(expected, result);
    }

    private String getStrResult() {
        calculator.setFormula(input);
        double value;
        String strResult;
        try {
            value = calculator.getValue();
            strResult = String.valueOf(value);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            strResult = ex.toString();
        }
        return strResult;
    }

}
