package com.fjn.tests.java8.defaultmethod;

public interface InterfaceA {
    public default void hello(){
        System.out.println("A");
    };
}
