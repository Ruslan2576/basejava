package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "C:\\Java\\basejava\\src\\ru\\javawebinar\\basejava";
        File file = new File(filePath);
        recursiveOutput(file);
    }

    public static void recursiveOutput(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    System.out.print("  ");
                    recursiveOutput(file);
                } else {
                    System.out.println("File: " + file.getName());
                }
            }
        }
    }
}
