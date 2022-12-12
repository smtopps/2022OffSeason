package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;

public class DriverTriggerLButton extends Button {
    @Override
    public boolean get() {
        return RobotContainer.driverController.getLeftTriggerAxis() > 0.5;
    }
}
