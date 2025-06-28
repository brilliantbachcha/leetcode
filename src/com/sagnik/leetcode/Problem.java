package com.sagnik.leetcode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Problem {
    private String retStr = "";




    /*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.*/
    public boolean braceOrder(String s)
    {
        boolean retVal = true;
        char[] chars = s.toCharArray();
        Stack<Character> st = new Stack<Character>();
        for(int i= 0; (i< chars.length) && retVal; i++) {
            if(chars[i] == '(' || chars[i] == '{' || chars[i] == '[') {
                st.push(chars[i]);
            }
            if(chars[i] == ')' || chars[i] == '}' || chars[i] == ']') {
                if(st.size() == 0) {
                    retVal = false;
                    break;
                }
                else {
                    char c = st.pop();
                    if (!((c + 1) == chars[i] || (c + 2) == chars[i])) {
                        retVal = false;
                        break;
                    }
                }
            }
        }
        if(st.size() > 0) retVal = false;

        return retVal;
    }

    /*
    Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
Return k after placing the final result in the first k slots of nums.
Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
Custom Judge:
The judge will test your solution with the following code:
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length
int k = removeDuplicates(nums); // Calls your implementation
assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     */
    public int removeDuplicates(int[] nums) {
        int count = 0;
        if(nums.length > 1){
            for(int i =0; i< nums.length-1;){
                if(nums[i] == nums[i+1]){
                    int j = i+1;
                    for(; (j < nums.length)&& (nums[i] ==nums[j]); j++){
                        nums[j] = Integer.MAX_VALUE;
                        count++;
                    }
                    i = j;
                }
                else{
                    i++;
                }
            }
            Arrays.sort(nums);
        }
        return (nums.length- count);
    }

    public int removeTwiceDuplicates(int[] nums) {
        int count = 0;
        int dup_count = 0;
        if(nums.length > 1){
            for(int i =0; i< nums.length-1;){
                if(nums[i] == nums[i+1]){
                    dup_count++;
                    if(dup_count > 1) {
                        int j = i + 1;
                        for (; (j < nums.length) && (nums[i] == nums[j]); j++) {
                            nums[j] = Integer.MAX_VALUE;
                            count++;
                        }
                        i = j;
                        dup_count =0;
                    }
                    else{
                        i++;
                    }
                }
                else{
                    i++;
                    dup_count =0;
                }
            }
            Arrays.sort(nums);

        }
        return (nums.length- count);
    }

    public void test_removeDuplicates(){
        int[] nums = new int[]{1,1,2};
        System.out.println("Result: " + removeTwiceDuplicates(nums));
        Arrays.stream(nums).boxed().filter(x-> x < Integer.MAX_VALUE).forEach(x-> System.out.print(x+" "));
        System.out.println();

        nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println("Result: " + removeTwiceDuplicates(nums));
        Arrays.stream(nums).boxed().filter(x-> x < Integer.MAX_VALUE).forEach(x-> System.out.print(x+" "));
        System.out.println();
        nums = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,3};
        System.out.println("Result: " + removeTwiceDuplicates(nums));
        Arrays.stream(nums).boxed().filter(x-> x < Integer.MAX_VALUE).forEach(x-> System.out.print(x+" "));
        System.out.println();
    }

    /*
    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
Return k after placing the final result in the first k slots of nums.
Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
Custom Judge:
The judge will test your solution with the following code:
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.
int k = removeElement(nums, val); // Calls your implementation
assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
     */
    public int removeElement(int[] nums, int val) {
        int count = 0;
        if(nums.length > 1){
            for(int i =0; i< nums.length; i++){
                if(nums[i] == val){
                    nums[i] =  Integer.MAX_VALUE;
                    count++;
                }
            }
            Arrays.sort(nums);
        }
        return (nums.length- count);
    }
    /*
    Given a string s, return the longest palindromic substring in s.
Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:
Input: s = "cbbd"
Output: "bb"
input:
dsqspnkrvrhqzqvovbofdzqishgtcrvckluzpwesvartjhljqdphdupktoxdffvoqupuxmehikegjnwuheoafgqrtvuzphkikaixnjmhepeqorzjzgnrxxxirhjvboijbzftxhvtrdmbcvysxscvqmgifipwujvvktithqthujpxwwgamwqkxnnxiymtuvtyzafbxybalnjboaiyrxedviesmzzwgagilndguylskdikiocvqmjmfykakuiihuqurgqqirjoccqoixegyspftktguitqtixcsywycutcaedusndombnfzpgoklqzzqlkogpzfnbmodnsudeactucywyscxitqtiugtktfpsygexioqccojriqqgruquhiiukakyfmjmqvcoikidkslyugdnligagwzzmseivdexryiaobjnlabyxbfazytvutmyixnnxkqwmagwwxpjuhtqhtitkvvjuwpifigmqvcsxsyvcbmdrtvhxtfzbjiobvjhrixxxrngzjzroqepehmjnxiakikhpzuvtrqgfaoehuwnjgekihemxupuqovffdxotkpudhpdqjlhjtravsewpzulkcvrctghsiqzdfobvovqzqhrvrknpsqsd

     */
    public String longestPalindrome(String s) {
        retStr = "";
        if(s.length() > 1) {
            for(int i =0; i< s.length(); i++){
                calculatePalindrome(s, i,i);
                calculatePalindrome(s, i,i+1);
                //System.out.println(i+ " ->" + retStr);
            }
        }
        else
            retStr = s;
        return retStr;
    }

    private void calculatePalindrome(String s, int start, int end){
        while(start >= 0 && end < s.length() && (s.charAt(start) == s.charAt(end))){
            start--;
            end++;
        }
        retStr =  (retStr.length() < (end -start )) ? s.substring(start+1, end): retStr;
    }

    /*
    Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
Example 1:
Input: x = 123
Output: 321
Example 2:
Input: x = -123
Output: -321
Example 3:
Input: x = 120
Output: 21
     */
    public int reverse(int x) {
        int revnum = 0;
        int multiplier = (x< 0) ? -1 : 1;
        int MAX_NUM = (int) Math.pow(2,31);
        int num = Math.abs(x);
        while(num > 0) {
            if(revnum <= (Integer.MAX_VALUE/10)) { //Checking the overflow
                revnum = (revnum * 10) + (num % 10);
                num = num / 10;
                System.out.println(revnum + " " + num);
            }
            else {
                num = 0;
                multiplier = 0;
            }
        }
        System.out.println(Integer.MAX_VALUE);
        revnum = (revnum > MAX_NUM) ? 0 : revnum;
        return (revnum * multiplier);
    }

    /*
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
    P        A       H       N
    A   P   L   S   I   I   G
    Y       I       R
    And then read line by line: "PAHNAPLSIIGYIR"
    Write the code that will take a string and make this conversion given a number of rows:
    string convert(string s, int numRows);
     */
    public String convert(String s, int numRows) {
        String[] strArry = new String[numRows];
        String retVal = "";
        boolean dir = true;
        int i=0;
        for(int x= 0; x< strArry.length; x++){
            strArry[x] = "";
        }

        char[] chArray = s.toCharArray();
        for(char ch : chArray){
            strArry[i] += ch+"";
            if(i == 0){
                dir = true;
            }
            if(i == numRows -1){
                dir = false;
            }
            i = dir? (i+1) : (i-1);
        }

        for(String str : strArry){
            retVal += str;
        }
        return retVal;
    }
    /*
    You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
You can return the answer in any order.
Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer>indexList = new ArrayList<Integer>();
        //int startIndex = 0;
        int subStringLength = words[0].length() * words.length;
        if(s.length() < subStringLength){
            return indexList;
        }
        HashMap<String, Integer>wordMap = new HashMap<String, Integer>();
        for(String word : words){
            int n = 0;
            if(wordMap.get(word) != null){
                n = wordMap.get(word);
            }
            wordMap.put(word, n+1);
        }

        for(int i =0; i<= s.length() - subStringLength; i++){
            String subStr = s.substring(i, i+ subStringLength);
            boolean isMatch = isAllWordMatch(subStr,wordMap);
            if(isMatch){
                indexList.add(i);
            }
        }

        return indexList;
    }

    private boolean isAllWordMatch(String str, HashMap<String, Integer>wordMap)
    {
        boolean isMatch = false;
        int count = -1;
        int wordLength = wordMap.keySet().iterator().next().length();
        List<String> allowedValues = new ArrayList<String>(wordMap.keySet());
        HashMap<String, Integer>tempWordMap = new HashMap<>(wordMap);
        for(int i=0;i <= str.length() - wordLength; ){
            String subStr = str.substring(i, i+ wordLength);
            for(String value : allowedValues){
                if(value.equals(subStr)){
                    int c = tempWordMap.get(value);
                    tempWordMap.put(value, c-1);
                    break;
                }
            }
            i = i+ wordLength;
        }
        count = tempWordMap.values().stream().filter(x-> x!=0).collect(Collectors.toList()).size();
       // System.out.println(str + ":  "+ sum);
        return (count == 0)? true: false;
    }

    public void testIsAllWordMatch(){
        List<Integer> indexList;
        String s;
        String[] arr;
         s = "barfoothefoobarman";
         arr = new String[] {"bar","foo"};
        indexList = findSubstring(s,arr);
        System.out.println("Expected: [0,9]");
        indexList.stream().forEach(System.out::print);
        System.out.println();

        s = "ababaab";
        arr = new String[] {"ab","ba","ba"};
        indexList = findSubstring(s,arr);
        System.out.println("Expected: [1]");
        indexList.stream().forEach(System.out::print);
        System.out.println();

        s = "barfoofoobarthefoobarman";
        arr = new String[] {"bar","foo","the"};
        indexList = findSubstring(s,arr);
        System.out.println("Expected: [6,9,12]");
        indexList.stream().forEach(System.out::print);
        System.out.println();
    }


    public boolean checkSortRotateArray(int[] nums) {
        if(nums.length <2){
            return true;
        }
        int[] sortNums = nums.clone();
        boolean retVal = true;
        int rotation = -1;
        Arrays.sort(sortNums);
        int checkNum = sortNums[0];
        for(int i=0;i <nums.length;i++){
            if(nums[i] == checkNum){
                rotation = i;
                break;
            }
        }
        for(int i=0;i <nums.length;i++){
            if(sortNums[i] != nums[(i+rotation)%nums.length]){
                retVal = false;
                break;
            }
        }
        return retVal;
    }

/*#66
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
Increment the large integer by one and return the resulting array of digits.
Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
Example 2:
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Example 3:
Input: digits = [9]
Output: [1,0]
Constraints:
1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
 */
    public int[] plusOne(int[] digits) {
        int[] retArray = new int[digits.length +1];
        retArray[0] = 0;
        System.arraycopy(digits,0,retArray,1,digits.length);
        int index = retArray.length -1;
        int carryFwd = 1;
        while(carryFwd != 0){
            int num = retArray[index] + carryFwd;
            carryFwd = num /10;
            retArray[index] = num % 10;
            index--;
        }
        if(retArray[0] == 0) {
            return Arrays.copyOfRange(retArray, 1,retArray.length);
        }
        else {
            return retArray;
        }
    }

    public void testPlusOne()
    {
        int[] arr;
        int[] ret = null;
        arr = new int[] {4,3,2,1};
        ret = plusOne(arr);
        for(int a : ret)
            System.out.print(a + " ");
        System.out.println();

        arr = new int[] {9};
        ret = plusOne(arr);
        for(int a : ret)
            System.out.print(a + " ");
        System.out.println();

        arr = new int[] {9,9,9,9,9,9,9,9,9,9};
        ret = plusOne(arr);
        for(int a : ret)
            System.out.print(a + " ");
        System.out.println();

    }


/*
Convert a non-negative integer num to its English words representation.
Example 1:
Input: num = 123
Output: "One Hundred Twenty Three"

Example 2:
Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
Input: num = 1,2,34,5,67
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
private Map<Integer, String> digitToWordMap = new HashMap<Integer, String>(){{
    put(1, "One");
    put(2, "Two");
    put(3, "Three");
    put(4, "Four");
    put(5, "Five");
    put(6, "Six");
    put(7, "Seven");
    put(8, "Eight");
    put(9, "Nine");
    put(10, "Ten");
    put(11, "Eleven");
    put(12, "Twelve");
    put(13, "Thirteen");
    put(14, "Forteen");
    put(15, "Fifteen");
    put(16, "Sixteen");
    put(17, "Seventeen");
    put(18, "Eighteen");
    put(19, "Nineteen");
    put(20, "Twenty");
    put(30, "Thirty");
    put(40, "Forty");
    put(50, "Fifty");
    put(60, "Sixty");
    put(70, "Seventy");
    put(80, "Eighty");
    put(90, "Ninety");
}};

    public String numberToWords(int num) {
        if(num ==0)
            return "Zero";
        List<String>numWords = new ArrayList<String>();
        String numInWords = "";
        int reminder = 0;
        int loopCount = 0;
        while(num > 0){
            switch (loopCount){
                case 1:
                    numWords.add("Thousand ");
                    break;
                case 2:
                    numWords.add("Million ");
                    break;
                case 3:
                    numWords.add("Billion ");
                    break;
                default:
                    break;
            }
            reminder = num % 1000;
            num = num / 1000;
            if(reminder > 0)
                numWords.add(numToStr(reminder));
            else if(loopCount != 0){
                numWords.remove(numWords.size() -1);
            }

            loopCount++;
        }
        Collections.reverse(numWords);
        for(String str : numWords){
            numInWords += str;
        }
        return numInWords.trim();
    }

    private String numToStr(int num){
        String numStr = "";
        int hundred = num /100;
        if(digitToWordMap.get(hundred) != null){
            numStr += digitToWordMap.get(hundred) + " Hundred ";
        }
        int reminder = num %100;
        if(digitToWordMap.get(reminder) != null){
            numStr += digitToWordMap.get(reminder) + " ";
        }
        else {
            int tens = (int)(reminder /10) * 10;
            if(digitToWordMap.get(tens) != null) {
                numStr += digitToWordMap.get(tens) + " ";
            }
            int ones = reminder %10;
            if(digitToWordMap.get(ones) != null) {
                numStr += digitToWordMap.get(ones) + " ";
            }

        }
        return numStr;
    }

    public void test_numberToWords(){
        /*System.out.println(numToStr(365));
        System.out.println(numToStr(3));
        System.out.println(numToStr(85));*/
        System.out.println(numberToWords(1));
        System.out.println(numberToWords(10));
        System.out.println(numberToWords(100));
        System.out.println(numberToWords(1000));
        System.out.println(numberToWords(10000));
        System.out.println(numberToWords(100000));
        System.out.println(numberToWords(1000000));
        System.out.println(numberToWords(10000000));
        System.out.println(numberToWords(100000000));
        System.out.println(numberToWords(1000000000));
   }



/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.
 */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        if(nums.length < 3){
            return returnList;
        }
        Integer lastListStartVal = null;
        int[]sortedNums = Arrays.stream(nums).sorted().toArray();
        for(int index=0; index < sortedNums.length-1; index++){
            int firstNum = sortedNums[index];
            //Managing testcase of 3k 0(zeros)
            if(!returnList.isEmpty() && returnList.size() > 0
                && !returnList.get(returnList.size()-1).isEmpty() && returnList.get(returnList.size()-1).get(0)!= null){
                lastListStartVal = returnList.get(returnList.size()-1).get(0);
            }
            if(lastListStartVal == null || firstNum != lastListStartVal){
                populateResultSet(returnList,sortedNums, index);
            }

        }
        returnList = returnList.stream().distinct().collect(Collectors.toList());
        return returnList;
    }

    private void populateResultSet(List<List<Integer>> returnList, int[] sortedNums, int index) {
        int firstNum = sortedNums[index];
        int startIndex = index + 1;
        int endIndex = sortedNums.length -1;
       int expectedSum = 0 - firstNum;
       boolean isfrontdirectional = true;
       while(startIndex < endIndex){
           int sum = sortedNums[startIndex] + sortedNums[endIndex];
           if(sum == expectedSum){
               //Add to ruturnlist
               List<Integer>dataList = new ArrayList<Integer>(3);
               dataList.add(firstNum);
               dataList.add(sortedNums[startIndex] );
               dataList.add(sortedNums[endIndex]);
               System.out.println("["+firstNum+ ","+ sortedNums[startIndex] + ","+sortedNums[endIndex]+ "]");
               returnList.add(dataList);
               if(isfrontdirectional){
                   startIndex++;
               }
               else{
                   endIndex--;
               }
           } else if (sum < expectedSum) {
               startIndex++;
               isfrontdirectional = true;
           }
           else{
               endIndex--;
               isfrontdirectional = false;
           }
       }
    }


    public void test_threeSum(){
        int[] nums = null;
        List<List<Integer>>resultList = null;

        /*nums = new int[] {-1,0,1,2,-1,-4};
        resultList = threeSum(nums);
        printResultList(resultList);

        nums = new int[] {0,1,1};
        resultList = threeSum(nums);
        printResultList(resultList);*/

        nums = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        resultList = threeSum(nums);
        printResultList(resultList);
    }

    private void printResultList(List<List<Integer>>resultList) {
        System.out.println("Result Count: " + resultList.size());
        /*Print Value*/
        for (List<Integer> intList : resultList) {
            Utility.printList(intList);
            System.out.print("   ");
        }
        System.out.println();
    }



    /* 1770. Maximum Score from Performing Multiplication Operations
        You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
        You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
        Choose one integer x from either the start or the end of the array nums.
        Add multipliers[i] * x to your score.
        Remove x from the array nums.
        Return the maximum score after performing m operations.
        Example 1:
        Input: nums = [1,2,3], multipliers = [3,2,1]
        Output: 14
        Explanation: An optimal solution is as follows:
        - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
        - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
        - Choose from the end, [1], adding 1 * 1 = 1 to the score.
        The total score is 9 + 4 + 1 = 14.

        Example 2:
        Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
        Output: 102
        Explanation: An optimal solution is as follows:
        - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
        - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
        - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
        - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
        - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
        The total score is 50 + 15 - 9 + 4 + 42 = 102.

        Constraints:
        n == nums.length
        m == multipliers.length
        1 <= m <= 103
        m <= n <= 105
        -1000 <= nums[i], multipliers[i] <= 1000
     */
    public int maximumScoreOLd(int[] nums, int[] multipliers) {
        int score1 = 0;
        int score2 = 0;

        List<Integer>numList1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer>numList2 = new ArrayList<Integer>();
        numList2.addAll(numList1);
        int start = 0;
        int last = 0;
        for(int index = 0; index <multipliers.length; index++){
            score1 += multipliers[index] *  numList1.get(start);
            last = numList2.size() -1;
            score2 += multipliers[index] *  numList2.get(last);
            System.out.println(multipliers[index] + " , " + numList1.get(start) + " , "+ numList2.get(last));
            System.out.println("Score: " + score1 + " , " + score2);
            numList1.remove(start);
            numList2.remove(last);
        }
        return (score1 > score2) ? score1 : score2;
    }

    public int maximumScore(int[] nums, int[] mul) {
        int m = mul.length;
        int[] dp = new int[m+1];
        System.out.println("Nums.length: "+ nums.length + " , mul.length: "+mul.length);
        for (int i = m-1; i >=0; i--){
            for (int left = 0; left <=i; left++){
                int right = nums.length-1 - (i-left);
                System.out.println("Index: " + i + " , "+ left+ " , "+ right);
                System.out.println("nums[left] : "+ nums[left]+ " , nums[right]: "+ nums[right]+ " , mul[i]: "+ mul[i]);
                int l = nums[left] * mul[i] + dp[left+1];
                int r = nums[right] * mul[i] + dp[left];

                System.out.println("l ="+ l +  " , r=" + r);

                dp[left] = Math.max(l,r);
                System.out.println(dp[left]);
            }
        }
        return dp[0];
    }


    public void test_maximumScore(){
/*        int[] nums1 = {1,2,3};
        int[] multiplier1 = {3,2,1};
        System.out.println("Score: "+ maximumScore(nums1,multiplier1));*/

        int[] nums2 = {-5,-3,-3,-2,7,1};
        int[] multiplier2 = {-10,-5,3,4,6};
        System.out.println("Score: "+ maximumScore(nums2,multiplier2));
    }

    /*
    You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
Return the maximum possible length of s.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.

Constraints:
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.
     */
    public int maxLength(List<String> arr) {
        int maxLength = arr.get(0).length();
        int arrLength = arr.size();
        int secondMaxLength = 0;
        boolean isAllDuplicate = true;
        if(arrLength > 1) {
            secondMaxLength = arr.get(1).length();
            for(String str : arr){
                if(containDuplicate(str)) {
                    if (str.length() > maxLength) {
                        secondMaxLength = maxLength;
                        maxLength = str.length();
                    }
                    if (str.length() > secondMaxLength) {
                        secondMaxLength = str.length();
                    }
                    System.out.println("maxLength: " + maxLength + "secondMaxLength: " + secondMaxLength);
                }
            }

            maxLength += secondMaxLength;
        }
        return maxLength;
    }

    private boolean containDuplicate(String str){
        HashSet<Character>strSet = new HashSet<Character>(str.length());
        for(char c : str.toCharArray()){
            strSet.add(c);
        }
        return (strSet.size() == str.length());
    }

    public void test_maxLength(){
        List<String> arr = new ArrayList<String>(Arrays.asList("cha","r","act", "aabcs","ers"));
        System.out.println("MaxLength: "+ maxLength(arr));
    }

    /*
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
     */
    public List<String> letterCombinations(String digits) {
        List<String>responseList = new ArrayList<String>();
        Map<Character, String>staticStringMap = Map.of('2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9',"wxyz");
        if(digits.length() > 1){
            List<String>combiList = new ArrayList<String>(digits.length());
            for(char c : digits.toCharArray()){
                combiList.add(staticStringMap.get(c));
            }

            Integer indexArr[] = new Integer[3];
            Arrays.fill(indexArr, 0);
            for(int i = 0; i < combiList.size(); i++){

                int changeIndex = i;
                for(int idx = 0; idx < 3; idx++ ) {
                    String str = "";
                    int j = 0;
                    while (j < combiList.size()) {
                        str= str + combiList.get(j++).charAt(indexArr[idx]);
                    }
                    responseList.add(str);
                    indexArr[changeIndex]++;
                }
            }

        }
        else {
            String digitChar = staticStringMap.get(digits.charAt(0));
            responseList.add(digitChar);
        }
        return responseList;
    }

    public void testLetterCombinations(){
        String digits = "23";
        List<String>ltrComboList = letterCombinations(digits);
        //ltrComboList.forEach(System.out::println);
        System.out.println(String.join(" , ", ltrComboList));
    }

    /*
    You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //Validations
        ArrayList<Integer>resultSet = new ArrayList<Integer>(m+n);
        if(m < 0 || n <0 || m > 200 || n > 200) {
            return ;
        }
        if(nums1.length != (m+n)){
            return ;
        }
        int num1Pos = m-1;
        int num2Pos = n - 1;
        int currPos = m+n -1;

        while(currPos >=0){
            if(num2Pos < 0 && num1Pos >= 0){
                //Data left in nums1
                currPos = 0;
            }
            else if(num1Pos < 0){
                nums1[currPos] = nums2[num2Pos];
                num2Pos--;
            }
            else if(nums1[num1Pos] < nums2[num2Pos]){
                nums1[currPos] = nums2[num2Pos];
                num2Pos--;
            }
            else{
                nums1[currPos] = nums1[num1Pos];
                num1Pos--;
            }
            currPos--;
        }

    }

    public void testArrayMerge(){
        int[] nums1 = {};
        int[] nums2 = {2,5,10};
        merge(nums1, 0, nums2, 3);

        Arrays.stream(nums1).forEach(System.out::println);
    }

    /*
    Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
     */
    public int majorityElement(int[] nums) {
        int majorityElement = nums[0];
        HashMap<Integer, Integer>dataCountMap = new HashMap<Integer, Integer>();
        int maxCount = 1;
        for(int i = 0;i < nums.length;i++){
            Integer number = nums[i];
            if(dataCountMap.get(number) != null){
                int count = dataCountMap.get(number);
                dataCountMap.put(number,++count);
                if(count > dataCountMap.get(majorityElement)){
                    majorityElement = number;
                    maxCount = count;
                }
            }
            else {
                dataCountMap.put(number, 1);
            }
        }
        return majorityElement;
    }

    //Alternate solution of space complexity O(1)
    public int majorityElement_alt(int[] nums) {
        //As it is expected that number will be always present & count is > length/2,
        // then element @length/2 is the majority element
        Arrays.sort(nums);
        return nums[nums.length / 2];

    }

    public void test_majorityElement(){
        int[] num = {6,5,5};
        int element = majorityElement(num);
        System.out.println("Expected 5. Received: "+ element);
        int[] num2 = {2,2,1,1,1,2,2};
        element = majorityElement_alt(num2);
        System.out.println("Expected 2. Received: "+ element);
    }

    /***22
     Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     Example 1:
     Input: n = 3
     Output: ["((()))","(()())","(())()","()(())","()()()"]

     Example 2:
     Input: n = 1
     Output: ["()"]

     Constraints:   1 <= n <= 8
     */
    public List<String> generateParenthesis(int n) {
        /* Initial Thought process ...
        List<String>retVal = new ArrayList<>();
        Set<String>genStrings = new HashSet<>();
        retVal.add("()");
        for(int i = 1; i<n; i++){
            for (String str: retVal) {
                String newStr;
                //Add begin
                newStr = "()"+str;
                genStrings.add(newStr);
                //Add End
                newStr = str + "()";
                genStrings.add(newStr);
                //Add over
                newStr = "("+str+")";
                genStrings.add(newStr);
            }
            retVal.clear();
            retVal.addAll(genStrings);
            genStrings.clear();
        }
        .....End of initial though process
         */
        List<String>retVal = new ArrayList<>();
        int openParenthesisCounter = 0;
        int closeParenthesisCounter = 0;
        String currentPopulatedString = "";

        generateValidParenthesis(retVal, openParenthesisCounter, closeParenthesisCounter, n, currentPopulatedString);

        return retVal;
    }

    private void generateValidParenthesis(List<String> retVal, int openParenthesisCounter, int closeParenthesisCounter, int n, String currentPopulatedString) {
        //Exit Condition
        if(currentPopulatedString.length() == n * 2){
            //Expected number of valid pairs are generated
            retVal.add(currentPopulatedString);
        }
        if(openParenthesisCounter < n) { //We still can add open parenthesis
            generateValidParenthesis(retVal, openParenthesisCounter + 1, closeParenthesisCounter, n, currentPopulatedString+ "(");
        }
        if(closeParenthesisCounter < openParenthesisCounter){
            generateValidParenthesis(retVal, openParenthesisCounter , closeParenthesisCounter + 1, n, currentPopulatedString+ ")");
        }
    }

    void test_generateParenthesis(int n){
        List<String> retVal = generateParenthesis(n);
        retVal.stream().forEach(x-> System.out.print(x + "  "));
        System.out.println();
    }

    /* #2038: Remove Colored Pieces if Both Neighbors are the Same Color
    There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'. You are given a string colors of length n where colors[i] is the color of the ith piece.

Alice and Bob are playing a game where they take alternating turns removing pieces from the line. In this game, Alice moves first.

Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'. She is not allowed to remove pieces that are colored 'B'.
Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'. He is not allowed to remove pieces that are colored 'A'.
Alice and Bob cannot remove pieces from the edge of the line.
If a player cannot make a move on their turn, that player loses and the other player wins.
Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.



Example 1:

Input: colors = "AAABABB"
Output: true
Explanation:
AAABABB -> AABABB
Alice moves first.
She removes the second 'A' from the left since that is the only 'A' whose neighbors are both 'A'.

Now it's Bob's turn.
Bob cannot make a move on his turn since there are no 'B's whose neighbors are both 'B'.
Thus, Alice wins, so return true.
Example 2:

Input: colors = "AA"
Output: false
Explanation:
Alice has her turn first.
There are only two 'A's and both are on the edge of the line, so she cannot move on her turn.
Thus, Bob wins, so return false.
Example 3:

Input: colors = "ABBBBBBBAAA"
Output: false
Explanation:
ABBBBBBBAAA -> ABBBBBBBAA
Alice moves first.
Her only option is to remove the second to last 'A' from the right.

ABBBBBBBAA -> ABBBBBBAA
Next is Bob's turn.
He has many options for which 'B' piece to remove. He can pick any.

On Alice's second turn, she has no more pieces that she can remove.
Thus, Bob wins, so return false.


Constraints:

1 <= colors.length <= 105
colors consists of only the letters 'A' and 'B'
     */
    public boolean winnerOfGame(String colors) {

        boolean retVal = false;
        boolean isWinnerDetermined = false;

        if(colors.length() < 3){
            //Alice can't make a move
            isWinnerDetermined = true;
        }

        StringBuilder inputString = new StringBuilder(colors);

        while(!isWinnerDetermined){
            if(inputString.indexOf("AAA") > -1){
                //Alice can make a move
                //Index of middle A
                int index = inputString.indexOf("AAA") + 1;
                inputString.deleteCharAt(index);
            }
            else{
                //Alice can't make a move , Bob is winner
                isWinnerDetermined = true;
                break;
            }

            if(inputString.indexOf("BBB") > -1){
                //Bob can make a move
                //Index of middle B
                int index = inputString.indexOf("BBB") + 1;
                inputString.deleteCharAt(index);
            }
            else{
                //Bob can't make a move , Alice is winner
                isWinnerDetermined = true;
                retVal = true;
            }
        }

        return retVal;
    }

    void test_winnerOfGame(){
        String ipStr = "AAAAABBB";

        String opStr = winnerOfGameV2(ipStr) ? "Alice is winner" : "Bob is winner";
        System.out.println(opStr);
    }

    public boolean winnerOfGameV2(String colors){
        if(colors.length() < 3){
            //Alice can't make a move
            return false;
        }

        long aliceMoveCount = countOfSubString(colors, "AAA");
        long bobMoveCount = countOfSubString(colors, "BBB");

        return (aliceMoveCount > bobMoveCount) ? true : false;

    }
    private long countOfSubString(String source, String pattern){
        long count  = 0;
        int index = -1;
        while(index != 0){
            index = source.indexOf(pattern, index) + 1;
            if(index != 0)
                count ++;
        }
        return count;
    }

    /* #1512
    Given an array of integers nums, return the number of good pairs.
A pair (i, j) is called good if nums[i] == nums[j] and i < j.
Example 1:
Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.

Example 2:
Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:
Input: nums = [1,2,3]
Output: 0

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
     */

    public int numIdenticalPairs(int[] nums) {
        int retVal = 0;
        Map<Integer, Integer>numMap = new HashMap<>();
        if(nums.length < 2) return retVal;
        boolean isPairPresent = false;
        for(int i : nums){
            Integer count = numMap.get(i) ;
            if(count== null){
                numMap.put(i, 0);
            }
            else{
                isPairPresent = true;
                numMap.put(i, ++count);
            }
        }
        if(isPairPresent){
           retVal = numMap.values().stream().filter(a -> a > 0).map(n-> (n * (n+1))/2).mapToInt(Integer::intValue).sum();
            //for(Integer x : numMap.values())
        }

        return retVal;

        /*Alternate Solution:
        int retVal = 0, cnt[] = new int[101];
        for (int a: nums) {
            retVal += cnt[a]++;
        }
        return retVal;
         */
    }

    void test_numIdenticalPairs(){
        int[] arr1 = new int[]{1,2,3,1,1,3};
        int[] arr2 = new int[]{1,2,3};
        int[] arr3 = new int[]{1,1,1,1};
        int[] arr4 = new int[]{1,1,1,1,1};
        int[] arr5 = new int[]{};

        System.out.println("Expected : 4, Actual: "+ numIdenticalPairs(arr1));
        System.out.println("Expected : 0, Actual: "+ numIdenticalPairs(arr2));
        System.out.println("Expected : 6, Actual: "+ numIdenticalPairs(arr3));
        System.out.println("Expected : 10, Actual: "+ numIdenticalPairs(arr4));
        System.out.println("Expected : 0, Actual: "+ numIdenticalPairs(arr5));

    }

    /* #29
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
Return the quotient after dividing dividend by divisor.
Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

Example 1:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.

Constraints:
-231 <= dividend, divisor <= 231 - 1
divisor != 0
     */
    public int divide(int dividend, int divisor) {

        int result = 0;
        //TODO: Complete this code by bitwise operator

        return result;
    }

    public void test_divide(){
        int dividend = 0;
        int divisor = 0;

        System.out.println("Value Expected: 16, Actual: " + divide(112,7));
        System.out.println("Value Expected: 2, Actual: " + divide(18,7));
        System.out.println("Value Expected: 12, Actual: " + divide(112,9));
        System.out.println("Value Expected: 10, Actual: " + divide(234,23));
    }

    /*#229
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:
Input: nums = [3,2,3]
Output: [3]

Example 2:
Input: nums = [1]
Output: [1]

Example 3:
Input: nums = [1,2]
Output: [1,2]

Constraints:
1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109

Follow up: Could you solve the problem in linear time and in O(1) space?
     */
    public List<Integer> majorityElement_229(int[] nums) {
        int minValue = 0;
        int oldElem = -110;
        List<Integer>majorityElementList = new ArrayList<>();
        int count = 0;
        minValue = (nums.length > 2) ? (nums.length/3) +1 : 1;

        Arrays.sort(nums);

        for(int x : nums){
            if(oldElem != x) count =0;
            if(majorityElementList.isEmpty() || (majorityElementList.size() > 0 && majorityElementList.get(majorityElementList.size()-1) != x)) {
                count++;
            }
            if(count == minValue){
                majorityElementList.add(x);
                count = 0;
            }
            oldElem = x;
        }
        return majorityElementList;
    }

    public void test_majorityElement_229()
    {
        List<Integer>elementList = null;
        int[]nums = null;

        nums = new int[]{1,3,2,3,1,1,3};
        elementList = majorityElement_229(nums);
        Utility.printIntegerList(elementList);

        nums = new int[]{3,2,3};
        elementList = majorityElement_229(nums);
        Utility.printIntegerList(elementList);

        nums = new int[]{1,2,3};
        elementList = majorityElement_229(nums);
        Utility.printIntegerList(elementList);
    }



    /**********#119
     * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     *
     * Example 1:
     * Input: rowIndex = 3
     * Output: [1,3,3,1]

     * Example 2:
     * Input: rowIndex = 0
     * Output: [1]

     * Example 3:
     * Input: rowIndex = 1
     * Output: [1,1]
     * Constraints: 0 <= rowIndex <= 33
     * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> retValList = new ArrayList<Integer>(rowIndex + 1);
        retValList.add(1);
        Integer lastValue = 1;
        for(int index = 1; index < (rowIndex+1); index++){
	            /*int data = lastValue * (rowIndex - index + 1) / index;
	            retValList.add(data);*/
            BigInteger value = BigInteger.valueOf(lastValue).multiply(BigInteger.valueOf(rowIndex - index + 1)).divide(BigInteger.valueOf(index));

            Integer data = value.intValue();
            retValList.add(data);
            lastValue = data;
        }
        return retValList;
    }

    public void  test_getRow_119(){
        int n = 3;
        List<Integer>retValList = getRow(n);
        Utility.printIntegerList(retValList);

    }

    /***** 118
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     *
     * Example 1:
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

     * Example 2:
     * Input: numRows = 1
     * Output: [[1]]
     *
     * Constraints: 1 <= numRows <= 30
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>>retValList = new ArrayList<List<Integer>>(numRows);
        List<Integer> coEffListForOne = new ArrayList<Integer>();
        int index = 2;

        coEffListForOne.add(1);
        retValList.add(coEffListForOne);
        if(numRows > 1){
            while(index <= numRows){
                List<Integer> coEffList = new ArrayList<Integer>(index);
                coEffList.add(1);
                if(index > 2) {
                    List<Integer> prevCoEffList = retValList.get(index -2);
                    for (int i = 1; i < index - 1; i++) {
                        int data = prevCoEffList.get(i - 1) + prevCoEffList.get(i);
                        coEffList.add(data);
                    }
                }
                coEffList.add(1);
                retValList.add(coEffList);
                index++;
            }
        }
        return retValList;
    }

    public void  test_generate_118(){
        int n = 30;
        List<List<Integer>>retValList = generate(n);
        retValList.forEach(x -> Utility.printIntegerList(x));

    }

    /**********  844
     *Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character. Note that after backspacing an empty text, the text will continue empty.
     *
     * Example 1:
     * Input: s = "ab#c", t = "ad#c"
     * Output: true
     * Explanation: Both s and t become "ac".
     *
     * Example 2:
     * Input: s = "ab##", t = "c#d#"
     * Output: true
     * Explanation: Both s and t become "".
     *
     * Example 3:
     * Input: s = "a#c", t = "b"
     * Output: false
     * Explanation: s becomes "c" while t becomes "b".
     *
     * Constraints:
     * 1 <= s.length, t.length <= 200
     * s and t only contain lowercase letters and '#' characters.
     *
     * Follow up: Can you solve it in O(n) time and O(1) space?
     */
    public boolean backspaceCompare(String s, String t) {
        Stack<Character>sStack = new Stack<>();
        Stack<Character>tStack = new Stack<>();
        int len = (s.length() > t.length()) ? s.length() : t.length();
        for(int idx = 0; idx < len; idx++){
            //For S
            if(idx < s.length()){
                Character sChar = s.charAt(idx);
                if(sChar == '#'){
                    if(!sStack.isEmpty())
                        sStack.pop();
                }
                else{
                    sStack.push(sChar);
                }
            }
            //For T
            if(idx < t.length()){
                Character tChar = t.charAt(idx);
                if(tChar == '#'){
                    if(!tStack.isEmpty())
                        tStack.pop();
                }
                else{
                    tStack.push(tChar);
                }
            }
        }
        String updateS ="";
        String updateT = "";
        while(!sStack.isEmpty()){
            updateS += sStack.pop();
        }
        while(!tStack.isEmpty()){
            updateT += tStack.pop();
        }
        return updateS.equals(updateT);
    }

    public void  test_backspaceCompare_844(){
        String a = null;
        String b = null;

        a= "y#fo##f";
        b = "y#f#o##f";
        System.out.println("Expect : True, Actual :" + backspaceCompare(a,b));

        a="abc#de#f#ghi#jklmn#op#";
        b = "abdghjklmo";
        System.out.println("Expect : True, Actual :" + backspaceCompare(a,b));

        a= "ab##";
        b = "c#d#";
        System.out.println("Expect : True, Actual :" + backspaceCompare(a,b));

        a= "a#c";
        b = "d";
        System.out.println("Expect : False, Actual :" + backspaceCompare(a,b));
    }

    /*
    There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
In each step, you will choose any 3 piles of coins (not necessarily consecutive).
Of your choice, Alice will pick the pile with the maximum number of coins.
You will pick the next pile with the maximum number of coins.
Your friend Bob will pick the last pile.
Repeat until there are no more piles of coins.
Given an array of integers piles where piles[i] is the number of coins in the ith pile.

Return the maximum number of coins that you can have.

Example 1:
Input: piles = [2,4,1,2,7,8]
Output: 9
Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with 7 coins and Bob the last one.
Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with 2 coins and Bob the last one.
The maximum number of coins which you can have are: 7 + 2 = 9.
On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7) you only get 2 + 4 = 6 coins which is not optimal.

Example 2:
Input: piles = [2,4,5]
Output: 4

Example 3:
Input: piles = [9,8,7,6,5,1,2,3,4]
Output: 18

Constraints:
3 <= piles.length <= 105
piles.length % 3 == 0
1 <= piles[i] <= 104
     */
    public int maxCoins(int[] piles) {

        int startIndex = 0;
        int endIndex = piles.length;
        int totSum = 0;
        Arrays.sort(piles);

        while(startIndex < endIndex){
            totSum += piles[endIndex -2];
            startIndex++;
            endIndex -= 2;
        }
        return totSum;
    }

    /* #1685
    You are given an integer array nums sorted in non-decreasing order.
Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

Example 1:
Input: nums = [2,3,5]
Output: [4,3,5]
Explanation: Assuming the arrays are 0-indexed, then
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.

Example 2:
Input: nums = [1,4,6,8,10]
Output: [24,15,13,15,21]

Constraints:
2 <= nums.length <= 105
1 <= nums[i] <= nums[i + 1] <= 104
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int sum[] = new int[nums.length];
        for(int index = 0; index <= nums.length-1; index++){
            int curSum = 0;

            for(int sumIndex =0; (sumIndex <= nums.length-1) ; sumIndex++){
                if(sumIndex > index){
                    curSum+= nums[sumIndex] - nums[index];
                }
                else {
                    curSum += nums[index] - nums[sumIndex];
                }
            }
            sum[index] = curSum;
            while(index != nums.length-1 && nums[index] == nums[index + 1]){
                sum[++index] = curSum;
            }
        }
        return  sum;
    }

    public int[] getSumAbsoluteDifferences_Alternate(int[] nums){
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int left = 0;
        int right = sum;

        int[] r = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            right -= n;

            r[i] = n * i - left + right - n * (nums.length - i - 1);

            left += n;
        }

        return r;
    }

    public void test_getSumAbsoluteDifferences_1685(){
        int nums[];
        nums = new int[]{2,3,5};
        int result[];
        result = getSumAbsoluteDifferences(nums);
        System.out.println("Expected: 4,3,5. Actual: ");
        Utility.printIntegerArray(result);

        nums = new int[]{1,4,6,8,10};
        result = getSumAbsoluteDifferences(nums);
        System.out.println("Expected: 24,15,13,15,21. Actual: ");
        Utility.printIntegerArray(result);
    }
/*
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2

Constraints:
1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
 */
    public int jump(int[] nums) {
        int distance = nums.length;
        int jumpCounter =0;
        int index = 0;

        if(nums.length < 2) return 0;
        if(nums.length == 2) return 1;

        while(index < nums.length){
            int curMaxJumpVal = nums[index];
            int maxJumpVal = -1;
            for(int innerIndex = 1; innerIndex <= curMaxJumpVal; innerIndex++){
                if(index + innerIndex < nums.length) {
                    if (nums[index + innerIndex] >= distance) {
                        ++jumpCounter;
                        index = nums.length;
                        break;
                    } else {
                        if (nums[index + innerIndex] > maxJumpVal) {
                            maxJumpVal = nums[index + innerIndex];
                        }
                    }
                }
                if(index != nums.length){
                    ++jumpCounter;
                    index += maxJumpVal;
                    distance -= index;
                }
            }
        }
        return jumpCounter;
    }

    public void test_jump_45(){
        int nums[];

        nums = new int[]{1,2,3};
        System.out.println("Expected output: 2, Actual :"+ jump(nums));

        nums = new int[]{2,3,0,1,4};
        System.out.println("Expected output: 2, Actual :"+ jump(nums));
    }

    /*
    #1688
    You are given an integer n, the number of teams in a tournament that has strange rules:

If the current number of teams is even, each team gets paired with another team. A total of n / 2 matches are played, and n / 2 teams advance to the next round.
If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired. A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
Return the number of matches played in the tournament until a winner is decided.

Example 1:
Input: n = 7
Output: 6
Explanation: Details of the tournament:
- 1st Round: Teams = 7, Matches = 3, and 4 teams advance.
- 2nd Round: Teams = 4, Matches = 2, and 2 teams advance.
- 3rd Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
Total number of matches = 3 + 2 + 1 = 6.

Example 2:
Input: n = 14
Output: 13
Explanation: Details of the tournament:
- 1st Round: Teams = 14, Matches = 7, and 7 teams advance.
- 2nd Round: Teams = 7, Matches = 3, and 4 teams advance.
- 3rd Round: Teams = 4, Matches = 2, and 2 teams advance.
- 4th Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
Total number of matches = 7 + 3 + 2 + 1 = 13.

Constraints:

1 <= n <= 200
     */
    public int numberOfMatches(int n) {
        int matchCount = 0;

        while(n > 1){
            matchCount += n /2;
            n = (n+1)/ 2;
        }
        return matchCount;
    }

    /*
    #1716. Calculate Money in Leetcode Bank
    Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.

He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.



Example 1:

Input: n = 4
Output: 10
Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.
Example 2:

Input: n = 10
Output: 37
Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2nd Monday, Hercy only puts in $2.
Example 3:

Input: n = 20
Output: 96
Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
     */

    public int totalMoney(int n) {

        int noOfweek = (n / 7);
        int restOfDays = (n % 7);
        int totalWeeklyMinSaving = 28; // n * (n+ 1) / 2

        int totMoney = 7 * (noOfweek * (noOfweek - 1) /2)    //Extra weekly saving for week 2 onwards
                              + noOfweek * totalWeeklyMinSaving  //total weekly savings in if starts from 1
                              + noOfweek * restOfDays                   // Extra saving for part of week days
                              + (restOfDays * (restOfDays + 1) / 2); // Total saving for part of week days if starts from 1

        return totMoney;
    }

    /*
    #1903. Largest Odd Number in String
    You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.
A substring is a contiguous sequence of characters within a string.
Example 1:
Input: num = "52"
Output: "5"
Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.

Example 2:
Input: num = "4206"
Output: ""
Explanation: There are no odd numbers in "4206".

Example 3:
Input: num = "35427"
Output: "35427"
Explanation: "35427" is already an odd number.

Constraints:
1 <= num.length <= 105
num only consists of digits and does not contain any leading zeros.
     */

    public String largestOddNumber(String num) {
        int itrIndex = num.length() -1;
        int subStrIndex = -1;

        while(itrIndex >= 0){
            if(isOdd(num.charAt(itrIndex))){
                subStrIndex = itrIndex + 1;
                break;
            }
            itrIndex--;
        }

        return  (subStrIndex == -1) ? "" : num.substring(0,subStrIndex);

    }
     private boolean isOdd(Character s){
        boolean retVal = false;
        try {
            Integer i = Integer.parseInt(s+"");
            if(i%2 != 0)
                retVal = true;
        }
        catch(Exception e){
            retVal = false;
        }
        return retVal;
     }

     public void test_largestOddNumber_1903(){
        String s1 = "52";
         String s2 = "4206";
         String s3 = "35427";

         System.out.println("Number: "+ s1+ " Expected: 5 Actual: "+ largestOddNumber(s1));
         System.out.println("Number: "+ s2+ " Expected:  Actual: "+ largestOddNumber(s2));
         System.out.println("Number: "+ s3+ " Expected: 35427 Actual: "+ largestOddNumber(s3));


     }

     /*
     #867. Transpose Matrix
     Given a 2D integer array matrix, return the transpose of matrix.
    The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]

Example 2:
Input: matrix = [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
-109 <= matrix[i][j] <= 109
      */
    public int[][] transpose(int[][] matrix) {
        int column = matrix[0].length;
        int row = matrix.length;

        int [][] newMatrix = new int[column][row];

        for(int i = 0; i< row; i++){
            for(int j = 0; j <column; j++){
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    /*
    #242. Valid Anagram
    Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
     */
    public boolean isAnagram(String s, String t) {
        s = sortedString(s);
        t = sortedString(t);
        return t.equals(s);
    }

    private String sortedString(String s){
        char[] sCharArr = s.toCharArray();
        Arrays.sort(sCharArr);
        return new String(sCharArr);
    }

    /*
    2108. Find First Palindromic String in the Array
    Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".
A string is palindromic if it reads the same forward and backward.
Example 1:
Input: words = ["abc","car","ada","racecar","cool"]
Output: "ada"
Explanation: The first string that is palindromic is "ada".
Note that "racecar" is also palindromic, but it is not the first.

Example 2:
Input: words = ["notapalindrome","racecar"]
Output: "racecar"
Explanation: The first and only string that is palindromic is "racecar".

Example 3:
Input: words = ["def","ghi"]
Output: ""
Explanation: There are no palindromic strings, so the empty string is returned.

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists only of lowercase English letters.
     */
    public String firstPalindrome(String[] words) {

        //Optional<String> retValOptional = Arrays.stream(words).filter(s -> isPalindrome(s, 0, s.length()-1)).findFirst();
        //return (retValOptional.isEmpty() ? "": retValOptional.get());
        String retVal = null;
        for (String str: words) {
            boolean checkPali = isPalindrome(str, 0, str.length()-1);
            if(checkPali){
                retVal = str;
                break;
            }
        }
        return retVal;
    }

    boolean isPalindrome(String s,int startIndex, int endIndex){
        boolean retVal = false;
        if(s.length() == 1)
            retVal = true;
        while(startIndex < endIndex){
            if(s.charAt(startIndex) == s.charAt(endIndex)){
                retVal =  isPalindrome(s, ++startIndex, --endIndex);
            }
        }
        return retVal;
    }

    public void test_firstPalindrome_2108(){
        String[] arrStr = new String[] {"abc","car","ada","racecar","cool"};
        System.out.println("First Palindromic String: " + firstPalindrome(arrStr));
    }


    /*
    Given a positive integer n, find the pivot integer x such that:
The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.

Example 1:
Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.

Example 2:
Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.

Example 3:
Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.

Constraints:
1 <= n <= 1000
     */
    public int pivotInteger(int n) {
        return 0;
    }

    /*
    #1249. Minimum Remove to Make Valid Parentheses
    Given a string s of '(' , ')' and lowercase English characters.
    Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
    Formally, a parentheses string is valid if and only if:
    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.

    Example 1:
    Input: s = "lee(t(c)o)de)"
    Output: "lee(t(c)o)de"
    Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

    Example 2:
    Input: s = "a)b(c)d"
    Output: "ab(c)d"

    Example 3:
    Input: s = "))(("
    Output: ""
    Explanation: An empty string is also valid.

    Constraints:

    1 <= s.length <= 105
    s[i] is either'(' , ')', or lowercase English letter.
     */
    public String minRemoveToMakeValid(String s) {

        StringBuilder retStringBuilder = new StringBuilder();
        Stack<Character>bracketStack = new Stack<>();
        char[] charArray = s.toCharArray();

        for(char c : charArray){
            if(c == '('){
                retStringBuilder.append(c+"");
                bracketStack.push(c);
            }
            else if (c == ')') {
                if(!bracketStack.isEmpty()) {
                    bracketStack.pop();
                    retStringBuilder.append(c+"");
                }
            }
            else{
                retStringBuilder.append(c+"");
            }
        }
        if(!bracketStack.isEmpty()){

            while(!bracketStack.isEmpty()){
                char x = bracketStack.pop();
                retStringBuilder.deleteCharAt(retStringBuilder.lastIndexOf(x+""));
            }

        }

        return retStringBuilder.toString();
    }

    void test_minRemoveToMakeValid_1249(){
        String s = "lee(t(c)o)de)";
        System.out.println("Input String: "+ s);
        System.out.println("Output String : "+ minRemoveToMakeValid(s));

        s = "a)b(c)d";
        System.out.println("Input String: "+ s);
        System.out.println("Output String : "+ minRemoveToMakeValid(s));

        s = "))((";
        System.out.println("Input String: "+ s);
        System.out.println("Output String : "+ minRemoveToMakeValid(s));
    }

    /*
    678. Valid Parenthesis String
    Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
    The following rules define a valid string:
    -Any left parenthesis '(' must have a corresponding right parenthesis ')'.
    -Any right parenthesis ')' must have a corresponding left parenthesis '('.
    -Left parenthesis '(' must go before the corresponding right parenthesis ')'.
    -'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".


    Example 1:
    Input: s = "()"
    Output: true

    Example 2:
    Input: s = "(*)"
    Output: true

    Example 3:
    Input: s = "(*))"
    Output: true

    Constraints:
    1 <= s.length <= 100
    s[i] is '(', ')' or '*'.
     */
    public boolean checkValidString(String s) {
        boolean retVal = false;
        Stack<Character>bracketStack = new Stack<>();
        char[] charArray = s.toCharArray();
        int starCount = 0;
        int iterationCount = 0;
        for(char c : charArray){
            if(c == '('){
                bracketStack.push(c);
            }
            else if (c == ')') {
                if(!bracketStack.isEmpty()) {
                    bracketStack.pop();
                }
                else if(starCount ==0){
                    break;
                }
                else
                    starCount--;
            }
            else if (c == '*') {
                starCount++;
            }
            iterationCount++;
        }
        if(iterationCount != charArray.length){
            retVal = false;
        }
        else{
            if(bracketStack.isEmpty()){
                retVal = true;
            }
            else if(bracketStack.size() <= starCount){
                retVal = true;
            }
        }

        return retVal;

    }
    void test_checkValidString_678(){
        String s = null;

        s = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";  //Not Working
        System.out.println("Input String: "+ s);
        System.out.println("Expected: false, Received : "+ checkValidString(s));

        /*
        s = "(((((()*)(*)*))())())(()())())))((**)))))(()())()";
        System.out.println("Input String: "+ s);
        System.out.println("Input String: "+ s);
        System.out.println("Expected: false, Received : "+ checkValidString(s));



        s = "(*)";
        System.out.println("Input String: "+ s);
        System.out.println("Expected: true, Received : "+ checkValidString(s));

        s = ")(";
        System.out.println("Input String: "+ s);
        System.out.println("Expected: false, Received : "+ checkValidString(s));

        s = "(*))";
        System.out.println("Input String: "+ s);
        System.out.println("Expected: true, Received : "+ checkValidString(s));
        */
    }


    /*
    #2073. Time Needed to Buy Tickets

There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
Return the time taken for the person at position k (0-indexed) to finish buying tickets.

Example 1:
Input: tickets = [2,3,2], k = 2
Output: 6
Explanation:
- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.

Example 2:
Input: tickets = [5,1,1,1], k = 0
Output: 8
Explanation:
- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
- In the next 4 passes, only the person in position 0 is buying tickets.
The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.

Constraints:
n == tickets.length
1 <= n <= 100
1 <= tickets[i] <= 100
0 <= k < n
     */
    public int timeRequiredToBuy(int[] tickets, int k) {

        int totalTimeTaken = 0;

        if(tickets.length < 1)
            return totalTimeTaken;
        else if(tickets.length == 1 && k ==0)
            return tickets[0];

        while(tickets[k] > 0) {
            for (int i = 0; i < tickets.length; i++) {
                int item = tickets[i];
                if (item > 0) {
                    tickets[i] = --item;
                    totalTimeTaken++;
                    if(tickets[k] == 0)
                        break;
                }
            }
        }
        return totalTimeTaken;
    }

    void test_timeRequiredToBuy_2073(){
        int [] tickets1 = new int[] {84,49,5,24,70,77,87,8};
        System.out.println("Scenario 1: Expected 154, Received: "+ timeRequiredToBuy(tickets1,3));
        /*
        int [] tickets2 = new int[] {5,1,1,1};
        System.out.println("Scenario 2: Expected 8, Received: "+ timeRequiredToBuy(tickets2,0));

         */
    }

    /*
    #950. Reveal Cards In Increasing Order
    You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer on the ith card is deck[i].
   You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
   You will do the following steps repeatedly until all cards are revealed:
     1.Take the top card of the deck, reveal it, and take it out of the deck.
     2.If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
     3.If there are still unrevealed cards, go back to step 1. Otherwise, stop.
     4.Return an ordering of the deck that would reveal the cards in increasing order.

Note that the first entry in the answer is considered to be the top of the deck.

Example 1:
Input: deck = [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]
Explanation:
We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
We reveal 13, and move 17 to the bottom.  The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.

Example 2:
Input: deck = [1,1000]
Output: [1,1000]

Constraints:
1 <= deck.length <= 1000
1 <= deck[i] <= 106
All the values of deck are unique.

     */
    public int[] deckRevealedIncreasing(int[] deck) {

        int[] retDeck = new int[deck.length];
        class Node{
            Node prev;
            Integer data;
            Node next;

            public Node(){
                prev = null;
                data= null;
                next = null;
            }
            public Node(Integer n){
                prev = null;
                data= n;
                next = null;
            }
        }

        class DLinkList{
            Node start = null;
            Node end = null;

            public void printInOrder(){

                if(start != null){
                    System.out.print("Print in order: ");
                    Node n = start;
                    while(n != null){
                        System.out.print(""+ n.data + ", ");
                        n = n.next;
                    }
                    System.out.println();
                }
            }

            public void printInReverseOrder(){
                if(end != null){
                    System.out.print("Print in reverse order: ");
                    Node n = end;
                    while(n != null){
                        System.out.print(""+ n.data+ ", ");
                        n = n.prev;
                    }
                    System.out.println();
                }
            }
        }
        DLinkList dLink = null;
        Integer[] sortedDeck = Arrays.stream(deck).boxed().toArray(Integer[]::new);
        Arrays.sort(sortedDeck, Collections.reverseOrder());
        if(deck.length == 1)
            return deck;

        for(Integer n : sortedDeck){
            Node node = new Node(n);
            System.out.println(" New Number : "+ n);
            if(dLink == null){
                dLink = new DLinkList();
                dLink.start = node;
                dLink.end = node;
            }
            else if(dLink.start == dLink.end){
                node.next = dLink.end;
                dLink.start = node;
                dLink.end.prev = node;
            }
            else{
                Node endNode = dLink.end;
                Node startNode = dLink.start;

                dLink.end = endNode.prev;
                dLink.end.next = null;
                if(dLink.end.prev == null){
                    dLink.end.prev = endNode;
                }

                node.next = endNode;
                endNode.prev = node;
                endNode.next = startNode;
                startNode.prev = endNode;
                dLink.start = node;
            }
            dLink.printInOrder();
            dLink.printInReverseOrder();
        }

        Node traverseNode = dLink.start;
        int i =0;
        while (traverseNode != null){
            retDeck[i++] = traverseNode.data;
            traverseNode = traverseNode.next;

        }

        return retDeck;

    }

    void test_deckRevealedIncreasing_950(){
        int nums[] = null;

        nums = new int[]{17,13,11,2,3,5,7};
        System.out.println("Expected: 2,13,3,11,5,17,7");
        System.out.print("Received : ");
        Arrays.stream(deckRevealedIncreasing(nums)).boxed().forEach(System.out::println);
    }

    /*
    1971. Find if Path Exists in Graph
    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
    You want to determine if there is a valid path that exists from vertex source to vertex destination.
    Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

    Example 1:
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2

    Example 2:
    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
    Output: false
    Explanation: There is no path from vertex 0 to vertex 5.

    Constraints:
    1 <= n <= 2 * 105
    0 <= edges.length <= 2 * 105
    edges[i].length == 2
    0 <= ui, vi <= n - 1
    ui != vi
    0 <= source, destination <= n - 1
    There are no duplicate edges.
    There are no self edges.
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>>edgeMap = new HashMap<>();
        return false;
    }


    public void test_validPath_1971()
    {
        int [][]edges1 = {{0,1},{1,2},{2,0}};
        System.out.println("Scenario 1: Expected= true, Received: "+ validPath(3,edges1,0,2));

        int [][]edges2 = {{0,1},{0,2},{3,5},{5,4},{4,3}};
        System.out.println("Scenario 1: Expected= false, Received: "+ validPath(3,edges2,0,2));
    }


    /*
    1590. Make Sum Divisible by P
    Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
    Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
    A subarray is defined as a contiguous block of elements in the array.

    Example 1:
    Input: nums = [3,1,4,2], p = 6
    Output: 1
    Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.

    Example 2:
    Input: nums = [6,3,5,2], p = 9
    Output: 2
    Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.

    Example 3:
    Input: nums = [1,2,3], p = 3
    Output: 0
    Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
     */
    public int minSubarray(int[] nums, int p) {
        int minSize = -1;
        long remainder = sumArray(nums) % p;
        if(remainder == 0){
            //Sum matches
            return 0;
        }
        else{
            //Check 1 size sub array
            if(Arrays.stream(nums).anyMatch(n -> n == remainder)){
                //Remove one element
                minSize = 1;
            }
            else{
                for(int subSize = 2; subSize < nums.length -1; subSize++){
                    for(int index =0; index <= nums.length - subSize; index++){
                        int[] subArr = new int[subSize];
                        System.arraycopy(nums,index,subArr,0,subSize);
                        if(sumArray(subArr) == remainder){
                            //Sub array found
                            minSize = nums.length - subSize;
                        }
                    }
                }
            }
        }

        return minSize;
    }

    private long sumArray(int[] nums){
        return Arrays.stream(nums).mapToLong(n-> Long.valueOf(n)).sum();
    }

    public void sumDivisible_1590(){
        int[] nums = new int[] {26,19,11,14,18,4,7,1,30,23,19,8,10,6,26,3};
        System.out.println("Minimun array size :" + minSubarray(nums, 26));
    }

    /*
    2593. Find Score of an Array After Marking All Elements
    You are given an array nums consisting of positive integers.

Starting with score = 0, apply the following algorithm:

Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.

Example 1:
Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.

Example 2:
Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.
 Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 106
     */
    public long findScore(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }
        else if(nums.length == 2){
            return (nums[0] < nums[1]) ? nums[0] : nums[1];
        }
        else{
            long score = 0;
            Map<Integer, List<Integer>> elementMap = new TreeMap<Integer, List<Integer>>();
            for(int i=0 ; i < nums.length; i++){
                if(elementMap.containsKey(nums[i])){
                    elementMap.get(nums[i]).add(i);
                }
                else{
                    List<Integer>indexList = new ArrayList<Integer>();
                    indexList.add(i);
                    elementMap.put(nums[i], indexList);
                }
            }

            for(Map.Entry<Integer, List<Integer>> element : elementMap.entrySet()){
                List<Integer> indexList = element.getValue();
                for(Integer x : indexList){
                    if(nums[x] == element.getKey()){
                        score += nums[x];
                        nums[x] = -1;
                        if((x-1) >= 0){
                            nums[x-1] = -1;
                        }
                        if((x+1) < nums.length){
                            nums[x+1] = -1;
                        }
                    }
                }
            }
            return score;
        }
    }

    public void test_findScore_2593(){
        int[] nums = {2,1,3,4,5,2};
        System.out.println("Scenario 1, Expected: 7, actual :" + findScore(nums));
        int[] nums1 = {2,3,5,1,3,2};
        System.out.println("Scenario 2, Expected: 5, actual :" + findScore(nums1));
        int[] nums2 = {2,23};
        System.out.println("Scenario 3, Expected: 2, actual :" + findScore(nums2));
    }

    /*
    3011. Find if Array Can Be Sorted
    You are given a 0-indexed array of positive integers nums.
    In one operation, you can swap any two adjacent elements if they have the same number of
    set bits
    . You are allowed to do this operation any number of times (including zero). Return true if you can sort the array, else return false.

    Example 1:
    Input: nums = [8,4,2,30,15]
    Output: true
    Explanation: Let's look at the binary representation of every element. The numbers 2, 4, and 8 have one set bit each with binary representation "10", "100", and "1000" respectively. The numbers 15 and 30 have four set bits each with binary representation "1111" and "11110".
    We can sort the array using 4 operations:
    - Swap nums[0] with nums[1]. This operation is valid because 8 and 4 have one set bit each. The array becomes [4,8,2,30,15].
    - Swap nums[1] with nums[2]. This operation is valid because 8 and 2 have one set bit each. The array becomes [4,2,8,30,15].
    - Swap nums[0] with nums[1]. This operation is valid because 4 and 2 have one set bit each. The array becomes [2,4,8,30,15].
    - Swap nums[3] with nums[4]. This operation is valid because 30 and 15 have four set bits each. The array becomes [2,4,8,15,30].
    The array has become sorted, hence we return true.
    Note that there may be other sequences of operations which also sort the array.

    Example 2:
    Input: nums = [1,2,3,4,5]
    Output: true
    Explanation: The array is already sorted, hence we return true.

    Example 3:
    Input: nums = [3,16,8,4,2]
    Output: false
    Explanation: It can be shown that it is not possible to sort the input array using any number of operations.
    Constraints:
    1 <= nums.length <= 100
    1 <= nums[i] <= 28
     */
    public boolean canSortArray(int[] nums) {
        //int[] sortedNum = Arrays.
        return false;
    }

    /*
    1792. Maximum Average Pass Ratio
    There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.
You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

Example 2:
Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485

Constraints:
1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        //Didn't work for large data, use priorityQ instead and class representation of "Classes"
        double result = 0.0;
        if(extraStudents < 1){
            result = calculateAvgPassRatio(classes);
        }
        else{

            int count = 1;
            while(count <= extraStudents){
                int index = -1;
                double maxProposedAvgRatioChange = 0.0;
                double curMaxAvgRatio = 0.0;
                double avgRatioChange = 0.0;
                for(int i = 0; i< classes.length; i++){
                    double curClassAvgRatio = classes[i][0] / ((double) classes[i][1]);
                    double proposedRatio = (classes[i][0] +1) / ((double) (classes[i][1] + 1));
                    avgRatioChange = proposedRatio - curClassAvgRatio;
                    if(avgRatioChange > maxProposedAvgRatioChange){
                        maxProposedAvgRatioChange = avgRatioChange;
                        curMaxAvgRatio = curClassAvgRatio;
                        index = i;
                    }
                    else if(avgRatioChange == maxProposedAvgRatioChange){
                        if(curClassAvgRatio >= curMaxAvgRatio){
                            maxProposedAvgRatioChange = avgRatioChange;
                            curMaxAvgRatio = curClassAvgRatio;
                            index = i;
                        }
                    }
                }
                if(index != -1){
                    classes[index][0] += 1;
                    classes[index][1] += 1;
                }
                //System.out.println("Index: "+ index + " avgRatioChange: "+  avgRatioChange);
                //print2DArray(classes);
                count++;
            }
            result = calculateAvgPassRatio(classes);
        }

        return result;
    }
    private double calculateAvgPassRatio(int[][] classes){
        double result = 0.0;
        for(int i = 0; i< classes.length; i++){
            result += classes[i][0] / ((double) classes[i][1]);
        }
        result = (result/(double)classes.length);
        BigDecimal bd = new BigDecimal(String.valueOf(result));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void test_maxAverageRatio_1792(){
        int [][]classes1 = {{1,2},{3,5},{2,2}};
        System.out.println("Scanerio 1: Expected: 0.78333 Actual: "+ maxAverageRatio(classes1, 2));

        int [][]classes2 = {{2,4},{3,9},{4,5},{2,10}};
        System.out.println("Scanerio 2: Expected: 0.53485 Actual: "+ maxAverageRatio(classes2, 4));
    }

    private void print2DArray(int[][]classes){
        for(int i =0; i< classes.length; i++){
            for(int j = 0; j <classes[i].length; j++ ){
                System.out.print(" "+ classes[i][j]);
            }
            System.out.println();
        }
    }

    /*
    2185. Counting Words With a Given Prefix
    You are given an array of strings words and a string pref. Return the number of strings in words that contain pref as a prefix.
A prefix of a string s is any leading contiguous substring of s.

Example 1:
Input: words = ["pay","attention","practice","attend"], pref = "at"
Output: 2
Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".

Example 2:
Input: words = ["leetcode","win","loops","success"], pref = "code"
Output: 0
Explanation: There are no strings that contain "code" as a prefix.

Constraints:
1 <= words.length <= 100
1 <= words[i].length, pref.length <= 100
words[i] and pref consist of lowercase English letters.

     */
    public int prefixCount(String[] words, String pref) {
        return (int)Arrays.asList(words).stream().filter(x-> x.startsWith(pref)).count();
    }

    /* 69. Sqrt(x)
    Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
You must not use any built-in exponent function or operator.
For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
Example 1:
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
*/
    public int mySqrt(int x) {

        if(x <2)
            return x;

        int mid = 0;
        int low = 1;
        int high = x -1;
        while(low <= high){
            mid = low + ((high - low) /2);
            Long num = (long) mid * mid;
            if(num > (long)x){
                high = mid -1;
            }
            else if(num < (long)x){
                low = mid +1;
            }
            else{
                return mid;
            }
            System.out.println("low: "+ low + " mid: "+ mid + " High: "+ high);
        }

        return high;
    }

    public void test_69_sqrtx(){
        int x = 2147483647;

        System.out.println("Expected: 46340, received: "+ mySqrt(2147483647));
    }



}
