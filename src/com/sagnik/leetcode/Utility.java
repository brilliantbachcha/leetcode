package com.sagnik.leetcode;

import java.util.List;

public class Utility {
    public static void printIntegerList(List<Integer> elemList){
        int count = 0;
        for (Integer i : elemList){
            if(count < elemList.size()-1)
                System.out.print(i + ", ");
            else
                System.out.print(i);

            count++;
        }
        System.out.println();
    }

    public static void printIntegerArray(int []elemList){
        int count = 0;
        for (int i : elemList){
            if(count < elemList.length-1)
                System.out.print(i + ", ");
            else
                System.out.print(i);

            count++;
        }
        System.out.println();
    }

    public static  void printList(List<Integer>intList){
        for (Integer i : intList) {
            System.out.print(i + " ");
        }
    }

    public static void printAnyList(List<?> printlist){
        for (Object element : printlist) {
            System.out.println(element);
        }
    }

}
