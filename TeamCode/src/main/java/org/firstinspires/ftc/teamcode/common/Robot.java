package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import lombok.Getter;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * A container class for the robot and the subsystems
 */
public class Robot {
    @Getter
    private final SampleMecanumDrive drive;
    private long lastTime;
    // declare the subsystems here with the @Getter flag


    /**
     * Constructs the subsystems and drive from the hardware map.
     * @param hardwareMap the hardware map
     */
    public Robot(HardwareMap hardwareMap) {
        double dt = System.currentTimeMillis() - lastTime;
        drive = new SampleMecanumDrive(hardwareMap);
        // create the subsystems here

        lastTime = System.currentTimeMillis();
    }

    /**
     * Runs one cycle of each of the subsystems and the drive
     */
    public void update() {
        drive.update();
        // add the call to update your subsystems
    }
}