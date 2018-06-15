package com.solomatoff.chaptertrainee002.chessboard;

import java.util.ArrayList;

public class Board {
    public ArrayList<Figure> figures = new ArrayList<>();

    private void add(Figure figure) {
        this.figures.add(figure);
    }

    /**
     *  Метод возвращает фигуру из заданной ячейки шахматной доски
     * */
    private Figure returnFigureFromCell(Cell cell) throws FigureNotFoundException {
        Figure ourfigure = null;

        for (Figure figure : this.figures) {
            if ((cell.x == figure.position.x) && (cell.y == figure.position.y)) {
                ourfigure = figure;
                break;
            }
        }
        //System.out.println("Ссылка на фигуру в ячейке - " + ourfigure);
        if (ourfigure == null) {
            System.out.println("Ссылка: " + ourfigure);
            throw new FigureNotFoundException("В заданной ячейке фигура отсутствует");
        }
        return ourfigure;
    }

    /**
     * Метод проверяет свободен ли путь для фигуры для движения из ячейки sourse в ячейку dest
     * Этот метод общий для всех фигур, кроме коня
     */
    private boolean checkFigureWay(ArrayList<Cell> wayfigure) throws OccupiedWayException {
        boolean free = true;
        for (Cell cell : wayfigure) {
            try {
                returnFigureFromCell(cell);
                free = false;
            } catch (FigureNotFoundException fnfe) {
                // Это означает что фигуры в ячейке нет - ячейка свободна
                System.out.println("Ячейка: " + Integer.toString(cell.x) + "," + Integer.toString(cell.y) + " свободна.");
                fnfe.getStackTrace();
            }
        }
        return free;
    }

    /**
     * Метод должен проверить
     *  - Что в заданной ячейке есть фигура. Если нет, то выкинуть исключение
     *  - Если фигура есть. Проверить может ли она так двигаться. Если нет, то выбросить исключение
     *  - Проверить что полученный путь не занят фигурами. Если занят, выкинуть исключение
     *  - Если все отлично, записать в ячейку новое новое положение
     */
    public boolean move(Cell sourse, Cell dest) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        Figure ourfigure, newfigure;
        boolean possible;
        ArrayList<Cell> cells;
       //System.out.println("Пытаемся передвинуть фигуру из " + sourse.x + ":" + sourse.y + " в " + dest.x + ":" + dest.y);

        try {
            //System.out.println("Проверяем что в заданной ячейке есть фигура");
            ourfigure = returnFigureFromCell(sourse);
        } catch (FigureNotFoundException fnfe) {
            throw new FigureNotFoundException("В заданной ячейке фигура отсутствует");
        }
        // Проверяем может ли фигура так двигаться
        //System.out.println("Проверяем может ли фигура так двигаться");
        if (!ourfigure.checkFigureMove(sourse, dest)) {
            throw new ImpossibleMoveException("Данная фигура не может так ходить");
        }
        // Формируем список ячеек по пути движения фигуры
        //System.out.println("Формируем список ячеек по пути движения фигуры");
        cells = ourfigure.way(sourse, dest);

        // Проверяем что полученный путь не занят фигурами
        //System.out.println("Проверяем что полученный путь не занят фигурами");
        possible = checkFigureWay(cells);
        if (!possible) {
            throw new OccupiedWayException("Путь для прохождения фигуры занят!");
        }
        // Создаем копию в новой ячейке
        newfigure = ourfigure.copy(sourse, dest);
        this.figures.add(newfigure);

       //System.out.println("");
        return possible;
    }

    /**
     *  Метод заполняет список данного класса figures конкретными объектами
     * */
    public void fillFigures() {
        Cell newCell;
        Bishop newFigure;
        // Создадим пока только фигуры типа СЛОН, которые у нас реализованы в классе Bishop
        newCell = new Cell(2, 0);
        newFigure = new Bishop(newCell);
        this.add(newFigure);

        newCell = new Cell(2, 7);
        newFigure = new Bishop(newCell);
        this.add(newFigure);

        newCell = new Cell(5, 0);
        newFigure = new Bishop(newCell);
        this.add(newFigure);

        newCell = new Cell(5, 7);
        newFigure = new Bishop(newCell);
        this.add(newFigure);
    }
    /**
     *  Метод печатает координаты списка данного класса figures
     * */
    public void printcoordinates() {
        System.out.println("Список занятых ячеек:");
        for (Figure figure : this.figures) {
           System.out.println(figure.position.x + ":" + figure.position.y);
        }
    }
}
