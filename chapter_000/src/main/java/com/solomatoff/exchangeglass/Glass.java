package com.solomatoff.exchangeglass;

import org.jetbrains.annotations.NotNull;
import java.util.*;

/**
 * Класс реализует структуру биржевого стакана
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Glass implements Iterable {
    private Set<Order> glassAsk = new TreeSet<>();
    private Set<Order> glassBid = new TreeSet<>();
    private String bookGlass;

    Glass(String bookGlass) {
        this.bookGlass = bookGlass;
    }

    /**
     * Метод добавляет запись по заявке в стакан
     * @param order запись
     * @return true - в случае успеха, false - иначе
     */
    boolean add(Order order) {
        return order.getAction().equals("ask") ? glassAsk.add(order) : glassBid.add(order);
    }

    /**
     * Метод удаляет запись по заявке из стакана
     * @param order запись
     * @return true - в случае успеха, false - иначе
     */
    boolean delete(Order order) {
        return order.getAction().equals("ask") ? glassAsk.remove(order) : glassBid.remove(order);
    }

    /**
     *  Метод совмещает два объекта типа Order.
     * @param order1 первый объект типа Order.
     * @param order2 второй объект типа Order.
     * @return новый совмещенный  объект типа Order
     */
    Order combineTwoOrder(Order order1, Order order2) {
        Order combineOrder = null;
        if (order1.analysisTwoOrder(order2)) {
                if (order1.volume >= order2.volume) {
                    combineOrder = new Order(order1.getIdRequest(), order1.getAction(), order1.getPrice(), order1.volume - order2.volume);
                } else {
                    combineOrder = new Order(order2.getIdRequest(), order2.getAction(), order2.getPrice(), order2.volume - order1.volume);
                }
        }
        return combineOrder;
    }

    /**
     * Метод анализирует содержимое биржевого стакана,
     * т.е. проверяет существование противоположных по направленности и совместимых по цене записей в стакане
     * (с целью их совместить, если таковые найдутся)
     */
    void analysisGlass() {
        Order currItAsk;
        Order currItBid;
        Order combineOrder;
        Iterator<Order> itAsk = glassAsk.iterator();
        Iterator<Order> itBid;
        while (itAsk.hasNext()) {
            currItAsk = itAsk.next();
            itBid = glassBid.iterator();
            while (itBid.hasNext()) {
                currItBid = itBid.next();
                combineOrder = combineTwoOrder(currItAsk, currItBid);
                if (combineOrder != null) {
                    itBid.remove();
                    itAsk.remove();
                    if (combineOrder.volume > 0) {
                        if (combineOrder.getAction().equals("ask")) {
                            glassAsk.add(combineOrder);
                            itAsk = glassAsk.iterator();
                        } else {
                            glassBid.add(combineOrder);
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Метод возвращает строку для печати стакана
     */
    String printGlass() {
        StringBuilder report = new StringBuilder();
        String ln = System.lineSeparator();
        Integer sumVolumeAsk;
        Integer sumVolumeBid;
        Iterator<Order> itAsk = glassAsk.iterator();
        Order lastRecItAsk = null;
        Iterator<Order> itBid = glassBid.iterator();
        Order lastRecItBid = null;
        Double askPrice;
        Double bidPrice;
        Double maxPrice;
        boolean firstLoop = true;
        report.append("Эмитент: ").append(this.bookGlass).append(ln);
        report.append("Покупка |   Цена  | Продажа").append(ln);
        while (itAsk.hasNext() || itBid.hasNext() || lastRecItAsk != null || lastRecItBid != null) {
            if (firstLoop) {
                lastRecItAsk = itAsk.hasNext() ? itAsk.next() : null;
                lastRecItBid = itBid.hasNext() ? itBid.next() : null;
                firstLoop = false;
            }
            askPrice = (lastRecItAsk != null) ? lastRecItAsk.getPrice() : 0;
            bidPrice = (lastRecItBid != null) ? lastRecItBid.getPrice() : 0;
            maxPrice = Math.max(askPrice, bidPrice);
            sumVolumeAsk = 0;
            sumVolumeBid = 0;
            while ((lastRecItAsk != null) && lastRecItAsk.getPrice().equals(maxPrice)) {
                sumVolumeAsk += lastRecItAsk.volume;
                if (itAsk.hasNext()) {
                    lastRecItAsk = itAsk.next();
                } else {
                    lastRecItAsk = null;
                }
            }
            while ((lastRecItBid != null) && lastRecItBid.getPrice().equals(maxPrice)) {
                sumVolumeBid += lastRecItBid.volume;
                if (itBid.hasNext()) {
                    lastRecItBid = itBid.next();
                } else {
                    lastRecItBid = null;
                }
            }
            report.append(String.format("%7d | %g | %7d", sumVolumeAsk, maxPrice, sumVolumeBid)).append(ln);
        }
        //System.out.println(report);
        return report.toString();
    }

    @NotNull
    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            Iterator<Order> itAsk = glassAsk.iterator();
            Iterator<Order> itBid = glassBid.iterator();

            @Override
            public boolean hasNext() {
                return itAsk.hasNext() || itBid.hasNext();
            }

            @Override
            public Order next() throws NoSuchElementException {
                Order result;
                if (itAsk.hasNext()) {
                    result = itAsk.next();
                } else {
                    if (itBid.hasNext()) {
                        result = itBid.next();
                    } else {
                        throw new NoSuchElementException();
                    }
                }
                return result;
            }
        };
    }
}
