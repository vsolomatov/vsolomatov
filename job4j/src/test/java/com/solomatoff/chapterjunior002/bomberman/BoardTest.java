package com.solomatoff.chapterjunior002.bomberman;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void printStatusAllBoardLock() {
        Board board = new Board();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.positionLock(2, 2);
                board.positionLock(4, 4);
                board.positionLock(5, 5);
                board.positionLock(1, 1);
                board.positionLock(3, 3);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.positionLock(1, 1);
                board.positionLock(2, 2);
                board.positionLock(3, 3);
                board.positionLock(4, 4);
                board.positionLock(5, 5);
            }
        });
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReentrantLock[][] boardContainer = board.getBoardContainer();
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COL; j++) {
               //System.out.print("[" + i + "," + j + "]=" + boardContainer[i][j].isLocked() + " ");
            }
           //System.out.println("");
        }
    }

    @Test
    public void printStatusAllBoardUnLock() {
        Board board = new Board();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.positionLock(2, 2);
                board.positionLock(4, 4);
                board.positionLock(5, 5);
                board.positionLock(1, 1);
                board.positionLock(3, 3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.positionUnLock(5, 5);
                board.positionUnLock(4, 4);
                board.positionUnLock(1, 1);
                board.positionUnLock(2, 2);
                board.positionUnLock(3, 3);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.positionLock(1, 1);
                board.positionLock(2, 2);
                board.positionLock(3, 3);
                board.positionLock(4, 4);
                board.positionLock(5, 5);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.positionUnLock(5, 5);
                board.positionUnLock(4, 4);
                board.positionUnLock(3, 3);
                board.positionUnLock(2, 2);
                board.positionUnLock(1, 1);

            }
        });
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReentrantLock[][] boardContainer = board.getBoardContainer();
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COL; j++) {
               //System.out.print("[" + i + "," + j + "]=" + boardContainer[i][j].isLocked() + " ");
            }
           //System.out.println("");
        }
    }

    @Test
    public void manyLockInOneThread() {
        Board board = new Board();
        board.positionLock(1, 1);
        board.positionLock(1, 1);
        board.positionLock(1, 1);

        ReentrantLock[][] boardContainer = board.getBoardContainer();

        board.positionUnLock(1, 1);

        board.positionUnLock(1, 1);
        int result = boardContainer[1][1].getHoldCount();
        assertThat(result, is(1));
    }
}