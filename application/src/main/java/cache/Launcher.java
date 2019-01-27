package cache;

import main.java.cache.GenericCache;
import main.java.util.Constants;

public class Launcher {
    public static void main(String[] args) {
        GenericCache cache = new GenericCache(1, 10);
        cache.initializeCollector(Constants.CacheType.QUEUE);
        System.out.println(cache.toString());
    }
}
