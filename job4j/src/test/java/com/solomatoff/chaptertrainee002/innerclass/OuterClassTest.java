package com.solomatoff.chaptertrainee002.innerclass;

import org.junit.Test;

public class OuterClassTest {
    @Test
    public void whenOuterTestTest() {
        OuterClass.StaticClass newVar = new OuterClass.StaticClass();
        // Вот так можно
        newVar.print();
        // Но лучше вот так
        OuterClass.StaticClass.print();

        OuterClass newVar1 = new OuterClass();
        OuterClass.NotStaticClass newVar2 = newVar1.new NotStaticClass();
        newVar2.print();
    }
}
