package com.fjn.tests.antlr4;

import org.antlr.v4.gui.TestRig;

public class Antlr4TestRigRunner {
    public static void main(String[] args) throws Exception {
        TestRig.main(new String[]{"ArrayInit", "value", "-tokens","D:\\projects\\workspace_test_idea\\tests\\tests-antlr\\src\\main\\resources\\ArrayInit.g4"});
    }
}
