package main.java.collector;

import main.java.model.CacheMetaDataModel;
import main.java.model.DataModel;
import main.java.util.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class CustomDataCollector extends BaseDataCollector {

    public CustomDataCollector(int id) {
        this.id = id;
        this.myId = getClass().getName() + "-" + id;
        this.myShard = new ArrayList<>(Constants.getInstance().getMaxShardSize());
        this.headId = 0;
        this.tailId = 0;
        this.hitId = 0;
    }

    @Override
    public DataModel searchByKey(int key) {
        return null;
    }

    @Override
    public int insertOneData(DataModel dataModel) {
        incrementHitId();

        int position = getInsertIndex(dataModel);
        CacheMetaDataModel cacheMetaDataModel = new CacheMetaDataModel();
        cacheMetaDataModel.setHitsCount(0);
        cacheMetaDataModel.setKey(dataModel.getKey());
        cacheMetaDataModel.setLastHitId(0);
        myShard.add(position, cacheMetaDataModel);

        incrementTailId();
        return position;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", " +
                "collector name: " + myId + ", " +
                "size of collector: " + myShard.size() + ", " +
                "current headId: " + headId + ", " +
                "current tailId: " + tailId + ", " +
                "current hitId: " + hitId + " }";
    }

    @Override
    public long getSize() {
        return myShard.stream().filter(Objects::nonNull).count();
    }

    @Override
    public void removeOneData() {
        incrementHitId();
        myShard.remove(tailId);
        decrementTailId();
    }

}
