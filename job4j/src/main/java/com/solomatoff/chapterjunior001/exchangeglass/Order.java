package com.solomatoff.chapterjunior001.exchangeglass;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Класс реализует единичную структуру биржевого стакана (запись), создаваемую на основании поступающих заявок.
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Order implements Comparable<Order> {
    private Integer idRequest;
    private String action;
    private Double price;
    Integer volume;

    Order(int idRequest, String action, double price, int volume) {
        this.idRequest = idRequest;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    public Integer getIdRequest() {
        return this.idRequest;
    }

    public String getAction() {
        return this.action;
    }

    public Double getPrice() {
        return this.price;
    }

    @Override
    public int compareTo(@NotNull Order o) {
        int result;
        if (this.action.compareTo(o.action) == 0) {
            if (this.price.compareTo(o.price) == 0) {
                result = this.idRequest.compareTo(o.idRequest);
            } else {
                result = -this.price.compareTo(o.price);
            }
        } else {
            result = this.action.compareTo(o.action);
        }
        return result;
    }

    /**
     * Метод проверяет возможно ли совместить два объекта типа Order,
     * т.е. проверяет являются ли они противоположными по направленности и совместимыми по цене
     * @param order объект для сравнения
     * @return true, если это возможно и false - в противном случае
     */
    public boolean analysisTwoOrder(Order order) {
        boolean result = false;
        if (!this.action.equals(order.action)) {
            result = this.action.equals("ask") ? this.price >= order.price : this.price <= order.price;
        }
        return result;
    }

    public String toString() {
        return "<id = " + this.idRequest + "> <" + this.action + "> <price = " + this.price + "> " + this.volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(idRequest, order.idRequest) && Objects.equals(action, order.action) && Objects.equals(price, order.price) && Objects.equals(volume, order.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequest, action, price, volume);
    }
}
