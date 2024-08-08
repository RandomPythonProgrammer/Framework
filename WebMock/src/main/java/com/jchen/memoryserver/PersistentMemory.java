package com.jchen.memoryserver;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * com.jchen.memoryserver.Memory that is stored temporary in RAM
 */
public class PersistentMemory implements Memory {
    private final Map<String, String> data;
    private static PersistentMemory instance = null;

    protected PersistentMemory() {
        data = new ConcurrentHashMap<>();
    }

    public static PersistentMemory getInstance() {
        if (instance == null) {
            instance = new PersistentMemory();
        }
        return instance;
    }

    @Override
    public void put(String key, String item) {
        data.put(key, item);
    }

    @Override
    public String get(String key) {
        return data.get(key);
    }

    @Override
    public void remove(String key) {
        data.remove(key);
    }

    @Override
    public boolean hasKey(String key) {
        return data.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        return data.keySet();
    }
}
