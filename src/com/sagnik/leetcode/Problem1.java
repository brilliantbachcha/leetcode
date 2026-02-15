package com.sagnik.leetcode;

public class Problem1 {

    /*
    67. Add Binary
    Given two binary strings a and b, return their sum as a binary string.
Example 1:
Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

Constraints:
1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
     */
    public String addBinary(String a, String b) {
        long longA = Long.parseLong(a,2);
        long longB = Long.parseLong(b,2);

        Long sum = longA + longB;

        return Long.toBinaryString(sum);
    }

    public void test_67_addBinary(){
        String a = "11";
        String b = "1";

        System.out.println("Expected : 100,  Actual: "+ addBinary(a,b));

        a = "1010";
        b = "1011";
        System.out.println("Expected : 10101,  Actual: "+ addBinary(a,b));

    }

}
