package com.tryout.generalPuzzles.primitiveTypes;

// Write a program that multiplies two nonnegative integers. The only operators you
// are allowed to use are
// • assignment,
// • the bitwise operators », «, |, &, “
// and
// • equality checks and Boolean combinations thereof.
public class n5_5_x_multiply_y {
    //solution The algorithm taught in grade-school for decimal multiplication
    //to multiply x and y we initialize the result
    //to 0 and iterate through the bits of x,adding 2^ky to the result if the kth bit of x is 1.

    public static void main(String[] args) {
        int a = 13; // 1011
        int b = 9; // 1001

        //In the first iteration, since the LSB of 13 is 1, we set the
        //result to (1001). The second bit of (1101) is 0, so we move on to the third bit

        //This bit is 1,so we shift (1001)2 to the left by 2 to obtain (100100)2 which we add to (1001)2
        //to get (101101)2. The fourth and final bit of (1101)2 is 1, so we shift (1001)2 to the left
        //by 3 to obtain (1001000)2, which we add to (101101)2 to get (1110101)2 = 117.

        //addition: when bits are 0 and 1 - result is 1
        //when 0 and 0 -result is 0
        //when 1 and 1 - result is 0 and 1 is carried to next bit

        System.out.println(multiply(a, b));

    }

    public static long multiply(long x, long y) {
        long sum = 0;
        while (x != 0){
            // Examines each bit of x.
            if ((x & 1) != 0){
                sum = add(sum, y);
            }
            x >>=1;
            y <<= 1;
        }
        return sum;
    }

    private static long add(long a, long b) {
        long sum = 0,carryin = 0,k = 1, tempA = a, tempB = b;
        while (tempA != 0 ||tempB != 0){
            long ak = a & k, bk = b & k;
            long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
            sum |= (ak ^ bk ^ carryin);
            carryin = carryout << 1;
            k <<=1;
            tempA >>=1;
            tempB >>=1;
        }
        return sum | carryin;
    }
}
