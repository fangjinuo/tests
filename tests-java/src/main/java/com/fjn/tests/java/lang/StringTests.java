package com.fjn.tests.java.lang;

public class StringTests {
    public static final String a1="a";
    public static String a2="a";
    public static final String a3 = "ab";
    public static void main(String[] args) {
        String a = "a";
        final String b = "b";
        final String c = a + b;
        String d = a + b;
        String e = a + "b";
        String f = "a" + b;
        String g = "a" + "b";
        String h = "ab";
        String i = new String(h);
        String j = a1+b;
        String k = a2+b;

        System.out.println(c == h); // false
        System.out.println(d == h); // false
        System.out.println(e == h); // false
        System.out.println(f == h); // true
        System.out.println(g == h); // true
        System.out.println(i == h); // false
        System.out.println(j == h); // true
        System.out.println(k == h); // false
        System.out.println(a3 == h); // true

        // 字面量，final 都会在编译期被优化，并且会被直接运算好
        // 所以 f,g,j,h,a3 在编译期就直接变为"ab"
        // 因为a,a2是变量， 所以使用到a,a2的表达式，都是在运行时才去计算的
    }
}
