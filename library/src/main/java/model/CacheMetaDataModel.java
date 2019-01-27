package main.java.model;

public class CacheMetaDataModel {
    /**
     * key refers to the key used from the actual data to determine which cache slot to assign this data to
     */
    private int key;

    /**
     * hitsCount refers to the number of times this element of the cache was searched for since it was last updated
     */
    private int hitsCount;

    /**
     * lastHitId refers to the hit ID of the search that last referred to this element of the cache
     */
    private int lastHitId;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public void setHitsCount(int hitsCount) {
        this.hitsCount = hitsCount;
    }

    public int getLastHitId() {
        return lastHitId;
    }

    public void setLastHitId(int lastHitId) {
        this.lastHitId = lastHitId;
    }
}
