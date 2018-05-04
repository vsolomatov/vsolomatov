package com.solomatoff.chaptertrainee001.calculate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test.
 *
 * @author Vyacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculateTest {
    /**
     * Test echo.
     */
    @Test
    public void whenTakeNameThenTreeEchoPlusName() {
        String input = "Vyacheslav Solomatov";
        String expect = "Echo, echo, echo : Vyacheslav Solomatov";
        Calculate calc = new Calculate();
        String result = calc.echo(input);
        assertThat(result, is(expect));
    }
}