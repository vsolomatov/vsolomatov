package ru.job4j.chessboard;

import java.util.ArrayList;

/**
 *  Базовый класс для фигур всех типов
 */

public abstract class Figure {
   public final Cell position;

    /**
    *  Конструктор фигур
     */
    public Figure(Cell cell) {
        this.position = cell;
    }

    /**
     * Метод проверяет может ли фигура двигаться из ячейки sourse в ячейку dest
     */
    public abstract boolean checkFigureMove(Cell sourse, Cell dest);

    /**
     *  Метод возвращает список ячеек шахматной доски, которые должна пройти фигура
     */
    public abstract ArrayList<Cell> way(Cell sourse, Cell dest);

    /**
     *  Метод создает копию фигуры с новой позицией на шахматной доске
     */
    public abstract Figure copy(Cell sourse, Cell dest);
}
