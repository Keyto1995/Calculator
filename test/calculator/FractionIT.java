/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Keyto
 */
public class FractionIT {

    public FractionIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of abs method, of class Fraction.
     */
    @Test
    public void testAbs() {
        System.out.println("abs");
        Fraction instance = new Fraction(-12);
        Fraction expResult = new Fraction(12);
        Fraction result = instance.abs();
        assertEquals(expResult, result);

    }

    /**
     * Test of negate method, of class Fraction.
     */
    @Test
    public void testNegate() {
        System.out.println("negate");
        Fraction instance = new Fraction(-16);
        Fraction expResult = new Fraction(16);
        Fraction result = instance.negate();
        assertEquals(expResult, result);

    }

    /**
     * Test of reciprocal method, of class Fraction.
     */
    @Test
    public void testReciprocal() {
        System.out.println("reciprocal");
        Fraction instance = new Fraction(6,12);
        Fraction expResult = new Fraction(12,6);
        Fraction result = instance.reciprocal();
        assertEquals(expResult, result);

    }

    /**
     * Test of add method, of class Fraction.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Fraction augend =new Fraction(31);
        Fraction instance = new Fraction(11);
        Fraction expResult = new Fraction(42);
        Fraction result = instance.add(augend);
        assertEquals(expResult, result);

    }

    /**
     * Test of subtract method, of class Fraction.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Fraction subtrahend = new Fraction(21);
        Fraction instance = new Fraction(12);
        Fraction expResult = new Fraction(-9);
        Fraction result = instance.subtract(subtrahend);
        assertEquals(expResult, result);

    }

    /**
     * Test of multiply method, of class Fraction.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        Fraction multiplicand = new Fraction(5);
        Fraction instance = new Fraction(2);
        Fraction expResult = new Fraction(10);
        Fraction result = instance.multiply(multiplicand);
        assertEquals(expResult, result);

    }

    /**
     * Test of divide method, of class Fraction.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        Fraction divisor = new Fraction(15);
        Fraction instance = new Fraction(2);
        Fraction expResult = new Fraction(2, 15);
        Fraction result = instance.divide(divisor);
        assertEquals(expResult, result);

    }

    /**
     * Test of intValue method, of class Fraction.
     */
    @Test
    public void testIntValue() {
        System.out.println("intValue");
        Fraction instance = new Fraction(21);
        int expResult = 21;
        int result = instance.intValue();
        assertEquals(expResult, result);

    }

    /**
     * Test of longValue method, of class Fraction.
     */
    @Test
    public void testLongValue() {
        System.out.println("longValue");
        Fraction instance = new Fraction(12222);
        long expResult = 12222L;
        long result = instance.longValue();
        assertEquals(expResult, result);

    }

    /**
     * Test of floatValue method, of class Fraction.
     */
    @Test
    public void testFloatValue() {
        System.out.println("floatValue");
        Fraction instance = new Fraction(1,5);
        float expResult = 0.2F;
        float result = instance.floatValue();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of doubleValue method, of class Fraction.
     */
    @Test
    public void testDoubleValue() {
        System.out.println("doubleValue");
        Fraction instance = new Fraction(1,2);
        double expResult = 0.5;
        double result = instance.doubleValue();
        assertEquals(expResult, result, 0.0);

    }

}
