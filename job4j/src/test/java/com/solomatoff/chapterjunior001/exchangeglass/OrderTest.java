package com.solomatoff.chapterjunior001.exchangeglass;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void whenGetId() {
        Order order = new Order(3, "bid", 21, 15);
        int result = order.getIdRequest();
        assertThat(result, is(3));

        order = new Order(2, "bid", 17, 9);
        result = order.getIdRequest();
        assertThat(result, is(2));

        order = new Order(1, "bid", 17, 9);
        result = order.getIdRequest();
        assertThat(result, is(1));
    }

    @Test
    public void whenCompareTo() {
        Order order1 = new Order(1, "bid", 21, 15);
        Order order2 = new Order(1, "bid", 21, 10);
        Order order3 = new Order(1, "bid", 17, 15);
        Order order4 = new Order(2, "bid", 17, 15);

        int result = order1.compareTo(order2);
        assertThat(result, is(0));

        result = order2.compareTo(order3);
        assertThat(result, is(-1));

        result = order4.compareTo(order3);
        assertThat(result, is(1));

        result = order2.compareTo(order4);
        assertThat(result, is(-1));

        result = order3.compareTo(order1);
        assertThat(result, is(1));
    }

    @Test
    public void whenAnalysisTwoOrder() {
        Order order1 = new Order(1, "bid", 21, 15);
        Order order2 = new Order(1, "ask", 18, 10);
        Order order3 = new Order(1, "bid", 17, 15);
        Order order4 = new Order(2, "ask", 17, 9);

        boolean result = order1.analysisTwoOrder(order2);
        assertThat(result, is(false));

        result = order2.analysisTwoOrder(order1);
        assertThat(result, is(false));

        result = order1.analysisTwoOrder(order3);
        assertThat(result, is(false));

        result = order3.analysisTwoOrder(order1);
        assertThat(result, is(false));

        result = order1.analysisTwoOrder(order4);
        assertThat(result, is(false));

        result = order4.analysisTwoOrder(order1);
        assertThat(result, is(false));

        result = order2.analysisTwoOrder(order3);
        assertThat(result, is(true));

        result = order3.analysisTwoOrder(order2);
        assertThat(result, is(true));

        result = order2.analysisTwoOrder(order4);
        assertThat(result, is(false));

        result = order4.analysisTwoOrder(order2);
        assertThat(result, is(false));

        result = order3.analysisTwoOrder(order4);
        assertThat(result, is(true));

        result = order4.analysisTwoOrder(order3);
        assertThat(result, is(true));
    }

    @Test
    public void whenToString() {
        Order order = new Order(3, "bid", 21, 15);
        String result = order.toString();
        assertThat(result, is("<id = 3> <bid> <price = 21.0> 15"));
    }
}