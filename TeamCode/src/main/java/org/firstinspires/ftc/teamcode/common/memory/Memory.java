package org.firstinspires.ftc.teamcode.common.memory;

import java.util.Set;

/**
 * An interface that specifies a memory storage device
 */
public interface Memory {
    /**
     * Insert or update data into memory
     * @param key the name of the option
     * @param item the data for the option
     */
    void put(String key, String item);

    /**
     * Get data from the memory
     * @param key the name of the option
     * @return the data cast into the specified type
     */
     String get(String key);

    /**
     * Remove an option from the memory
     * @param key the option
     */
    void remove(String key);

    /**
     * Check if an option exists
     * @param key the option name
     * @return if the option exists
     */
    boolean hasKey(String key);

    /**
     * Gets the set of all keys
     * @return the key set
     */
    public Set<String> keySet();
}
