package com.solomatoff.exchangeglass;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ExchangeTest {
    @Test
    public void whenExecuteNewRequestAdd() {
        Exchange exchange = new Exchange();
        Request request = new Request(1, "Gazprom", "add", "ask", 10, 25);
        boolean result = exchange.executeNewRequest(request);
        assertThat(result, is(true));

        request = new Request(1, "Rosneft", "add", "ask", 10, 25);
        result = exchange.executeNewRequest(request);
        assertThat(result, is(true));

        request = new Request(1, "Rosneft", "add", "ask", 10, 7);
        result = exchange.executeNewRequest(request);
        assertThat(result, is(false));

        request = new Request(2, "Rosneft", "add", "ask", 10, 25);
        result = exchange.executeNewRequest(request);
        assertThat(result, is(true));
    }

    @Test
    public void whenExecuteNewRequestDeleteTrue() {
        Exchange exchange = new Exchange();
        Request request = new Request(1, "Gazprom", "add", "ask", 10, 25);
        exchange.executeNewRequest(request);

        request = new Request(1, "Gazprom", "delete", "ask", 10, 0);
        boolean result = exchange.executeNewRequest(request);
        assertThat(result, is(true));
    }

    @Test
    public void whenExecuteNewRequestDeleteFalse() {
        Exchange exchange = new Exchange();
        Request request = new Request(1, "Rosneft", "add", "ask", 10, 25);
        exchange.executeNewRequest(request);

        request = new Request(1, "Gazprom", "delete", "ask", 10, 0);

        boolean result = exchange.executeNewRequest(request);
        assertThat(result, is(false));
    }

    @Test
    public void whenAddThenNeedAnalysisGlass() {
        Exchange exchange = new Exchange();
        Request request = new Request(1, "Gazprom", "add", "bid", 21, 15);
        exchange.executeNewRequest(request);

        request = new Request(2, "Gazprom", "add", "bid", 8, 10);
        exchange.executeNewRequest(request);

        request = new Request(3, "Gazprom", "add", "ask", 8, 11);
        exchange.executeNewRequest(request);

        request = new Request(5, "Gazprom", "add", "bid", 9, 10);
        exchange.executeNewRequest(request);

        request = new Request(7, "Gazprom", "add", "ask", 9, 11);
        exchange.executeNewRequest(request);

        request = new Request(4, "Gazprom", "add", "ask", 11, 10);
        exchange.executeNewRequest(request);

        request = new Request(6, "Gazprom", "add", "ask", 21, 10);
        exchange.executeNewRequest(request);

        request = new Request(8, "Rosneft", "add", "ask", 14, 8);
        exchange.executeNewRequest(request);

        Glass glass = exchange.getGlassByBook("Gazprom");
        Iterator<Order> it = glass.iterator();
        Order order = it.next();
        int result = order.getIdRequest();
        assertThat(result, is(4));

        order = it.next();
        result = order.getIdRequest();
        assertThat(result, is(7));

        order = it.next();
        result = order.getIdRequest();
        assertThat(result, is(3));

        order = it.next();
        result = order.getIdRequest();
        assertThat(result, is(1));

        boolean hasNext = it.hasNext();
        assertThat(hasNext, is(false));

        glass = exchange.getGlassByBook("Rosneft");
        it = glass.iterator();
        order = it.next();
        result = order.getIdRequest();
        assertThat(result, is(8));
    }

    @Test
    public void whenPrintGlases() {
        Exchange exchange = new Exchange();
        Request request = new Request(1, "Gazprom", "add", "ask", 10, 25);
        exchange.executeNewRequest(request);

        request = new Request(2, "Gazprom", "add", "ask", 11, 29);
        exchange.executeNewRequest(request);

        request = new Request(3, "Gazprom", "add", "ask", 12, 33);
        exchange.executeNewRequest(request);

        request = new Request(4, "Rosneft", "add", "ask", 13, 11);
        exchange.executeNewRequest(request);

        String ln = System.lineSeparator();
        String result = exchange.printGlasses();

        StringBuilder report = new StringBuilder();
        report.append("Эмитент: Gazprom").append(ln);
        report.append("Покупка |   Цена  | Продажа").append(ln);
        report.append("     33 | 12,0000 |       0").append(ln);
        report.append("     29 | 11,0000 |       0").append(ln);
        report.append("     25 | 10,0000 |       0").append(ln);
        report.append("Эмитент: Rosneft").append(ln);
        report.append("Покупка |   Цена  | Продажа").append(ln);
        report.append("     11 | 13,0000 |       0").append(ln);
        String expected = report.toString();
        assertThat(result, is(expected));
    }
}