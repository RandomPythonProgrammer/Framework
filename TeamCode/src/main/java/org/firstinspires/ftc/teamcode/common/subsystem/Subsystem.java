package org.firstinspires.ftc.teamcode.common.subsystem;

/**
 * Interface to represent a subsystem
 */
public interface Subsystem {
    /**
     * Runs one cycle of the subsystem
     * @param dt the time past since last call
     */
    void update(long dt);
}
