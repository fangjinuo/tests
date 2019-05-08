package com.fjn.tests.java8.defaultmethod;

public interface InterfaceB {
    public default void hello(){
        System.out.println("B");
    };
}
