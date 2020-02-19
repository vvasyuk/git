package com.tryout.DailyCodingProblems.p26;

// given a string representing the initial conditions of some dominoes, each element can take one of 3 values:
// L - just been pushed to the left
// R - just been pushed to the right
// . - standing still

// determine orientation of each tile when dominoes stop falling. Node that if a domino receives a force from the left and right - will remain upright
// example, given
// .L.R....L - you should return
// LL.RRRLLL

// ..R...L.L
// ..RR.LLLL
public class n269_domino {
    public static void main(String[] args) {
        String in = ".L.R....L"; // LL.RRRLLL

        convert(in);
    }

    private static void convert(String in) {
        int curr = 0;
        int leftFalling = -100;
        int rightFalling = 100;
        StringBuilder res = new StringBuilder();

        while(curr<in.length()){
            if (in.charAt(curr)=='.'){
                for (int i = curr; i >-1 ; i--) {
                    if(in.charAt(i)!='.'){
                        if (in.charAt(i)=='R'){
                            leftFalling=i;
                        }else{
                            leftFalling=-100;
                        }
                        break;
                    }
                }
                for (int i = curr; i < in.length() ; i++) {
                    if(in.charAt(i)!='.'){
                        if (in.charAt(i)=='L') {
                            rightFalling=i;
                        }else{
                            rightFalling=100;
                        }
                        break;
                    }
                }
                System.out.println("leftFalling: " + leftFalling + " char/curr: " + curr + ":" + in.charAt(curr) + " rightFalling: " + rightFalling);
            }
            if (in.charAt(curr)=='.'){
                if (Math.abs(leftFalling)==100 && Math.abs(rightFalling)==100){
                    res.append(".");
                }else if (rightFalling-curr < Math.abs(leftFalling-curr)){
                    res.append("L");
                }else if (rightFalling-curr > Math.abs(leftFalling-curr)){
                    res.append("R");
                }else if (rightFalling-curr == Math.abs(leftFalling-curr)){
                    res.append(".");
                }
            }else{
                res.append(in.charAt(curr));
            }

            curr++;
        }
        System.out.println(res.toString());
    }

}

