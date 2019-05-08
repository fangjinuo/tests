package com.fjn.tests.java8;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaTests {
    public static interface Computer{
        public double compute(double a, int b);
    }

    public static void executeCompute(List<Integer> list, Computer computer){
        double result = 1D;
        if(list!=null){
            for (Integer i : list) {
                if(i==0){
                    continue;
                }
                result = computer.compute(result, i);
            }
        }
        System.out.println(result);
    }

    private static List<Integer> nums = Arrays.asList(new Integer[]{12,23,545,2,345,0, -1});
    @Test
    public void test0(){
        Computer getSum = new Computer() {
            @Override
            public double compute(double a, int b) {
                if(b!=0) {
                    return a + b;
                }
                return a;
            }
        };
        executeCompute(nums, getSum);

        Computer getMult = new Computer() {
            @Override
            public double compute(double a, int b) {
                return a * b;
            }
        };
        executeCompute(nums, getMult);

        executeCompute(nums, (a,b)->{ return (b!=0) ? (a+b) : a;});
        executeCompute(nums, (a,b)->{ return a * b;} );
    }

    //-------------------------------------------------------------------------------------

    /**
     * Operate an Object use an Lambda
     */
    static class Person{
        private int age;
        private String name;
        Person(String name, int age){
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return "name: "+name + " age: "+age;
        }

    }


    private static interface InfoGetter {
        public String get(Person p);
        public default String get2(int a, Person p){return "";};
        public default String get3(Person p){return "";};
    }

    static void show(List<Person> persons, InfoGetter infoGetter){
        persons.forEach((person)->{
            System.out.println(infoGetter.get(person));
        });
    }

    static List<Person> persons = new ArrayList<>();
    static{
        for(int i = 0 ; i< 10; i++) {
            persons.add(new Person("hello_"+i, i));
        }
    }

    @Test
    public void test1(){
        LambdaTests ctx = this;

        InfoGetter getter1= (Person person)->{
            System.out.println(ctx == this);
            Method[] methods = this.getClass().getMethods();
            return person.toString();
        };

        InfoGetter getter2= (Person person)->{
            System.out.println(ctx == this);
            return person.toString();
        };

        show(persons, getter1);
        System.out.println(getter1==getter2);

        InfoGetter getter3 = getInfoGetter();
        InfoGetter getter4 = getInfoGetter();
        System.out.println(getter3==getter4);

        InfoGetter getter5 = getInfoGetter("a");
        InfoGetter getter6 = getInfoGetter("b");
        System.out.println(getter3==getter5);
        System.out.println(getter5==getter6);

        InfoGetter getter7 = getInfoGetter("a",1);
        InfoGetter getter8 = getInfoGetter("b",2);
        System.out.println(getter3==getter5);
        System.out.println(getter7==getter8);
    }

    static InfoGetter getInfoGetter(){
        return (p)->"";
    }

    static InfoGetter getInfoGetter(String str){
        System.out.println(str);
        return (p)->{int a = 1; return "" + a;};
    }
    static InfoGetter getInfoGetter(String str, int i){
        System.out.println(str);
        return (p)->str + i;
    }
}
