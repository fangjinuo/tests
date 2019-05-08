package com.fjn.tests.java8.defaultmethod;

public class ImplementClassA implements InterfaceA {
    @Override
    public void hello() {
        InterfaceA.super.hello();
    }
}
