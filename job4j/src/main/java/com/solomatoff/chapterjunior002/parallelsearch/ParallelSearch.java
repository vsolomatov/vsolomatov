package com.solomatoff.chapterjunior002.parallelsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static java.nio.file.FileVisitResult.CONTINUE;

@ThreadSafe
public class ParallelSearch {
    private final Path root;
    private final String text;
    private final List<String> exts;
    private static final Path DUMMY = Paths.get("");
    private volatile boolean finish = false;

    @GuardedBy("this")
    private final BlockingQueue<Path> files = new LinkedBlockingQueue<>();
    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    /**
     *  Конструктор
     * @param root начальный каталог для поиска
     * @param text искомый текст
     * @param fileEctsions расширения файлов (для поиска только в таких файлах)
     */
    ParallelSearch(String root, String text, List<String> fileEctsions) {
        this.root = Paths.get(root);
        this.text = text;
        this.exts = fileEctsions;
    }

    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                searchTask();
                finish = true;
                System.out.println("Завершила работу нить search.");
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                FillingFileQueue fillingFileQueue = new FillingFileQueue();
                try {
                    Files.walkFileTree (root, fillingFileQueue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                files.offer(DUMMY);
                while (!finish) {}
                System.out.println("Завершила работу нить read.");
            }
        };
        search.start();
        read.start();
    }

    synchronized List<String> result() {
        return this.paths;
    }

    class FillingFileQueue extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            String fileName = file.getFileName().toString();
            String extension;
            try {
                extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            } catch (Exception e) {
                extension = "";
            }
            if (exts.contains(extension)) {
                System.out.println("Добавляем в очередь files [" + file + "]");
                files.offer(file);
            }
            return CONTINUE;
        }

        // If there is some error accessing
        // the file, let the user know.
        // If you don't override this method
        // and an error occurs, an IOException
        // is thrown.
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }

    /**
     * This task searches files for a given keyword.
     */

        private void searchTask() {
            boolean done = false;
            while (!done) {
                Path file = files.poll();
                if (file != null) {
                    if (file == DUMMY) {
                        done = true;
                    } else {
                        search(file);
                    }
                }
            }
        }

        /**
         * Searches a file for a given keyword and prints all matching lines.
         * @param file the file to search
         */
        private void search(Path file) {
            try {
                System.out.println("    Осуществляем поиск текста в [" + file + "]");
                Scanner in = new Scanner(file.toFile());
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(this.text)) {
                        paths.add(file.toString());
                        break;
                    }
                }
            } catch (FileNotFoundException | NullPointerException e) {
                e.printStackTrace();
            }
        }


}




