package org.dq;

/**
 * Hello world!
 */
public class App {
    private static final String LOCK = "lock";

    public static void main(String[] args) {
        while (true) {
            new Test().myTest();
        }
    }
}
