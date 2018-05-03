package com.solomatoff.jdbc;

import org.junit.Test;

public class StylizerTest {

    @Test
    public void whenStylizerRunTest() {
        Stylizer.stylizerRun("./target/1.xsl", "./target/1.xml");
        }
}