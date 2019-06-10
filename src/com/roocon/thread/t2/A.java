package com.roocon.thread.t2;

public class A {

    private B b;


    public static void main(String[] args) {
        A aaaaa = new A() {
            public void B() {
                System.out.println("aaaaa");
            }
        };
        System.out.println(aaaaa);
    }
}
