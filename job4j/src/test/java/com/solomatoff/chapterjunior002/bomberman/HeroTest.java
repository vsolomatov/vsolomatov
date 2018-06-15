package com.solomatoff.chapterjunior002.bomberman;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void whenFailedInitHero() {
        List<String> commandList = new ArrayList<>(Arrays.asList("UP", "UP", "RIGHT", "DOWN", "LEFT", "LEFT", "DOWN", "DOWN"));
        Board board = new Board();
        Thread thread0 = new Thread(() -> {
           //System.out.println("Блокируем поле куда нельзя ходить [5,3]");
            board.positionLock(5, 3);
        });
        Thread thread1 = new Thread(() -> {
            Hero heroBomberMan = new Hero(5, 3, board);
            if (heroBomberMan.initHero()) {
                heroBomberMan.moveBomberMan(commandList);
                heroBomberMan.freeCell();
            }
        });
        thread0.start();
        try {
            thread0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        try {
            thread0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReentrantLock[][] boardContainer = board.getBoardContainer();
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COL; j++) {
                System.out.print("[" + i + "," + j + "]=" + boardContainer[i][j].isLocked() + " ");
            }
            System.out.println("");
        }
    }

    @Test
    public void whenHeroMoveWithObstruction() {
        List<String> commandList = new ArrayList<>(Arrays.asList("UP", "UP", "RIGHT", "DOWN", "LEFT", "LEFT", "DOWN", "DOWN", "LEFT"));
        Board board = new Board();
        Thread thread0 = new Thread(() -> {
           //System.out.println("Блокируем поле куда нельзя ходить [5,3]");
            board.positionLock(5, 3);
        });
        Thread thread1 = new Thread(() -> {
            Hero heroBomberMan = new Hero(5, 4, board);
            if (heroBomberMan.initHero()) {
                heroBomberMan.moveBomberMan(commandList);
                heroBomberMan.freeCell();
            }
            System.out.println("Последняя позиция [" + heroBomberMan.getLine() + "," + heroBomberMan.getColumn() + "]");
        });
        thread0.start();
        try {
            thread0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReentrantLock[][] boardContainer = board.getBoardContainer();
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COL; j++) {
                System.out.print("[" + i + "," + j + "]=" + boardContainer[i][j].isLocked() + " ");
            }
            System.out.println("");
        }
    }

    @Test
    public void whenHeroMoveFree() {
        List<String> commandList = new ArrayList<>(Arrays.asList("UP", "UP", "RIGHT", "DOWN", "LEFT", "LEFT", "DOWN", "DOWN", "LEFT"));
        Board board = new Board();

        Thread thread1 = new Thread(() -> {
            Hero heroBomberMan = new Hero(5, 4, board);
            if (heroBomberMan.initHero()) {
                heroBomberMan.moveBomberMan(commandList);
                heroBomberMan.freeCell();
            }
            System.out.println("Последняя позиция [" + heroBomberMan.getLine() + "," + heroBomberMan.getColumn() + "]");
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReentrantLock[][] boardContainer = board.getBoardContainer();
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COL; j++) {
                System.out.print("[" + i + "," + j + "]=" + boardContainer[i][j].isLocked() + " ");
            }
            System.out.println("");
        }
    }
}