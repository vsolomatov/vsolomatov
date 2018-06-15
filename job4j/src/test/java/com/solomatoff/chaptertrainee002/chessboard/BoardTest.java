package com.solomatoff.chaptertrainee002.chessboard;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenMoveFigureSuccessfully() {
        Board newGame = new Board();
        newGame.fillFigures();
        // Создадим новые две ячейки
        Cell oneCell = new Cell(2, 0);
        Cell twoCell = new Cell(7, 5);
        try {
            // пытаемся передвинуть фигуру и проверяем результат
            assertThat(newGame.move(oneCell, twoCell), is(true));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }

        // Создадим новые две ячейки
        oneCell = new Cell(2, 7);
        twoCell = new Cell(7, 2);
        try {
            // пытаемся передвинуть фигуру и проверяем результат
            assertThat(newGame.move(oneCell, twoCell), is(true));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }

        // Создадим новые две ячейки
        oneCell = new Cell(5, 0);
        twoCell = new Cell(0, 5);
        try {
            // пытаемся передвинуть фигуру и проверяем результат
            assertThat(newGame.move(oneCell, twoCell), is(true));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }

        // Создадим новые две ячейки
        oneCell = new Cell(5, 7);
        twoCell = new Cell(0, 2);
        try {
            // пытаемся передвинуть фигуру и проверяем результат
            assertThat(newGame.move(oneCell, twoCell), is(true));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }

        //newGame.printcoordinates();
    }

    @Test
    public void whenFigureNotFoundException() {
        Board newGame = new Board();
        newGame.fillFigures();
        // Создадим новые две ячейки
        Cell oneCell = new Cell(4, 4);
        Cell twoCell = new Cell(7, 7);
        try {
            // пытаемся передвинуть фигуру и проверяем результат
            assertThat(newGame.move(oneCell, twoCell), is(false));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }

        //newGame.printcoordinates();
    }

    @Test
    public void whenImpossibleMoveException() {
        Board newGame = new Board();
        newGame.fillFigures();
        // Создадим новые две ячейки
        Cell oneCell = new Cell(2, 7);
        Cell twoCell = new Cell(7, 1);
        try {
            assertThat(newGame.move(oneCell, twoCell), is(false));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }
        //newGame.printcoordinates();
    }

    @Test
    public void whenOccupiedWayException() {
        Board newGame = new Board();
        newGame.fillFigures();
        // Создадим новые две ячейки
        Cell oneCell = new Cell(2, 7);
        Cell twoCell = new Cell(7, 2);
        try {
            assertThat(newGame.move(oneCell, twoCell), is(true));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }
        // Создадим новые две ячейки
        oneCell = new Cell(5, 0);
        twoCell = new Cell(7, 2);
        try {
            assertThat(newGame.move(oneCell, twoCell), is(false));
        } catch (FigureNotFoundException fnfe) {
           //System.out.println("Не найдена фигура");
        } catch (ImpossibleMoveException ime) {
           //System.out.println("Невозможно сходить этой фигурой");
        } catch (OccupiedWayException owe) {
           //System.out.println("Невозможно сходить этой фигурой, путь занят");
        }
        //newGame.printcoordinates();
    }
}