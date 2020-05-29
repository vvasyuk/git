package com.tryout.generalPuzzles.a5stacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

// A string is said to be an arithmetical expression in Reverse Polish notation (RPN) if:
// 1) It is a single digit or a sequence of digits, prefixed with an option -, e.g., "6", "123", "-42".
// 2) It is of the form“A,B,o" where A and B are RPN expressions and o is one of +,-,x,/
//For example, the following strings satisfy these rules: "1729", "3,4, +,2,x,1, +", "1,1, +,-2, x", "-641,6, /, 28, /".
//An RPN expression can be evaluated uniquely to an integer, which is determined
//recursively. The base case corresponds to Rule (1.), which is an integer expressed in
//base-10 positional system. Rule (2.)corresponds to the recursive case, and the RPNs
//are evaluated in the natural way, e.g., if A evaluates to 2 and B evaluates to 3, then
//"A,B,x" evaluates to 6.
public class n9_2_evaluate_rpn_expression {
    // Let's begin with the RPN example "3,4, +, 2, X,1, + The ordinary form for
    //this is (3 + 4) X 2 + 1. To evaluate this by hand, we would scan from left to right. We
    //record 3, then 4, then applying the + to 3 and 4, and record the result, 7. Note that
    //we never need to examine the 3 and 4 again. Next we multiply by 2, and record the
    //result, 14. Finally, we add 1 to obtain the final result, 15.
    //Observe that we need to record partial results, and as we encounter operators,
    //we apply them to the partial results. The partial results are added and removed in
    //last-in, first-out order, which makes a stack the natural data structure for evaluating
    //RPN expressions

    public static int eval(String RPNExpression) {
        Deque<Integer> intermediateResults = new LinkedList<>();
        String delimiter = ",";
        String[] symbols = RPNExpression.split(delimiter);
        for (String token : symbols) {
            if (token.length() == 1 && "+-*/".contains(token)){
            final int y = intermediateResults.removeFirst();
            final int x = intermediateResults.removeFirst();
            switch (token.charAt(0)){
                case '+':
                    intermediateResults.addFirst(x + y);
                    break ;
                case '-':
                    intermediateResults.addFirst(x - y);
                    break ;
                case '*':
                    intermediateResults.addFirst(x * y);
                    break ;
                case '/':
                    intermediateResults.addFirst(x / y);
                    break ;
                default:
                    throw new IllegalArgumentException("Malformed RPN at :" + token);
            }
        } else { // token is a number.
            intermediateResults.addFirst(Integer.parseInt(token));
        }
    }
    return intermediateResults.removeFirst();
    }
}
