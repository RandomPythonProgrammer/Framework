package org.firstinspires.ftc.teamcode.common;

import lombok.Getter;
import lombok.Setter;
import org.firstinspires.ftc.teamcode.common.command.Command;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Creates an async queue to execute your commands.
 * You can just set the current command or also manipulate the queue to schedule commands.
 */
public class Processor {
    @Getter
    private final Deque<Command> commandQueue;
    @Getter
    @Setter
    private Command current;
    private long lastTime;

    /**
     * constructs an empty processor
     */
    public Processor() {
        commandQueue = new LinkedList<>();
        lastTime = System.currentTimeMillis();
        current = null;
    }

    /**
     * Check the status of the current command, pulls a new command from the queue if needed.
     * Runs the update of the current command one cycle.
     * @param robot the robot
     */
    public void update(Robot robot) {
        long dt = System.currentTimeMillis() - lastTime;
        if (current == null || current.isDone()) {
            if (!commandQueue.isEmpty()) {
                current = commandQueue.remove();
            }
        }

        if (current != null) {
            current.update(robot, dt);
        }
        lastTime = System.currentTimeMillis();
    }

    /**
     * Checks if the queue has tasks
     * @return if the queue has work to do
     */
    public boolean isBusy() {
       return (current == null || current.isDone()) && commandQueue.isEmpty();
    }
}



