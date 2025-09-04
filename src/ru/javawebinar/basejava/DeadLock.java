package ru.javawebinar.basejava;

public class DeadLock {
    private static final Object ONE = new Object();
    private static final Object TWO = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (ONE) {
                System.out.println("Hello ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (TWO) {
                    System.out.println("world!");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (TWO) {
                System.out.println("Goodbye ");
                synchronized (ONE) {
                    System.out.println("world!");
                }
            }
        }).start();
    }
}
