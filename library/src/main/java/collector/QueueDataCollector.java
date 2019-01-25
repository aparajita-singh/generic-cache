package main.java.collector;

import main.java.model.DataModelInterface;

public class QueueDataCollector implements DataCollectorInterface {
    public int id;

    public QueueDataCollector(int id) {
        this.id = id;
    }

    @Override
    public DataModelInterface searchByKey(Long key) {
        return null;
    }

    @Override
    public Long insertByKey(Long key, DataModelInterface dataModel) {
        return null;
    }

    @Override
    public Long removeByKey(DataModelInterface dataModel) {
        return null;
    }

    @Override
    public String toString() {
        return "QueueDataCollector";
    }
}
