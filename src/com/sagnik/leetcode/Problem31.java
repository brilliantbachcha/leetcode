package com.sagnik.leetcode;

public class Problem31 {
    /* Not solved
    3170. Lexicographically Minimum String After Removing Stars
    You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
While there is a '*', do the following operation:
Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the lexicographically smallest resulting string after removing all '*' characters.

Example 1:
Input: s = "aaba*"
Output: "aab"
Explanation:
We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

Example 2:
Input: s = "abc"
Output: "abc"
Explanation:
There is no '*' in the string.

Constraints:

1 <= s.length <= 105
s consists only of lowercase English letters and '*'.
The input is generated such that it is possible to delete all '*' characters.
     */
    public String clearStars(String s) {
        int firstStarIndex = s.indexOf('*');
        if(firstStarIndex < 0){
            return s;
        }
        else{
            String minStr = s.substring(0, firstStarIndex);
            int smallCharindex = findSmallest(minStr);
            if(smallCharindex ==0){
                minStr = minStr.substring(1);
            }
            else if(smallCharindex == minStr.length() -1){
                minStr = minStr.substring(0,smallCharindex);
            }
            else{
                minStr = minStr.substring(0,smallCharindex-1) + minStr.substring(smallCharindex+1);
            }
            String lexStr = minStr + s.substring(firstStarIndex+1).replace("*","");
            return lexStr;
        }
    }

    private int findSmallest(String str){
        char min = 'z';
        int count = 0;
        for(char c: str.toCharArray()){
            if(c < min){
                min = c;
            }
            count++;
        }

        return str.lastIndexOf(min);
    }

    public void test_clearStars_3170(){
        String input = "aaba*";
        System.out.println("Expected: aab, Actual :" + clearStars(input));

    }
}
