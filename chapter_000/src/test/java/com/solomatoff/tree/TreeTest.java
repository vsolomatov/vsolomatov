package com.solomatoff.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIterator() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 5);
        tree.add(1, 6);
        tree.add(1, 7);
        tree.add(5, 2);
        tree.add(6, 3);
        tree.add(7, 4);
        tree.add(7, 8);

        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));

        Integer result = it.next();
        assertThat(result, is(1));

        result = it.next();
        assertThat(result, is(5));

        result = it.next();
        assertThat(result, is(6));

        result = it.next();
        assertThat(result, is(7));

        result = it.next();
        assertThat(result, is(2));

        result = it.next();
        assertThat(result, is(3));

        result = it.next();
        assertThat(result, is(4));

        result = it.next();
        assertThat(result, is(8));

        assertThat(it.hasNext(), is(false));
    }
}