package com.solomatoff.chapterjunior001.list;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class NodeCycleTest {

    @Test
    public void whenCycleExistsLastToFirst() {
        Node<String> firstString = new Node<>("Какая-то строка");
        Node<String> secondString = new Node<>("Какая-то строка");
        Node<String> thirdString = new Node<>("Какая-то строка");
        Node<String> fourString = new Node<>("Какая-то строка");

        firstString.next = secondString;
        secondString.next = thirdString;
        thirdString.next = fourString;
        fourString.next = firstString;

        NodeCycle<String> nodeCycle = new NodeCycle<>();

        boolean result = nodeCycle.hasCycle(firstString);
        assertThat(result, is(true));
    }

    @Test
    public void whenCycleExistsInternal() {
        Node<Integer> firstNumber = new Node<>(10);
        Node<Integer> secondNumber = new Node<>(105);
        Node<Integer> thirdNumber = new Node<>(3);
        Node<Integer> fourNumber = new Node<>(15);

        firstNumber.next = secondNumber;
        secondNumber.next = thirdNumber;
        thirdNumber.next = fourNumber;
        fourNumber.next = secondNumber;

        NodeCycle<Integer> nodeCycle = new NodeCycle<>();

        boolean result = nodeCycle.hasCycle(firstNumber);
        assertThat(result, is(true));
    }

    @Test
    public void whenOneElement() {
        Node<String> firstString = new Node<>("Какая-то строка");

        firstString.next = null;

        NodeCycle<String> nodeCycle = new NodeCycle<>();

        boolean result = nodeCycle.hasCycle(firstString);
        assertThat(result, is(false));
    }

    @Test
    public void whenNoElement() {
        Node<String> firstString = null;

        NodeCycle<String> nodeCycle = new NodeCycle<>();

        boolean result = nodeCycle.hasCycle(firstString);
        assertThat(result, is(false));
    }

    @Test
    public void whenCycleNotExists() {
        Node<String> firstString = new Node<>("Какая-то строка");
        Node<String> secondString = new Node<>("Какая-то строка");
        Node<String> thirdString = new Node<>("Какая-то строка");
        Node<String> fourString = new Node<>("Какая-то строка");

        firstString.next = secondString;
        secondString.next = thirdString;
        thirdString.next = fourString;
        //fourString.next = firstString;

        NodeCycle<String> nodeCycle = new NodeCycle<>();

        boolean result = nodeCycle.hasCycle(firstString);
        assertThat(result, is(false));
    }
}