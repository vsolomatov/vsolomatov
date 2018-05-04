package com.solomatoff.chaptertrainee002.chessboard;

import java.util.ArrayList;

public class Bishop extends Figure {
    /**
     *  Конструктор фигур типа СЛОН вызывает конструктор родительского класса Figure
     */
    public Bishop(Cell cell) {
        super(cell);
    }

    /**
     * Метод проверяет может ли фигура СЛОН двигаться из ячейки sourse в ячейку dest
     */
    public boolean checkFigureMove(Cell sourse, Cell dest) {
        boolean possible = false;
        if (Math.abs(sourse.x - dest.x) == Math.abs(sourse.y - dest.y)) {
            possible = true;
        }
        return possible;
    }

    /**
     *  Метод возвращает список ячеек шахматной доски, которые должна пройти фигура
     */
    public ArrayList<Cell> way(Cell sourse, Cell dest) {
        ArrayList<Cell> cells = new ArrayList<>();
        Cell newCell;
        int i;

        if (sourse.x < dest.x) {
            // Координата X у начальной точки меньше, чем у конечной
            // Координата Y у начальной точки меньше, чем у конечной
            if (sourse.y < dest.y) {
                i = sourse.x + 1;
                for (int j = sourse.y + 1; j <= dest.y; j++) {
                    newCell = new Cell(i, j);
                    cells.add(newCell);
                    i++;
                }
            } else {
                // Координата X у начальной точки меньше, чем у конечной
                // Координата Y у начальной точки БОЛЬШЕ, чем у конечной
                i = sourse.x + 1;
                for (int j = sourse.y - 1; j >= dest.y; j--) {
                    newCell = new Cell(i, j);
                    cells.add(newCell);
                    i++;
                }
            }
        } else {
            // Координата X у начальной точки БОЛЬШЕ, чем у конечной
            // Координата Y у начальной точки меньше, чем у конечной
            if (sourse.y < dest.y) {
                i = sourse.x - 1;
                for (int j = sourse.y + 1; j <= dest.y; j++) {
                    newCell = new Cell(i, j);
                    cells.add(newCell);
                    i--;
                }
            } else {
                // Координата X у начальной точки БОЛЬШЕ, чем у конечной
                // Координата Y у начальной точки БОЛЬШЕ, чем у конечной
                i = sourse.x - 1;
                for (int j = sourse.y - 1; j >= dest.y; j--) {
                    newCell = new Cell(i, j);
                    cells.add(newCell);
                    i--;
                }
            }
        }
        return cells;
    }

    /**
     *  Метод создает фигуру данного типа с новой позицией на шахматной доске
     */
    public Figure copy(Cell sourse, Cell dest) {
        return new Bishop(dest);
    }

}
