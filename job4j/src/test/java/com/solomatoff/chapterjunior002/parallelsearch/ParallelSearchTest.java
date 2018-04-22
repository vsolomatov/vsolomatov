package com.solomatoff.chapterjunior002.parallelsearch;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class ParallelSearchTest {

    @Test
    public void whenParallelSearchTxtAndJava() {
        String directory = "c:/projects";
        String keyword = "Thread";
        List<String> exts = new ArrayList<>(Arrays.asList("txt", "java"));

        ParallelSearch parallelSearch = new ParallelSearch(directory, keyword, exts);
        parallelSearch.init();
        List<String> result = parallelSearch.result();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + result.get(i));
        }
    }

    @Test
    public void whenParallelSearchTxt() {
        String directory = "c:/projects";
        String keyword = "Thread";
        List<String> exts = new ArrayList<>(Arrays.asList("txt"));

        ParallelSearch parallelSearch = new ParallelSearch(directory, keyword, exts);
        parallelSearch.init();
        List<String> result = parallelSearch.result();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + result.get(i));
        }
    }
}