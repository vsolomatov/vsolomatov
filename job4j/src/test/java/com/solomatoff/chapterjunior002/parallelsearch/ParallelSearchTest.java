package com.solomatoff.chapterjunior002.parallelsearch;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class ParallelSearchTest {

    @Test
    public void whenParallelSearchTxtAndJava() {
        String directory = "c:/projects";
        String keyword = "Thread";
        List<String> exts = new ArrayList<>(Arrays.asList("txt", "java"));

        ParallelSearch parallelSearch = new ParallelSearch(directory, keyword, exts, 40);
        parallelSearch.init();
        BlockingQueue<String> result = parallelSearch.result();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = 1;
        for (String file : result) {
           //System.out.println("(" + index + ") " + file);
            index++;
        }
    }

    @Test
    public void whenParallelSearchTxt() {
        String directory = "c:/projects";
        String keyword = "Thread";
        List<String> exts = new ArrayList<>(Collections.singletonList("txt"));

        ParallelSearch parallelSearch = new ParallelSearch(directory, keyword, exts, 5);
        parallelSearch.init();
        BlockingQueue<String> result = parallelSearch.result();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = 1;
        for (String file : result) {
           //System.out.println("(" + index + ") " + file);
            index++;
        }
    }
}