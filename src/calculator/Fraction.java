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
public final class Fraction extends Number {

    private final BigInteger a;
    private final BigInteger b;

    /**
     * 常用分数常量
     */
    public static Fraction ZERO = new Fraction(0, 1);
    public static Fraction ONE = new Fraction(1, 1);
    public static Fraction TEN = new Fraction(10, 1);

    @Deprecated
    public Fraction(String val) {
        this(new BigDecimal(val));
    }

    @Deprecated
    public Fraction(BigDecimal val) {
        this(val.unscaledValue(), BigInteger.TEN.pow(val.scale()));
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
     * 把整数val转化为分数
     *
     * @param val
     * @return fraction==val
     */
    public static Fraction valueOf(long val) {
        return new Fraction(val);
    }

    /**
     * 把浮点数val转化为分数
     *
     * @param val
     * @return fraction==val
     */
    public static Fraction valueOf(double val) {
        return new Fraction(BigDecimal.valueOf(val));
    }

    /**
     * 把高精度浮点数val转化为分数
     *
     * @param val
     * @return fraction==val
     */
    public static Fraction valueOf(BigDecimal val) {
        if (val.scale() < 0) {
            val = val.setScale(0);
        }
        return new Fraction(val.unscaledValue(), BigInteger.TEN.pow(val.scale()));
    }

    /**
     * 把字符串val转化为分数
     *
     * @param val
     * @return fraction==val
     */
    public static Fraction valueOf(String val) {
        return Fraction.valueOf(new BigDecimal(val));
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

    //num是被开方数，n是开方次数,precision设置保留几位小数
    public static BigDecimal rootN_Decimal(BigInteger num, int n, int precision) {
        //预测值 A/k
        BigDecimal x = new BigDecimal(num.divide(BigInteger.valueOf(n)));
        BigDecimal x0 = BigDecimal.ZERO;

        //记录精度
        BigDecimal e = BigDecimal.ONE.scaleByPowerOfTen(-precision);
        //A=x^k
        BigDecimal A = new BigDecimal(num);
        BigDecimal k = new BigDecimal(n);

        while (x.subtract(x0).abs().compareTo(e) > 0) {
            x0 = x;
//            //公式：x'=x+(A/x^(k-1)-x)/k
//            x = x.add(A.divide(x.pow(n - 1), precision, BigDecimal.ROUND_FLOOR).subtract(x).divide(k, precision, BigDecimal.ROUND_FLOOR));
            //公式·改：x'=x+(A-(x^k))/(k*x^(k-1))
            x = x.add(A.subtract(x.pow(n)).divide(k.multiply(x.pow(n - 1)), precision, BigDecimal.ROUND_HALF_EVEN));
        }

        return x;
    }

    /**
     * 若this小于val则返回-1;
     * 若this等于val则返回0;
     * 若this大于val则返回1;
     *
     * @param val
     * @return
     */
    public int compareTo(Fraction val) {
        return this.subtract(val).signum();
    }

    /**
     * this 是整数
     *
     * @return <code> true </code> 是整数
     *         <code> false </code> 不是整数
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
        return a.divide(b).intValue();
    }

    @Override
    public long longValue() {
        return a.divide(b).longValue();
    }

    @Override
    public float floatValue() {
        if (a.signum() == 0) {
            return 0F;
        }

        BigDecimal bd_a = new BigDecimal(a);
        BigDecimal bd_b = new BigDecimal(b);

        int precision = b.divide(a).bitLength() / 3 + 10;
        return bd_a.divide(bd_b, precision, BigDecimal.ROUND_FLOOR).floatValue();
    }

    @Override
    public double doubleValue() {
        if (a.signum() == 0) {
            return 0;
        }

        BigDecimal bd_a = new BigDecimal(a);
        BigDecimal bd_b = new BigDecimal(b);

        int precision = b.divide(a).bitLength() / 3 + 18;
        return bd_a.divide(bd_b, precision, BigDecimal.ROUND_FLOOR).doubleValue();
    }

}
