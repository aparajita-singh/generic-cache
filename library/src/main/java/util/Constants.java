package main.java.util;

public class Constants {
    private static Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    public enum CacheType {
        QUEUE
        //TODO: add implementations for: STACK, LIST, TREE, HEAP, SORTED_SET, SET
    }

    private Constants() {
    }
}
