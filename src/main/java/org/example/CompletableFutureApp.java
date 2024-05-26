package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureApp {

    private static final String DIR_PATH = "/home/andrey/work/projects/emm_git";

    public static void main(String[] args) {
        int filesCount = getCountOfFilesInDir(DIR_PATH, ".java");
        System.out.printf("Count of java files inside the dir = %d", filesCount);
    }

    private static int getCountOfFilesInDir(String rootPath, String extension) {
        File rootDir = new File(rootPath);
        return getCountOfFilesInDir(rootDir, extension);
    }

    private static int getCountOfFilesInDir(File dir, String extension) {
        return CompletableFuture.supplyAsync(() -> {
            if (!dir.exists() || !dir.isDirectory()) {
                return 0;
            }

            int countOfFilesInDir = 0;
            List<File> dirs = new ArrayList<>();

            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    dirs.add(file);
                } else {
                    if (file.getName().endsWith(extension)) {
                        countOfFilesInDir++;
                    }
                }
            }

            int countOfFilesInSubDirs = dirs.stream()
                    .map(subDir -> getCountOfFilesInDir(subDir, extension))
                    .reduce(0, Integer::sum);

            return countOfFilesInDir + countOfFilesInSubDirs;
        }).join();
    }
}
