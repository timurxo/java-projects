package com.example;

/*
    SAVES AND RETRIEVES DATA IN ARRAYLIST
 */

import java.util.List;
import java.util.Map;

public interface Info {

    Map<Integer, String> save();

    void retrieve(Map<Integer, String> savedInfo);

}
