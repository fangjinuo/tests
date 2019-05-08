package com.fjn.tests.java8;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

/***
 * Lambda 的本质是在运行时（编译时不会产生的）产生一个匿名内部类
 * MethodReference 的本质是产生一个Lambda，并在lambda里调用你指定的方法。
 * 所以如果你要写的Lambda只是用于调用另外一个方法时，你完全可以用MethodReference来替代的。
 */
public class MethodReferenceTest {

    private static interface MyPrinter {
        public void print(Serializable o);
    }

    private static void printArray(Integer[] arr, MyPrinter printer){
        if(arr==null){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            printer.print(arr[i]);
            printer.print(" ");
        }
        printer.print("\n");
    }

    @Test
    public void test0(){
        // 原始写法
        Integer[] arr1 = new Integer[]{12,23,545,2,345,0, -1};
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t1.compareTo(t2);
            }
        };
        Arrays.sort(arr1, comparator1);
        printArray(arr1, new MyPrinter() {
            @Override
            public void print(Serializable o) {
                System.out.print(o);
            }
        });

        // 使用lambda的写法
        Integer[] arr2 = new Integer[]{12,23,545,2,345,0, -1};
        Arrays.sort(arr2, (Integer x, Integer y)->{return x.compareTo(y);});
        printArray(arr2, (x)->System.out.print(x));

        // 使用MethodReference的写法
        Comparator<Integer> comparator3 = Integer::compareTo;
        Integer[] arr3 = new Integer[]{12,23,545,2,345,0, -1};
        Arrays.sort(arr3, comparator3);
        MyPrinter printer = System.out::print;
        printArray(arr3, printer);

        printer.print("test finish\n");

        Comparator<Integer> comparator4 = (Integer x,Integer y)->{return x.compareTo(y);};

    }
}
