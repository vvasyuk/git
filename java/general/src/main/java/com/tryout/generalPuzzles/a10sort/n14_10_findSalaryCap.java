package com.tryout.generalPuzzles.a10sort;

// Every employee who earned more than the cap last year
//will be paid the cap this year; employees who earned no more than the cap will see
//no change in their salary

import java.util.Collections;
import java.util.List;

//For example, if there were five employees with salaries last year were
//$90,$30,$100,$40, and $20, and the target payroll this year is $210, then 60 is a
//suitable salary cap, since 60 + 30 + 60 + 40 + 20 = 210.
//Design an algorithm for computing the salary cap, given existing salaries and the
//target payroll.
public class n14_10_findSalaryCap {

    // For the given example, A = (20,30,40,90,100), and T = 210. The payrolls for
    //caps equal to the salaries in A are (100,140,170, 270, 280). Since T = 210 lies between
    //170 and 270, the cap lies between the 40 and 90. For any cap c between 40 and
    //90, the implied payroll is 20 + 30 + 40 + 2c. We want this to be 210, so we solve
    //20 + 30 + 40 + 2c = 210 for c, yielding c = 60.
    public static double findSalaryCap(double targetPayroll, List<Double> currentSalaries) {
        Collections.sort(currentSalaries);
        double unadjustedSalarySum = 0;
        for (int i = 0; i < currentSalaries.size(); ++i) {
            final double adjustedSalarySum = currentSalaries.get(i) * (currentSalaries.size() - i);
            if (unadjustedSalarySum + adjustedSalarySum >= targetPayroll) {
                return (targetPayroll - unadjustedSalarySum) / (currentSalaries.size() - i);
            }
            unadjustedSalarySum += currentSalaries.get(i);
        }
        // No solution, since targetPayroll > existing payroll.
        return -1.0;
    }
}
