package com.solomatoff.tracker;

import org.junit.Test;
import java.io.File;

public class TrackerPropertyTest {

    @Test
    public void getPropertyByKey() {
        TrackerProperty trackerProperty = new TrackerProperty("tracker.properties");

        String url = trackerProperty.getProperty("url");
       //System.out.println("url = " + url);
        String username = trackerProperty.getProperty("username");
       //System.out.println("username = " + username);
        String password = trackerProperty.getProperty("password");
       //System.out.println("password = " + password);

        trackerProperty.setProperty("password", "password");
        password = trackerProperty.getProperty("password");
       //System.out.println("password = " + password);

        trackerProperty.setProperty("username", "postgres");
        username = trackerProperty.getProperty("username");
       //System.out.println("username = " + username);

        trackerProperty.setProperty("host", "localhost:5432");
        url = trackerProperty.getProperty("host");
       //System.out.println("url = " + url);

        trackerProperty.setPropertyAsDefault("tracker.properties");
    }
}