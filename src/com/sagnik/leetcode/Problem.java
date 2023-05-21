package com.sagnik.leetcode;

import java.util.*;
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

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.
 */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>>resultSet = new HashSet<List<Integer>>();

        for(int i =0 ;i< nums.length; i++){
            for(int j =0 ;j< nums.length && (j != i); j++){
                for(int k =0 ; k <nums.length && (k != i) && (k!= j); k++){
                    if(nums[i] + nums[j] + nums[k] ==0){
                        List<Integer> intList = new ArrayList<Integer>(3);
                        intList.add(nums[i]);
                        intList.add(nums[j]);
                        intList.add(nums[k]);
                        Collections.sort(intList);
                        resultSet.add(intList);
                        printResultList(new ArrayList<List<Integer>>(resultSet));
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(resultSet);
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

        nums = new int[] {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        resultList = threeSum(nums);
        //printResultList(resultList);
    }

    private void printResultList(List<List<Integer>>resultList) {
        System.out.println("Result Count: " + resultList.size());
        /*Print Value*/
        for (List<Integer> intList : resultList) {
            printList(intList);
            System.out.print("   ");
        }
        System.out.println();
    }

    private void printList(List<Integer>intList){
        for (Integer i : intList) {
            System.out.print(i + " ");
        }
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

}
