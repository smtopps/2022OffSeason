// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class TriggerRButton extends Trigger {
    @Override
    public boolean get() {
        return RobotContainer.driverController.getRightTriggerAxis() > 0.5;
    }
}
