package cache;

import main.java.cache.GenericCache;
import main.java.util.Constants;

public class Launcher {
    public static void main(String[] args) {
        System.out.println(new GenericCache().initializeCache(Constants.CacheType.QUEUE));
    }
}
