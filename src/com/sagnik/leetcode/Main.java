package com.sagnik.leetcode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();
        Problem50 prob50 = new Problem50();
        Problem31 prob31 = new Problem31();
        Problem25 prob25 = new Problem25();
        Problem21 prob21 = new Problem21();
        Problem20 prob20 = new Problem20();
        Problem34 prob34 = new Problem34();
        /*String s = "";
        System.out.println("Enter the String ");
        Scanner sc = new Scanner(System.in);*/

        /*s = sc.nextLine();
        System.out.println(problem.reverse(s)); */
        /*s = sc.nextLine();
        String[] x = s.split(",");
        int[]a  = Arrays.stream(x).map(i->Integer.parseInt(i)).mapToInt(i->i).toArray();
        int q = problem.removeDuplicates(a);
        System.out.println("Result: "+ q + " Size: "+ a.length);
        for(int y : a)
        System.out.print(y + ",");
        System.out.println();*/
        /*s = sc.nextLine();
        System.out.println("Enter the Rows");
        int x = sc.nextInt();
        System.out.println(problem.convert(s,x));*/

        /*s = sc.nextLine();
        System.out.println("Enter search string[Comma separated]");
        String x = sc.nextLine();
        String[] chk = x.split(",");
        System.out.println(problem.findSubstring(s, chk));*/

        //problem.test_maxLength();
        //problem.testLetterCombinations();
        //problem.testArrayMerge();
        //problem.test_majorityElement();
        /*System.out.println("Enter a number between 1 to 8:");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        problem.test_generateParenthesis(x);*/

        //problem.test_winnerOfGame();
        //problem.test_numIdenticalPairs();
        //problem.test_majorityElement_229();
        //problem.test_getRow_119();
        //problem.test_generate_118();
        //problem.test_backspaceCompare_844();
        //problem.test_getSumAbsoluteDifferences_1685();
        //problem.test_jump_45();
        //problem.test_largestOddNumber_1903();
        //problem.test_firstPalindrome_2108();
        //problem.test_minRemoveToMakeValid_1249();
        //problem.test_checkValidString_678();
        //problem.test_timeRequiredToBuy_2073();
        //problem.test_deckRevealedIncreasing_950();
        //problem.sumDivisible_1590();
        //problem.test_findScore_2593();
        //problem.test_maxAverageRatio_1792();
        //problem.test_69_sqrtx();
        //prob50.test_threeSumClosest_16();
        //prob31.test_clearStars_3170();
        //prob25.test_minMaxDifference_2566();
        //prob21.test_divideString_2138();
        //prob20.test_kMirror_2081();
        //prob20.test_maxSubsequence_2099();
        prob34.test_numOfUnplacedFruits_3477();

    }
}
