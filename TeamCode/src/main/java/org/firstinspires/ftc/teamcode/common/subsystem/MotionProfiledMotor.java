package org.firstinspires.ftc.teamcode.common.subsystem;

import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import lombok.Getter;
import lombok.Setter;

/**
 * A wrapper to help you manage Motion Profiles
 */
public class MotionProfiledMotor {
    private final DcMotor motor;
    private MotionProfile profile;
    private final ElapsedTime timer;
    private final PIDFController controller;

    @Getter
    @Setter
    private double maxVelocity;
    @Getter
    @Setter
    private double maxAcceleration;


    /**
     * Constructs a MotionProfiledMotor from a motor and PIDFController
     * @param motor the motor to control
     * @param controller the PIDF controller
     */
    public MotionProfiledMotor(DcMotor motor, PIDFController controller) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.motor = motor;
        this.controller = controller;
        timer = new ElapsedTime();
        maxVelocity = 0;
        maxAcceleration = 0;
    }

    /**
     * Runs one cycle of the controller
     * @param dt the change in time since last call
     */
    public void update(long dt) {
        if (profile != null) {
            MotionState motionState = profile.get(timer.milliseconds());
            controller.setTargetPosition(motionState.getX());
            controller.setTargetVelocity(motionState.getV());
            controller.setTargetAcceleration(motionState.getA());
            motor.setPower(controller.update(motor.getCurrentPosition()));
        }
    }

    /**
     * Sets the target of the motor
     * @param target where the motor should go
     */
    public void setTarget(int target) {
        profile = MotionProfileGenerator.generateSimpleMotionProfile(
                new MotionState(motor.getCurrentPosition(), 0, 0),
                new MotionState(target, 0, 0),
                maxVelocity, maxAcceleration
        );
        timer.reset();
    }

    /**
     * Gets the current profile target, returns current position if profile is null
     * @return the target
     */
    public int getTarget() {
        return profile == null ? motor.getCurrentPosition() : (int) profile.end().getX();
    }


}
