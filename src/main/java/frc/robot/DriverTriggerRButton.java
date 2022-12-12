package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;

public class DriverTriggerRButton extends Button {
    @Override
    public boolean get() {
        return RobotContainer.driverController.getRightTriggerAxis() > 0.5;
    }
}
