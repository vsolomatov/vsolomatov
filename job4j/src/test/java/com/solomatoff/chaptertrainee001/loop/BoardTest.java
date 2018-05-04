package com.solomatoff.chaptertrainee001.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
    public class BoardTest {
        @Test
        public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
            Board board = new Board();
            String result = board.paint(3, 3);
            final String line = System.getProperty("line.separator");
            String expected = String.format("X X%s X %sX X%s", line, line, line);
            assertThat(result, is(expected));
        }
        @Test
        public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
            //тест, проверяющий формирование доски 5 на 4.
            Board board = new Board();
            String result = board.paint(5, 4);
            final String line = System.getProperty("line.separator");
            String expected = String.format("X X X%s X X %sX X X%s X X %s", line, line, line, line);
            assertThat(result, is(expected));
        }
    }