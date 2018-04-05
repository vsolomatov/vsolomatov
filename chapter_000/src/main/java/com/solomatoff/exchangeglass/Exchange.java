package com.solomatoff.exchangeglass;

import java.util.TreeMap;

public class Exchange {
    private TreeMap<String, Glass> glasses = new TreeMap<>();

    /**
     * Метод выполняет действие по добавлению или удалению данных по заявке
     * @param request заявка на добавление или удаление биржевых данных (в стакан или из стакана)
     * @return true - если выполнено, и false - в противном случае
     */
    boolean executeNewRequest(Request request) {
        return (request.type.equals("add")) ? addOrder(request) : deleteOrder(request);
    }

    /**
     * Метод выполняет действие по добавлению данных по заявке
     * @param request заявка на добавление биржевых данных (в стакан)
     * @return true - если выполнено, и false - в противном случае
     */
    private boolean addOrder(Request request) {
        boolean result;
        String bookGlass = request.book;
        Glass glass = glasses.get(bookGlass);
        if (glass == null) {
            glass = new Glass(bookGlass);
            glasses.putIfAbsent(bookGlass, glass);
        }
        Order order = new Order(request.id, request.action, request.price, request.numberOfShares);
        result = glass.add(order);
        if (result) {
            glass.analysisGlass();
        }
        return result;
    }

    /**
     * Метод выполняет действие по удалению данных по заявке
     * @param request заявка на удаление биржевых данных (из стакана)
     * @return true - если выполнено, и false - в противном случае
     */
    private boolean deleteOrder(Request request) {
        boolean result;
        String bookGlass = request.book;
        Glass glass = glasses.get(bookGlass);
        if (glass == null) {
            result = false;
        } else {
            Order order = new Order(request.id, request.action, request.price, request.numberOfShares);
            result = glass.delete(order);
        }
        return result;
    }

    /**
     * Метод возвращает стакан по имени стакана
     * @param glassBook наименование эмитента ценных бумаг (ключ стакана)
     * @return стакан - если существует стакан с таким названием (ключом), и null - в противном случае
     */
    Glass getGlassByBook(String glassBook) {
        return glasses.get(glassBook);
    }

    /**
     * Метод возвращает строку для печати всех стаканов
     */
    String printGlasses() {
        StringBuilder report = new StringBuilder();
        for (Glass glass : glasses.values()) {
            report.append(glass.printGlass());
        }
        return report.toString();
    }
}
