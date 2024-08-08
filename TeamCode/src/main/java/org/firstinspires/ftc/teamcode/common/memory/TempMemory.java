package org.firstinspires.ftc.teamcode.common.memory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory that is stored temporary in RAM
 */
public class TempMemory implements Memory {
    private final Map<String, String> data;
    private static TempMemory instance = null;

    protected TempMemory() {
        data = new ConcurrentHashMap<>();
    }

    public static TempMemory getInstance() {
        if (instance == null) {
            instance = new TempMemory();
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
