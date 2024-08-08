package org.firstinspires.ftc.teamcode.common.memory;

import android.os.Environment;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory that is stored temporary in storage so persists
 */
public class PersistentMemory implements Memory {
    private Map<String, String> data;
    private final File file;

    private static PersistentMemory instance = null;

    private PersistentMemory() {
        data = new ConcurrentHashMap<>();
        file = new File(Environment.getDataDirectory(), "data");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        load();
    }

    public static PersistentMemory getInstance() {
        if (instance == null) {
            instance = new PersistentMemory();
        }
        return instance;
    }

    private synchronized void load() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            data = (Map<String, String>) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void save() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(String key, String item) {
        data.put(key, item);
        save();
    }

    @Override
    public String get(String key) {
        return data.get(key);
    }

    @Override
    public void remove(String key) {
        data.remove(key);
        save();
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
