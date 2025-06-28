package com.sagnik.leetcode;

import java.util.Arrays;

public class Problem50 {
    /*#12
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.
 */
    public String intToRoman(int num) {
        String romanNumberStr = "";
        int  tempNum = num;
        int power = 0;
        while(tempNum > 0){
            if(tempNum >= 1000){
                romanNumberStr += "M";
                tempNum = tempNum-1000;
            }
            else if(tempNum >=900){
                romanNumberStr += "CM";
                tempNum = tempNum-900;
            }
            else if (tempNum >= 500) {
                romanNumberStr += "D";
                tempNum = tempNum-500;
            }
            else if (tempNum >= 400) {
                romanNumberStr += "CD";
                tempNum = tempNum-400;
            }
            else if (tempNum >= 100) {
                romanNumberStr += "C";
                tempNum = tempNum-100;
            }
            else if (tempNum >= 90) {
                romanNumberStr += "XC";
                tempNum = tempNum-90;
            }
            else if (tempNum >= 50) {
                romanNumberStr += "L";
                tempNum = tempNum-50;
            }
            else if (tempNum >= 40) {
                romanNumberStr += "XL";
                tempNum = tempNum-40;
            }
            else if (tempNum >= 10) {
                romanNumberStr += "X";
                tempNum = tempNum-10;
            }
            else if (tempNum == 9) {
                romanNumberStr += "IX";
                tempNum = tempNum-9;
            }
            else if (tempNum >= 5) {
                romanNumberStr += "V";
                tempNum = tempNum-5;
            }
            else if (tempNum == 4) {
                romanNumberStr += "IV";
                tempNum = 0;
            }
            else if (tempNum >= 1) {
                romanNumberStr += "I";
                tempNum = tempNum-1;
            }
        }
        return romanNumberStr;
    }

    /*#
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given an roman numeral., convert it to an integer
     */

    public int romanToNumber(String str){
        int number = 0;
        String romanStr = new String(str);
        char[] strArr = romanStr.toCharArray();
        for(int i=0;i < strArr.length; i++){
            if(strArr[i] == 'M'){
                number += 1000;
            }
            else if(strArr[i] == 'D'){
                number += 500;
            }
            else if(strArr[i] == 'C'){
                if(i+ 1 < strArr.length){
                    if(strArr[i+1] == 'M'){
                        number += 900;
                        i++;
                    }
                    else if(strArr[i+1] == 'D'){
                        number += 400;
                        i++;
                    }
                    else{
                        number += 100;
                    }
                }
                else {
                    number += 100;
                }
            }
            else if(strArr[i] == 'L'){
                number += 50;
            }
            else if(strArr[i] == 'X'){
                if(i+ 1 < strArr.length){
                    if(strArr[i+1] == 'C'){
                        number += 90;
                        i++;
                    }
                    else if(strArr[i+1] == 'L'){
                        number += 40;
                        i++;
                    }
                    else{
                        number += 10;
                    }
                }
                else {
                    number += 10;
                }
            }
            else if(strArr[i] == 'V'){
                number += 5;
            }
            else if(strArr[i] == 'I'){
                if(i+ 1 < strArr.length){
                    if(strArr[i+1] == 'X'){
                        number += 9;
                        i++;
                    }
                    else if(strArr[i+1] == 'V'){
                        number += 4;
                        i++;
                    }
                    else{
                        number += 1;
                    }
                }
                else {
                    number += 1;
                }
            }
        }
        return number;
    }
    public void testIntToRoman() {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
        System.out.println(intToRoman(3994));

        System.out.println(romanToNumber("III"));
        System.out.println(romanToNumber("LXXIX"));
        System.out.println(romanToNumber("MCMXCIV"));
        System.out.println(romanToNumber("DCCCXLV"));
    }

    /*
#11:
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.
Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length -1;
        int storedWater = 0;
        boolean isIncreased = false;
        if(height.length > 1){
            while(i < j){
                int width = j -i;
                int h = (height[i] > height[j]) ? height[j] : height[i];
                isIncreased = (height[i] < height[j]);
                if((width * h) > storedWater){
                    storedWater = width * h;
                }
                if(isIncreased) i++;
                else j--;
            }
        }
        return storedWater;
    }
    public void test_maxArea(){
        int[] heights = null;
        heights = new int[] {1,8,6,2,5,4,8,3,7};
        System.out.println("Expected: 49 Actual: "+ maxArea(heights));

        heights = new int[] {1,1};
        System.out.println("Expected: 1 Actual: "+ maxArea(heights));

        heights = new int[] {1};
        System.out.println("Expected: 0 Actual: "+ maxArea(heights));

        heights = new int[] {};
        System.out.println("Expected: 0 Actual: "+ maxArea(heights));
    }

    /* #16. 3Sum Closest
    Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.
Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Example 2:
Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

Constraints:
3 <= nums.length <= 500
-1000 <= nums[i] <= 1000
-104 <= target <= 104
     */
    public int threeSumClosest(int[] nums, int target) {
        int targetSum = 0;
        int closeSum = Integer.MAX_VALUE;
        Arrays.sort(nums);
        boolean isTargetSumFound = false;
        for(int i = 0; (i< nums.length -2) && !isTargetSumFound; i++){
            int left = i + 1;
            int right = nums.length - 1;
            if (i == left) {
                continue;
            }
            while(left < right && !isTargetSumFound) {
                targetSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - targetSum) <= Math.abs(target - closeSum)) {
                    closeSum = targetSum;
                }
                if (targetSum == target) {
                    isTargetSumFound = true;
                }
                else if (targetSum < target) {
                        left++;
                }
                else{
                    right--;
                }
            }
        }

        return closeSum;
    }

    public void test_threeSumClosest_16(){
        int nums1[] = {10,20,30,40,50,60,70,80,90};
        System.out.println("Expected: 60, Actual: "+ threeSumClosest(nums1, 2));

        int nums2[] = {-1,2,1,-4};
        System.out.println("Expected: 2, Actual: "+ threeSumClosest(nums2, 1));

    }
}
