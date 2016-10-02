/*
 * Copyright (C) 2016 Keyto
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * E-mail: Keyto1995@Outlook.com
 */
package calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 模拟分数及其操作
 * 以消除double精度损失
 *
 * @author Keyto
 */
public class Fraction extends Number {

    private final BigInteger a;
    private final BigInteger b;

    public Fraction() {
        this(new BigInteger("0"), new BigInteger("1"));
    }

    public Fraction(long a) {
        this(a, 1);
    }

    Fraction(long a, long b) {
        this(BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    private Fraction(BigInteger a, BigInteger b) {
        if (b.signum() == 0) {
            throw new ArithmeticException("分母为零");
        }

        if (b.signum() == -1) {
            a = a.negate();
            b = b.negate();
        }

        BigInteger tmp = a.gcd(b);

        this.a = a.divide(tmp);
        this.b = b.divide(tmp);
    }

    /**
     * 绝对值操作
     *
     * @return (|this|)
     */
    public Fraction abs() {
        return (signum() == -1) ? negate() : this;
    }

    /**
     * 取反操作
     *
     * @return (-this)
     */
    public Fraction negate() {
        return new Fraction(a.negate(), b);
    }

    /**
     * 倒数运算
     *
     * @return (b/a)
     */
    public Fraction reciprocal() {
        switch (signum()) {
            case 0:
                throw new ArithmeticException("对 0 求倒数");
            case -1:
                return new Fraction(b.negate(), a.negate());
            case 1:
                return new Fraction(b, a);
            default:
                throw new ArithmeticException("this.signum() 返回意外值");
        }
    }

    /**
     * 判断符号
     *
     * 当负、零、正时输出-1、0、1
     *
     * @return
     */
    public int signum() {
        return a.signum();
    }

    /**
     * 加 操作
     *
     * @param augend
     * @return (this+augend)
     */
    public Fraction add(Fraction augend) {
        BigInteger tmp_a, tmp_b;

        tmp_a = a.multiply(augend.b).add(b.multiply(augend.a));
        tmp_b = b.multiply(augend.b);

        return new Fraction(tmp_a, tmp_b);
    }

    /**
     * 减 操作
     *
     * @param subtrahend
     * @return (this-subtrahend)
     */
    public Fraction subtract(Fraction subtrahend) {
        return add(subtrahend.negate());
    }

    /**
     * 乘 操作
     *
     * @param multiplicand
     * @return (this*multiplicand)
     */
    public Fraction multiply(Fraction multiplicand) {
        BigInteger tmp_a, tmp_b;

        tmp_a = a.multiply(multiplicand.a);
        tmp_b = b.multiply(multiplicand.b);

        return new Fraction(tmp_a, tmp_b);
    }

    /**
     * 除 操作
     *
     * @param divisor
     * @return (this/divisor)
     */
    public Fraction divide(Fraction divisor) {
        return multiply(divisor.reciprocal());
    }

    public Fraction pow(Fraction n) {
        if (n.isInteger()) {
            return this.pow(n.intValue());
        } else {
            throw new ArithmeticException("乘方操作暂不支持非整数次方");
        }
    }

    /**
     * 乘方 操作 (暂不支持非整数次方)
     *
     * @param n
     * @return this的n次方
     */
    public Fraction pow(int n) {
        if (n >= 0) {
            return new Fraction(a.pow(n), b.pow(n));
        } else {
            return new Fraction(b.pow(-n), a.pow(-n));
        }
    }

    /**
     * this 是整数
     *
     * @return <code> true </code>  是整数
     *          <code> false </code>    不是整数
     */
    public boolean isInteger() {
        return b.intValue() == 1;
    }

    @Override
    public String toString() {
        return a.toString() + " / " + b.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Fraction)) return false;

        Fraction otherFraction = (Fraction) other;
        return this.a.equals(otherFraction.a) && this.b.equals(otherFraction.b);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.a);
        hash = 41 * hash + Objects.hashCode(this.b);
        return hash;
    }

    @Override
    public int intValue() {
        //BigInteger.intValueExact() since 1.8
        return a.divide(b).intValueExact();

        //BigInteger.intValue() since 1.6
//        return a.divide(b).intValue();
    }

    @Override
    public long longValue() {
        //BigInteger.longValueExact() since 1.8
        return a.divide(b).longValueExact();

        //BigInteger.longValue() since 1.6
//        return a.divide(b).longValue();
    }

    @Override
    public float floatValue() {
        BigDecimal bd_a = new BigDecimal(a);
        BigDecimal bd_b = new BigDecimal(b);
        return bd_a.divide(bd_b).floatValue();
    }

    @Override
    public double doubleValue() {
        BigDecimal bd_a = new BigDecimal(a);
        BigDecimal bd_b = new BigDecimal(b);
        return bd_a.divide(bd_b).doubleValue();
    }

}
