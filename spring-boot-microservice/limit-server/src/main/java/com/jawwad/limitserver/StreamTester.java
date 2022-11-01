package com.jawwad.limitserver;

import java.util.*;

@FunctionalInterface
interface myLambda {
    public int display(int a, int s);
}

//class LambdaDemo implements myLambda {
//    @Override
//    public void display() {
//        System.out.println("my lambday");
//    }
//}

public class StreamTester {
    public static void main(String[] args) {
        myLambda result = (a, b) -> a + b;
        System.out.println(result.display(10,20));
    }
}
