package com.solomatoff.generic;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddShouldException() {
        SimpleArray<String> simpleString = new SimpleArray<>(3);
        simpleString.add("String 0");
        simpleString.add("String 1");
        simpleString.add("String 2");
        simpleString.add("String 3");
        simpleString.add("String 4");
   }

    @Test
    public void whenDeleteShouldValue() {
        SimpleArray<String> simpleString = new SimpleArray<>(4);
        simpleString.add("String 0");
        simpleString.add("String 1");
        simpleString.add("String 2");
        simpleString.add("String 3");

        //System.out.println(simpleString.toString());

        simpleString.delete(1);
        simpleString.delete(1);

        String result = simpleString.get(1);
        assertThat(result, is("String 3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteShouldException() {
        SimpleArray<Integer> simpleInteger = new SimpleArray<>(4);
        simpleInteger.add(10);
        simpleInteger.add(20);
        simpleInteger.add(30);
        simpleInteger.add(40);

        //System.out.println(simpleInteger.toString());

        simpleInteger.delete(1);
        simpleInteger.delete(2);

        Integer result = simpleInteger.get(1);
        assertThat(result, is(30));

        simpleInteger.delete(2);
    }

    @Test
    public void whenSetShouldValue() {
        SimpleArray<String> simpleString = new SimpleArray<>(4);
        simpleString.add("String 0");
        simpleString.add("String 1");
        simpleString.add("String 2");
        simpleString.add("String 3");

        simpleString.set(3, "Строка 3");
        //System.out.println(simpleString.toString());

        String result = simpleString.get(3);
        assertThat(result, is("Строка 3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenSetShouldExseption() {
        SimpleArray<String> simpleString = new SimpleArray<>(4);
        simpleString.add("String 0");
        simpleString.add("String 1");
        simpleString.add("String 2");
        simpleString.add("String 3");

        simpleString.set(4, "Строка 4");
        //System.out.println(simpleString.toString());
    }


    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        SimpleArray<Integer> simpleInteger = new SimpleArray<>(4);
        simpleInteger.add(10);
        simpleInteger.add(20);
        simpleInteger.add(30);
        simpleInteger.add(40);
        Iterator it = simpleInteger.iterator();

        //System.out.println(simpleInteger.toString());

        assertThat(it.next(), is(10));
        assertThat(it.next(), is(20));
        assertThat(it.next(), is(30));
        assertThat(it.next(), is(40));
    }

    @Test
    public void hasNextInvocation() {
        SimpleArray<Integer> simpleInteger = new SimpleArray<>(4);
        simpleInteger.add(10);
        simpleInteger.add(20);
        simpleInteger.add(30);
        simpleInteger.add(40);
        Iterator it = simpleInteger.iterator();

        //System.out.println(simpleInteger.toString());

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(20));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(30));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(40));

        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        SimpleArray<String> simpleString = new SimpleArray<>(4);
        simpleString.add("String 0");
        simpleString.add("String 1");
        simpleString.add("String 2");
        simpleString.add("String 3");
        Iterator it = simpleString.iterator();

        //System.out.println(simpleString.toString());

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("String 0"));
        assertThat(it.next(), is("String 1"));
        assertThat(it.next(), is("String 2"));
        assertThat(it.next(), is("String 3"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void sequentialHasNextInvocationAfterDeleteAffectRetrievalOrder() {
        SimpleArray<String> simpleString = new SimpleArray<>(4);
        simpleString.add("String 0");
        simpleString.add("String 1");
        simpleString.add("String 2");
        simpleString.add("String 3");

        simpleString.delete(1);

        Iterator it = simpleString.iterator();
        //System.out.println(simpleString.toString());

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("String 0"));
        assertThat(it.next(), is("String 2"));
        assertThat(it.next(), is("String 3"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void sequentialIsEmpty() {
        SimpleArray<String> simpleString = new SimpleArray<>(4);

        Iterator it = simpleString.iterator();
        //System.out.println(simpleString.toString());

        assertThat(it.hasNext(), is(false));
    }
}