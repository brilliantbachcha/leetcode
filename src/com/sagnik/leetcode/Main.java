package com.sagnik.leetcode;

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();
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

        problem.test_threeSum();
        //problem.test_maxLength();
        //problem.testLetterCombinations();

    }
}
