package com.sagnik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem25 {

    /*
    2566. Maximum Difference by Remapping a Digit
You are given an integer num. You know that Bob will sneakily remap one of the 10 possible digits (0 to 9) to another digit.
Return the difference between the maximum and minimum values Bob can make by remapping exactly one digit in num.
Notes:
When Bob remaps a digit d1 to another digit d2, Bob replaces all occurrences of d1 in num with d2.
Bob can remap a digit to itself, in which case num does not change.
Bob can remap different digits for obtaining minimum and maximum values respectively.
The resulting number after remapping can contain leading zeroes.

Example 1:
Input: num = 11891
Output: 99009
Explanation:
To achieve the maximum value, Bob can remap the digit 1 to the digit 9 to yield 99899.
To achieve the minimum value, Bob can remap the digit 1 to the digit 0, yielding 890.
The difference between these two numbers is 99009.

Example 2:
Input: num = 90
Output: 99
Explanation:
The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
Thus, we return 99.

Constraints:
1 <= num <= 108
     */
    public int minMaxDifference(int num) {
        final int MAX_DIGIT = 9;
        final int MIN_DIGIT = 0;
        int retVal = -1;
        int changeVal = -1;
        if(num < 10)
            retVal =  MAX_DIGIT;
        else{
           int max_num = MAX_DIGIT;
           int min_num = MIN_DIGIT;
           int index = 0;
           List<Integer> numArr = new ArrayList<Integer>();
           while(num > 0){
               numArr.add(num %10);
               num = num /10;
           }
           int j = numArr.size() -1;
           while( j> -1){
                if(numArr.get(j) != MAX_DIGIT){
                    changeVal = numArr.get(j);
                    break;
                }
                j--;
            }
           index = numArr.size() -2;
           while(index > -1){
               int curVal = numArr.get(index);
               if(curVal == changeVal){
                   max_num = (max_num * 10) + MAX_DIGIT;
                   min_num = (min_num * 10) + MIN_DIGIT;
               }
               else {
                   max_num = (max_num * 10) + curVal;
                   min_num = (min_num * 10) + curVal;
               }
               index--;
           }
           retVal = max_num - min_num;
        }
        return retVal;
    }

    public void test_minMaxDifference_2566(){
        //System.out.println("Enter the Rows");
        //Scanner sc = new Scanner(System.in);
        //int x = sc.nextInt();
        System.out.println("Provided input: "+ 11891 + " Response: "+ minMaxDifference(11891));
        System.out.println("Provided input: "+ 90 + " Response: "+ minMaxDifference(90));
    }
}
