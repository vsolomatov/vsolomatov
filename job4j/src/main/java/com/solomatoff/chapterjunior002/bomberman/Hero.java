package com.solomatoff.chapterjunior002.bomberman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hero {
    private int line;
    private int column;
    private Board board;

    Hero(int line, int column, Board board) {
        this.line = line;
        this.column = column;
        this.board = board;
    }

    boolean initHero() {
        return board.positionLock(this.line, this.column);
    }

    void freeCell() {
        board.positionUnLock(this.line, this.column);
    }

    private boolean moveOneStep(String direction) {
        boolean result = false;
        int newLine = 0;
        int newColumn = 0;
        switch (direction) {
            case "UP":
                newLine = this.line - 1;
                newColumn = this.column;
                if (newLine >= 0) {
                    result = this.board.positionLock(newLine, newColumn);
                }
                System.out.println("[" + Thread.currentThread().getName() + "] go UP into [" + newLine + "," + newColumn + "] = " + result);
                break;
            case "DOWN":
                newLine = this.line + 1;
                newColumn = this.column;
                if (newLine < Board.ROW) {
                    result = this.board.positionLock(newLine, newColumn);
                }
                System.out.println("[" + Thread.currentThread().getName() + "] go DOWN into [" + newLine + "," + newColumn + "] = " + result);
                break;
            case "LEFT":
                newLine = this.line;
                newColumn = this.column - 1;
                if (newColumn >= 0) {
                    result = this.board.positionLock(newLine, newColumn);
                }
                System.out.println("[" + Thread.currentThread().getName() + "] go LEFT into [" + newLine + "," + newColumn + "] = " + result);
                break;
            case "RIGHT":
                newLine = this.line;
                newColumn = this.column + 1;
                if (newColumn < Board.COL) {
                    result = this.board.positionLock(newLine, newColumn);
                }
                System.out.println("[" + Thread.currentThread().getName() + "] go RIGHT into [" + newLine + "," + newColumn + "] = " + result);
                break;
             default:
                 System.out.println("[" + Thread.currentThread().getName() + "]. Неизвестная команда " + direction);
        }
        if (result) {
            this.board.positionUnLock(this.line, this.column);
            this.line = newLine;
            this.column = newColumn;

        }
        return result;
    }

    void moveBomberMan(List<String> commandList) {
        boolean result;
        for (String command : commandList) {
           if (!moveOneStep(command)) {
               System.out.println("     Команда " + command + " не может быть выполнена!");
           }
        }
    }

    int getLine() {
        return this.line;
    }

    int getColumn() {
        return this.column;
    }
}
