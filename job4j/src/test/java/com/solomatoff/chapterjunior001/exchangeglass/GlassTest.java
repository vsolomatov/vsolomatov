package com.solomatoff.chapterjunior001.exchangeglass;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class GlassTest {

    @Test
    public void whenAdd() {
        Glass glass = new Glass("Gazprom");

        Order order = new Order(1, "bid", 21, 15);
        glass.add(order);

        order = new Order(2, "bid", 9, 10);
        glass.add(order);

        order = new Order(3, "ask", 8, 11);
        glass.add(order);

        order = new Order(4, "ask", 11, 10);
        glass.add(order);

        order = new Order(4, "ask", 7, 10);
        boolean resultAdd = glass.add(order);
        assertThat(resultAdd, is(true));

        order = new Order(5, "bid", 9, 10);
        resultAdd = glass.add(order);
        assertThat(resultAdd, is(true));

        order = new Order(5, "bid", 9, 19);
        resultAdd = glass.add(order);
        assertThat(resultAdd, is(false));

        Iterator<Order> it = glass.iterator();

        int result = it.next().getIdRequest();
        assertThat(result, is(4));

        result = it.next().getIdRequest();
        assertThat(result, is(3));

        result = it.next().getIdRequest();
        assertThat(result, is(4));

        result = it.next().getIdRequest();
        assertThat(result, is(1));

        result = it.next().getIdRequest();
        assertThat(result, is(2));

        result = it.next().getIdRequest();
        assertThat(result, is(5));

        /*for (Order ord : glass) {
           //System.out.println(ord);
        }*/
    }

    @Test
    public void whenDelete() {
        Glass glass = new Glass("Gazprom");

        Order order = new Order(1, "bid", 21, 15);
        glass.add(order);

        order = new Order(2, "bid", 9, 10);
        glass.add(order);

        order = new Order(3, "ask", 8, 11);
        glass.add(order);

        order = new Order(4, "ask", 11, 10);
        glass.add(order);

        order = new Order(2, "bid", 9, 10);
        boolean resultDelete = glass.delete(order);
        assertThat(resultDelete, is(true));

        order = new Order(4, "ask", 11, 10);
        resultDelete = glass.delete(order);
        assertThat(resultDelete, is(true));

        order = new Order(1, "ask", 21, 15);
        resultDelete = glass.delete(order);
        assertThat(resultDelete, is(false));

        order = new Order(3, "ask", 8, 15);
        resultDelete = glass.delete(order);
        assertThat(resultDelete, is(true));

        Iterator<Order> it = glass.iterator();

        int result = it.next().getIdRequest();
        assertThat(result, is(1));

        boolean hasNext = it.hasNext();
        assertThat(hasNext, is(false));
        /*for (Order ord : glass) {
           //System.out.println(ord);
        }*/
    }

    @Test
    public void whenCombineTwoOrder() {
        Glass glass = new Glass("Gazprom");

        Order order1 = new Order(2, "bid", 9, 10);
        Order order2 = new Order(3, "ask", 8, 11);
        Order result = glass.combineTwoOrder(order1, order2);
        assertThat(result, is(nullValue()));

        order1 = new Order(2, "bid", 9, 15);
        order2 = new Order(3, "ask", 8, 11);
        result = glass.combineTwoOrder(order1, order2);
        assertThat(result, is(nullValue()));


        order1 = new Order(2, "bid", 8, 10);
        order2 = new Order(3, "ask", 9, 11);
        result = glass.combineTwoOrder(order1, order2);
        Order orderResult = new Order(3, "ask", 9, 1);
        //System.out.println(orderResult);
        assertThat(result, is(orderResult));

        order1 = new Order(2, "bid", 8, 11);
        order2 = new Order(3, "ask", 9, 10);
        result = glass.combineTwoOrder(order1, order2);
        orderResult = new Order(2, "bid", 8, 1);
        //System.out.println(orderResult);
        assertThat(result, is(orderResult));

    }

    @Test
    public void whenAnalysisGlass() {
        Glass glass = new Glass("Gazprom");

        Order order;
        order = new Order(1, "bid", 21, 15);
        glass.add(order);
        order = new Order(2, "bid", 8, 10);
        glass.add(order);
        order = new Order(3, "ask", 8, 11);
        glass.add(order);
        order = new Order(4, "ask", 11, 27);
        glass.add(order);
        order = new Order(5, "bid", 9, 10);
        glass.add(order);
        order = new Order(6, "ask", 21, 10);
        glass.add(order);
        order = new Order(7, "ask", 9, 11);
        glass.add(order);

        glass.analysisGlass();

        Iterator<Order> it = glass.iterator();

        int result = it.next().getIdRequest();
        assertThat(result, is(4));

        result = it.next().getIdRequest();
        assertThat(result, is(7));

        result = it.next().getIdRequest();
        assertThat(result, is(3));

        result = it.next().getIdRequest();
        assertThat(result, is(1));

        boolean hasNext = it.hasNext();
        assertThat(hasNext, is(false));
    }

    /*@Test
    public void whenPrintGlass() {
        Glass glass = new Glass("Gazprom");

        Order order;
        order = new Order(1, "bid", 21, 15);
        glass.add(order);
        order = new Order(4, "bid", 11, 27);
        glass.add(order);
        order = new Order(7, "bid", 9, 11);
        glass.add(order);
        order = new Order(3, "bid", 8, 44);
        glass.add(order);

        order = new Order(2, "ask", 18, 13);
        glass.add(order);
        order = new Order(5, "ask", 10, 7);
        glass.add(order);
        order = new Order(6, "ask", 9, 2);
        glass.add(order);
        order = new Order(8, "ask", 21, 12);
        glass.add(order);

        String ln = System.lineSeparator();

        String result = glass.printGlass();

        StringBuilder report = new StringBuilder();
        report.append("Эмитент: Gazprom").append(ln);
        report.append("Покупка |   Цена  | Продажа").append(ln);
        report.append("     12 | 21,0000 |      15").append(ln);
        report.append("     13 | 18,0000 |       0").append(ln);
        report.append("      0 | 11,0000 |      27").append(ln);
        report.append("      7 | 10,0000 |       0").append(ln);
        report.append("      2 | 9,00000 |      11").append(ln);
        report.append("      0 | 8,00000 |      44").append(ln);
        String expected = report.toString();

        assertThat(result, is(expected));
    }*/
}