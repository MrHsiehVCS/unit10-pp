package u10pp;
import java.math.BigInteger;

public class RecursiveMath {

    /**
     * Precondition - exponent is non-negative
     */
    public static double pow(double base, int exponent) {
        if (exponent == 0) return 1;
        return pow(base, exponent-1) * base; 
    }

    /**
     * Precondition - n is non-negative
     */
    public static BigInteger getFactorial(int n) {
        if(n == 0) {
            return BigInteger.ONE;
        }
        return getFactorial(n-1).multiply(BigInteger.valueOf(n));
    }


    /**
     * Precondition - n is positive
     */
    public static long getFibonacciNumber(int n) {
        if(n <= 2) {
            return 1;
        }

        long fib = getFibonacciNumber(n-1) + (getFibonacciNumber(n-2));
        return fib;
    }

}
