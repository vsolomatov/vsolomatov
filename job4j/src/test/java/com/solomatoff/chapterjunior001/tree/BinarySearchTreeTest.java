package com.solomatoff.chapterjunior001.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void iterator() {
        BinarySearchTree<Integer> treeBST = new BinarySearchTree<>();
        treeBST.add(10);
        treeBST.add(6);
        treeBST.add(7);
        treeBST.add(5);
        treeBST.add(6);
        Iterator<Integer> it = treeBST.iterator();

        Integer result =  it.next();
        assertThat(result, is(10));
        boolean hasNext =  it.hasNext();
        assertThat(hasNext, is(true));

        result =  it.next();
        assertThat(result, is(6));
        hasNext =  it.hasNext();
        assertThat(hasNext, is(true));

        result =  it.next();
        assertThat(result, is(5));
        hasNext =  it.hasNext();
        assertThat(hasNext, is(true));

        result =  it.next();
        assertThat(result, is(7));
        hasNext =  it.hasNext();
        assertThat(hasNext, is(true));

        result =  it.next();
        assertThat(result, is(6));
        hasNext =  it.hasNext();
        assertThat(hasNext, is(false));
    }

    @Test
    public void add() {
        BinarySearchTree<Integer> treeBST = new BinarySearchTree<>();
        treeBST.add(8);
        treeBST.add(4);
        treeBST.add(2);
        treeBST.add(3);
        treeBST.add(10);
        treeBST.add(6);
        treeBST.add(7);
        treeBST.add(5);
        treeBST.add(6);
        treeBST.add(8);
        Iterator<Integer> it = treeBST.iterator();

        Integer result =  it.next();
        assertThat(result, is(8));

        result =  it.next();
        assertThat(result, is(4));

        result =  it.next();
        assertThat(result, is(10));

        result =  it.next();
        assertThat(result, is(2));

        result =  it.next();
        assertThat(result, is(6));

        result =  it.next();
        assertThat(result, is(3));

        result =  it.next();
        assertThat(result, is(5));

        result =  it.next();
        assertThat(result, is(7));

        result =  it.next();
        assertThat(result, is(6));

        result =  it.next();
        assertThat(result, is(8));
        boolean hasNext =  it.hasNext();
        assertThat(hasNext, is(false));
    }
}