package ru.job4j.innerclass;

public class OuterClass {
    static class StaticClass {
        public static void print() {
            System.out.println("Static nested class");
        }
    }

    class NotStaticClass {
        void print() {
            System.out.println("NotStatic class");
        }
    }
}
