package main.java.cache;

import main.java.collector.DataCollectorInterface;
import main.java.collector.QueueDataCollector;
import main.java.util.Constants;

public class GenericCache {
    public String initializeCache(Constants.CacheType cacheType) {
        switch (cacheType) {
            case QUEUE:
                DataCollectorInterface dataCollector = new QueueDataCollector(0);
                return dataCollector.toString();
            default: return "DEFAULT";
        }
    }
}
