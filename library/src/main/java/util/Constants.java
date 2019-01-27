package main.java.util;

public class Constants {
    private static Constants ourInstance = new Constants();
    private int maxShardSize;
    private int collisionAllowance;

    public static Constants getInstance() {
        return ourInstance;
    }

    public int getMaxShardSize() {
        return maxShardSize;
    }

    public void setMaxShardSize(int maxShardSize) {
        this.maxShardSize = maxShardSize;
    }

    public int getCollisionAllowance() {
        return collisionAllowance;
    }

    public void setCollisionAllowance(int collisionAllowance) {
        this.collisionAllowance = collisionAllowance;
    }

    public enum CacheType {
        QUEUE
        //TODO: add implementations for: STACK, LIST, TREE, HEAP, SORTED_SET, SET
    }

    private Constants() {
    }
}
