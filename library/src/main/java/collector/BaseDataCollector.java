package main.java.collector;

import main.java.model.CacheMetaDataModel;
import main.java.model.DataModel;
import main.java.util.Constants;

import java.util.ArrayList;

public abstract class BaseDataCollector {
    /**
     * the numeric ID of this shard of the cache
     */
    int id = -1;

    /**
     * the human-readable ID of this shard of the cache
     */
    String myId;

    /**
     * the actual cache
     */
    ArrayList<CacheMetaDataModel> myShard;

    /**
     * the index of the first element of this shard
     */
    int headId;

    /**
     * the index of the last element of this shard
     */
    int tailId;

    /**
     * hitId is a pseudo-auto-incremented field which will be incremented every time there is a request to the cache
     */
    int hitId;

    /**
     * @param key the attribute from the actual data used for searching the cache
     * @return the data if found in the cache, else null
     */
    public abstract DataModel searchByKey(int key);

    /**
     * @param dataModel the data to be inserted in the cache
     * @return the position where the data was stored. if there is already data in that position and the key of the existing data is matching with the key of this data, the data is overwritten. else, attempt to insert in adjacent positions, as determined by the collisionAllowance attribute in config.
     */
    public abstract int insertOneData(DataModel dataModel);

    /**
     * @return human-readable information about this shard in string form
     */
    public abstract String toString();

    /**
     * @return the number of elements in this shard
     */
    public abstract long getSize();

    /**
     * @return remove one element from this shard, as determined by the type of cache
     */
    public abstract void removeOneData();

    int getInsertIndex(DataModel data) {
        int position = hasher(data.getKey());
        int collisionAllowance = Constants.getInstance().getCollisionAllowance();
        if (positionIsNull(position)) {
            return position;
        } else {
            for (int incrementor = 0; incrementor < collisionAllowance; incrementor++) {
                for (int iterator : mirrorPositions(position, incrementor)) {
                    if (positionIsNull(iterator)) {
                        return iterator;
                    }
                }
            }

            int min = position;
            for (int incrementor = 0; incrementor < collisionAllowance; incrementor++) {
                for (int iterator : mirrorPositions(position, incrementor)) {
                    if (positionHasLessHits(iterator, min)) {
                        min = iterator;
                    } else if (positionHasSameHits(iterator, min) && positionIsLessRecent(iterator, min)) {
                        min = iterator;
                    }
                }
            }
            removeOneData(min);
            return min;
        }
    }

    void removeOneData(int key) {
        if(!positionIsNull(key)) {
            myShard.remove(key);
        }
    }

    private int[] mirrorPositions(int position, int i) {
        return new int[]{position - i, position + i};
    }

    private boolean positionIsLessRecent(int position, int min) {
        return myShard.get(position).getLastHitId() < myShard.get(min).getLastHitId();
    }

    private boolean positionHasSameHits(int position, int min) {
        return myShard.get(position).getHitsCount() == myShard.get(min).getHitsCount();
    }

    private boolean positionHasLessHits(int position, int min) {
        return myShard.get(position).getHitsCount() < myShard.get(min).getHitsCount();
    }

    protected boolean positionIsNull(int position) {
        return myShard.get(position) == null;
    }

    int hasher(int key) {
        return String.valueOf(key).hashCode() % Constants.getInstance().getMaxShardSize();
    }

    void incrementHitId() {
        hitId++;
    }

    void decrementTailId() {
        tailId--;
    }

    void incrementTailId() {
        tailId++;
    }
}
