package com.solomatoff.chaptertrainee003.shape;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("   +   ")
                                .append(System.lineSeparator())
                                .append("  + +  ")
                                .append(System.lineSeparator())
                                .append(" +   + ")
                                .append(System.lineSeparator())
                                .append("+++++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
       //System.out.println(triangle.draw());
    }
}
