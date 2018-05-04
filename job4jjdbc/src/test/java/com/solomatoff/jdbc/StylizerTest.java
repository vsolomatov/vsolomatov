package com.solomatoff.jdbc;

import org.junit.Test;

public class StylizerTest {

    @Test
    public void whenStylizerRunTest() {
        Stylizer.stylizerRun("1.xsl", "1.xml");
        }
}