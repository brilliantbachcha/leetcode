package com.sagnik.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem20 {

    /*    2081. Sum of k-Mirror Numbers (Done)
    A k-mirror number is a positive integer without leading zeros that reads the same both forward and backward in base-10 as well as in base-k.
For example, 9 is a 2-mirror number. The representation of 9 in base-10 and base-2 are 9 and 1001 respectively, which read the same both forward and backward.
On the contrary, 4 is not a 2-mirror number. The representation of 4 in base-2 is 100, which does not read the same both forward and backward.
Given the base k and the number n, return the sum of the n smallest k-mirror numbers.

Example 1:
Input: k = 2, n = 5
Output: 25
Explanation:
The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
  base-10    base-2
    1          1
    3          11
    5          101
    7          111
    9          1001
Their sum = 1 + 3 + 5 + 7 + 9 = 25.

Example 2:
Input: k = 3, n = 7
Output: 499
Explanation:
The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
  base-10    base-3
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.

Example 3:
Input: k = 7, n = 17
Output: 20379000
Explanation: The 17 smallest 7-mirror numbers are:
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596

Constraints:
2 <= k <= 9
1 <= n <= 30
     */
    public long kMirror(int k, int n) {
        long retVal = 0;
        int count = 0;
        int j = 0;
        while(count < n){
            for (long i = (long)Math.pow(10,j); i < (long)Math.pow(10,j+1) && (count < n); i++) {
                long pnum = generatePalindrom(i, true,10);
                if(isBaseNPalindrome(pnum,k)){
                    //System.out.println(pnum + " is  matched");
                    retVal += pnum;
                    count++;
                }
            }
            for (long i = (long)Math.pow(10,j); i < (long)Math.pow(10,j+1) && (count < n); i++) {
                long pnum = generatePalindrom(i, false,10);
                if(isBaseNPalindrome(pnum,k)){
                    //System.out.println(pnum + " is  matched");
                    retVal += pnum;
                    count++;
                }
            }
            //System.out.println("Count: "+ count + " RetVal : "+ retVal);
            j++;
        }


        return retVal;
    }

    private long generatePalindrom(long x, boolean isOdd, int base){
        long retVal = x;
        if(isOdd){
            x /= base;
        }
        while(x > 0){
            retVal = retVal * base + x % base;
            x /= base;
        }
        return retVal;

    }

    boolean isBaseNPalindrome(long num, int base){
        StringBuilder nBaseNum = new StringBuilder();
        while(num > 0){
            String digitStr = String.valueOf(num % base);
            nBaseNum.append(digitStr);
            num /= base;
        }
        String numStr = nBaseNum.toString();
        String revNumStr = nBaseNum.reverse().toString();
        return (numStr.equals(revNumStr));
    }


    public void test_kMirror_2081(){
        System.out.println("Expected: 25, Actual: "+ kMirror(2,5));
        System.out.println("Expected: 499, Actual: "+ kMirror(3,7));
        System.out.println("Expected: 6849225412, Actual: "+ kMirror(5,25));
    }




    /* not done
    2099. Find Subsequence of Length K With the Largest Sum
    You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.Return any such subsequence as an integer array of length k.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: nums = [2,1,3,3], k = 2
Output: [3,3]
Explanation:
The subsequence has the largest sum of 3 + 3 = 6.

Example 2:
Input: nums = [-1,-2,3,4], k = 3
Output: [-1,3,4]
Explanation:
The subsequence has the largest sum of -1 + 3 + 4 = 6.

Example 3:
Input: nums = [3,4,3,3], k = 2
Output: [3,4]
Explanation:
The subsequence has the largest sum of 3 + 4 = 7.
Another possible subsequence is [4, 3].

 Constraints:
1 <= nums.length <= 1000
-105 <= nums[i] <= 105
1 <= k <= nums.length
     */
    public int[] maxSubsequence(int[] nums, int k) {
        List<Integer> arrList = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).limit(k).collect(Collectors.toList());
        int[] retIntArr = arrList.stream().mapToInt(Integer::intValue).toArray();
        for(int i=0;i< retIntArr.length -1; i++){
            for(int j= i+1; j<retIntArr.length;j++){
                if(getnumImdex(nums, retIntArr[i]) > getnumImdex(nums, retIntArr[j])){
                    int t = retIntArr[i];
                    retIntArr[i] = retIntArr[j];
                    retIntArr[j] = t;
                }
            }
        }
        return retIntArr;
    }
    private  int getnumImdex(int[] nums, int n){
        return IntStream.range(0,nums.length).filter(i -> n == nums[i]).findFirst().orElse(-1);
    }

    public void test_maxSubsequence_2099(){
        int[] n = new int[] {18,3,19,-8,30,22,-35,11,16,18,-21,32,-7,-6,38,25,-21,-1,26,-8,-37,-39,-34,6,-36,-3,26,-32,22,-20,35,-35,-30,-8,11,7,-23,-9,-22,1,33,-6,12,2,27,-27,28,-12,21,12,16,21,33};
        int[] r = maxSubsequence(n,50);
        System.out.println("18,3,19,-8,30,22,-35,11,16,18,-21,32,-7,-6,38,25,-21,-1,26,-8,-34,6,-3,26,-32,22,-20,35,-35,-30,-8,11,7,-23,-9,-22,1,33,-6,12,2,27,-27,28,-12,21,12,16,21,33");
        Utility.printIntegerArray(r);
    }
}
