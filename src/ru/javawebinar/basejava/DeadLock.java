package ru.javawebinar.basejava;

public class DeadLock {
    private static final Object ONE = new Object();
    private static final Object TWO = new Object();

    public static void main(String[] args) {
        doIt(ONE, TWO);
        doIt(TWO, ONE);
    }

    public static void doIt(Object one, Object two) {
        new Thread(() -> {
            synchronized (one) {
                System.out.println("Hello ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (two) {
                    System.out.println("world!");
                }
            }
        }).start();
    }
}
