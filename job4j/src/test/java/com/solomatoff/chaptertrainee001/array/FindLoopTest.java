package com.solomatoff.chaptertrainee001.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    @Test
    public void whenFindEl() {
        FindLoop rst = new FindLoop();
        int[] aArr = {1, 4, 25, 16, 9};
        //System.out.println(rst.indexOf(aArr,9));
        assertThat(rst.indexOf(aArr, 9), is(4));
    }
    @Test
    public void whenDontFindEl() {
        FindLoop rst = new FindLoop();
        int[] aArr = {1, 4, 25, 16, 9, 36, 49};

        //System.out.println(rst.indexOf(aArr,8));

        assertThat(rst.indexOf(aArr, 8), is(-1));
    }
}
