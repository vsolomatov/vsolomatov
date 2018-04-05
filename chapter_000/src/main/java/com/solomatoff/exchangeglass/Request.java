package com.solomatoff.exchangeglass;

/**
 * Класс реализует структуру заявок, поступающих на биржу
 * @author Vyacheslav Solomatov (solomatoff.vaycheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Request {
    int id;
    String book;
    String type;
    String action;
    double price;
    int numberOfShares;

    Request(int id, String book, String type, String action, double price, int numberOfShares) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.numberOfShares = numberOfShares;
    }
}
