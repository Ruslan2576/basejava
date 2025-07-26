package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./src/ru/javawebinar/basejava";
        File file = new File(filePath);
        recursiveOutput(file);
    }

    public static void recursiveOutput(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    recursiveOutput(f);
                } else {
                    System.out.println(f);
                }
            }
        }
    }
}
