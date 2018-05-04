package com.solomatoff.chaptertrainee003.comparator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListCompareTest {
    @Test
    public void whenStringsAreEqualThenZero() {
        ListCompare obgListCompare = new ListCompare();
        int rst = obgListCompare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        ListCompare obgListCompare = new ListCompare();
        int rst = obgListCompare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreateThanRightResultShouldBePositive() {
        ListCompare obgListCompare = new ListCompare();
        int rst = obgListCompare.compare(
                "Ivanova",
                "Ivanov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        ListCompare obgListCompare = new ListCompare();
        int rst = obgListCompare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        ListCompare obgListCompare = new ListCompare();
        int rst = obgListCompare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        ListCompare obgListCompare = new ListCompare();
        int rst = obgListCompare.compare(
                "Patrov",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}