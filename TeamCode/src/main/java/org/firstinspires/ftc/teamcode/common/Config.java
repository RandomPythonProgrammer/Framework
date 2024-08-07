package org.firstinspires.ftc.teamcode.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Store global data
 */
public class Config {
    private static final Map<String, Object> data = new HashMap<>();

    /**
     * Insert or update data into config
     * @param key the name of the option
     * @param item the data for the option
     */
    public static void put(String key, Object item) {
        data.put(key, item);
    }

    /**
     * Get data from the config
     * @param type the data type to be retrieved
     * @param key the name of the option
     * @return the data cast into the specified type
     * @param <T> the data type to be retrieved
     */
    public static <T> T get(Class<T> type, String key){
        return type.cast(data.get(key));
    }

    /**
     * Remove an option from the config
     * @param key the option
     */
    public static void remove(String key) {
        data.remove(key);
    }

    /**
     * Check if an option exists
     * @param key the option name
     * @return if the option exists
     */
    public static boolean hasKey(String key) {
        return data.containsKey(key);
    }
}
