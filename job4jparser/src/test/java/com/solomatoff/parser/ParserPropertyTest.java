package com.solomatoff.parser;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParserPropertyTest {
    @Test
    public void getProperty() {
        String username = ParserProperty.getProperty("username");
        assertThat(username, is("solomatov"));
    }
}