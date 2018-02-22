package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class CounterTest {
    @Test
    public void whenCounterStartOneFinishTen() {
        Counter count = new Counter();
        assertThat(
                count.add(1, 10),
                is(30)
        );
    }
    @Test
    public void whenCounterStartFiveFinishTwentyNine() {
        Counter count = new Counter();
        assertThat(
                count.add(5, 29),
                is(204)
        );
    }
}
