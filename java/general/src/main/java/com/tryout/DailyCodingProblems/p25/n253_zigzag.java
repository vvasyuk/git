package com.tryout.DailyCodingProblems.p25;

// Given a string and a number of lines k, print the string in zigzag form.
// In zigzag, characters are printed out diagonally from top left to bottom right until reaching the kth line,
// then back up to top right, and so on.
//
//For example, given the sentence "thisisazigzag" and k = 4, you should print:
//
//t     a     g
// h   s z   a
//  i i   i z
//   s     g
public class n253_zigzag {
    public static void main(String[] args) {
        String s = "thisisazigzag";
        int k=4;
        int x=0;
        int y=0;
        boolean down = true;

        boolean[][] matrix = new boolean[k][s.length()];

        while (y<s.length()){
            if (down) {
                matrix[x][y] = true;
                x+=1;y+=1;
            }else{
                matrix[x][y] = true;
                x-=1;y+=1;
            }
            if (x==k-1) down=false;
            if (x==0) down=true;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //System.out.print(matrix[i][j]);
                if (matrix[i][j]){
                    System.out.print(s.charAt(j));
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
