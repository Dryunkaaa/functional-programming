package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureApp {

    private static final String DIR_PATH = "/home/andrey/work/projects/emm_git";

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> findFilesInDir(DIR_PATH, "java"))
                .thenApply(List::size)
                .thenAccept(System.out::println)
                .join();
    }

    private static List<File> findFilesInDir(String rootPath, String extension) {
        File rootDir = new File(rootPath);
        return findFilesInDir(rootDir, extension);
    }

    private static List<File> findFilesInDir(File dir, String extension) {
        return CompletableFuture.supplyAsync(() -> {
            List<File> dirs = new ArrayList<>();
            LinkedList<File> files = new LinkedList<>();

            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    dirs.add(file);
                } else {
                    if (file.getName().endsWith(extension)) {
                        files.add(file);
                    }
                }
            }

            dirs.stream()
                    .map(subDir -> findFilesInDir(subDir, extension))
                    .forEach(files::addAll);

            return files;
        }).join();
    }
}
