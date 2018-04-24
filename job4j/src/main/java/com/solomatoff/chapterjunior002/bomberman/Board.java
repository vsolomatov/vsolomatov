package com.solomatoff.chapterjunior002.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    static final int ROW = 10;
    static final int COL = 10;
    private final ReentrantLock[][] boardContainer = new ReentrantLock[ROW][COL];

    Board() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                this.boardContainer[i][j] = new ReentrantLock();
            }
        }
    }

    boolean positionLock(int row, int col) {
        //System.out.println("[" + Thread.currentThread().getName() + "]. [" + row + "," + col + "] Состояние перед блокировкой isLocked = " + boardContainer[row][col].isLocked());
        boolean result;
        try {
            result = boardContainer[row][col].tryLock(500, TimeUnit.MILLISECONDS);
            /*if (result) {
                System.out.println("[" + Thread.currentThread().getName() + "]. [" + row + "," + col + "] заблокирована потоком = " + result + "(getHoldCount = " + boardContainer[row][col].getHoldCount() + ")");
            }*/
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            result = false;
        }
        return result;
    }

    boolean positionUnLock(int row, int col) {
        //System.out.println("[" + Thread.currentThread().getName() + "]. [" + row + "," + col + "] Состояние перед РАЗБЛОКИРОВКОЙ isLocked = " + boardContainer[row][col].isLocked());
        boolean result = true;
        try {
            boardContainer[row][col].unlock();
        } catch (IllegalMonitorStateException e) {
            //System.out.println("    [" + Thread.currentThread().getName() + "]. [" + row + "," + col + "] Этот поток не владеет блокировкой этого объекта!!! (getHoldCount = " + boardContainer[row][col].getHoldCount() + ")");
            result = false;
        }
        /*if (result) {
            System.out.println("[" + Thread.currentThread().getName() + "]. [" + row + "," + col + "] разблокирована потоком. (getHoldCount = " + boardContainer[row][col].getHoldCount() + ")");
        }*/
        return result;
    }

    ReentrantLock[][] getBoardContainer() {
        return  this.boardContainer;
    }

}
