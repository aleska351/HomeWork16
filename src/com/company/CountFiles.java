package com.company;

import java.io.File;

public class CountFiles {
    private static int countFiles = 0;
    private static int countDirectory = 0;

    public static void main(String[] args) {

        File file = new File("D:\\Java");

        searchingFiles(file);
        System.out.println("Количество папок");
        System.out.println(countDirectory);
        System.out.println("Количество файлов");
        System.out.println(countFiles);
    }
    public static void searchingFiles(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                searchingFiles(entry);
                countDirectory++;
                continue;
            }
            countFiles++;
        }
    }
}
