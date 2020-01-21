package com.tryout.generalPuzzles.primitiveTypes;

public class n5_6_divide {
    // 11 -> 1011
    // 2  -> 0010
    public static void main(String[] args) {
        System.out.println(divide(11, 2));
    }

    public static long divide(long x, long y) {
        System.out.println("x: " + Long.toBinaryString(x));
        System.out.println("y: " + Long.toBinaryString(y));
        long result = 0;
        int power = 32;
        long yPower = y <<power;  // move y bits all the way to the left
        System.out.println("yPower: " + Long.toBinaryString(yPower));

        // take bits of y to 32 power
        // decrease power and yPower until yPower < x
        // then result += yPower; and x-=yPower;
        while (x >= y) {
            while (yPower > x) {  // decrease yPower and power
                yPower >>>= 1;
                --power;
                System.out.println("decrease powers (yPower/power): " + Long.toBinaryString(yPower) + "/" + power);
            }
            result += 1L << power;
            x -= yPower;
            System.out.println("result: " + result + ":" + Long.toBinaryString(result));
            System.out.println("x: " + Long.toBinaryString(x));
        }
        return result;
    }
}
