package main.java.cache;

import main.java.collector.BaseDataCollector;
import main.java.collector.CustomDataCollector;
import main.java.model.DataModel;
import main.java.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenericCache {
    int id;
    int shardCount;
    private ArrayList<BaseDataCollector> dataCollectorsList;

    public GenericCache(int id, int shardCount) {
        this.id = id;
        this.shardCount = shardCount;
        this.dataCollectorsList = new ArrayList<>();
    }

    public Boolean initializeCollector(Constants.CacheType cacheType) {
        switch (cacheType) {
            case QUEUE:
                IntStream.range(0, shardCount).forEach(i -> this.dataCollectorsList.add(new CustomDataCollector(i)));
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return this.dataCollectorsList.stream().map(e -> e.toString()).collect(Collectors.toList()).toString();
    }

    public DataModel searchData(int key) {
        return dataCollectorsList.get(hasher(key)).searchByKey(key);
    }

    public HashMap<String, Integer> insertData(DataModel data) {
        int shardId = hasher(data.getKey());

        HashMap<String, Integer> dataPosition = new HashMap<>();
        dataPosition.put("shardId", shardId);
        dataPosition.put("collectorId", dataCollectorsList.get(shardId).insertOneData(data));
        return dataPosition;
    }

    private boolean collectorIsFull(int shardId) {
        return dataCollectorsList.get(shardId).getSize() == Constants.getInstance().getMaxShardSize();
    }

    private int hasher(int key) {
        return String.valueOf(key).hashCode() % shardCount;
    }
}
