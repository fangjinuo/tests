package com.fjn.tests.java8.defaultmethod;

public class InterfaceDefaultMethodTest {
    public static interface Eatable {
        public default void eat() {
            System.out.println("eating ...");
        }
    }

    class Animal implements Eatable{
        public void run(){
            System.out.println("running ...");
        }
    }

    public static void main(String[] args) {
        InterfaceDefaultMethodTest test = new InterfaceDefaultMethodTest();
        Animal a1 = test.new Animal();
        a1.eat();

        ImplementClassAB ab = new ImplementClassAB();
        ab.hello();
    }
}
