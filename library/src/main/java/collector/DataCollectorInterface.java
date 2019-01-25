package main.java.collector;

import main.java.model.DataModelInterface;

public interface DataCollectorInterface {
    int id = -1;
    DataModelInterface searchByKey(Long key);
    Long insertByKey(Long key, DataModelInterface dataModel);
    Long removeByKey(DataModelInterface dataModel);
    String toString();
}
