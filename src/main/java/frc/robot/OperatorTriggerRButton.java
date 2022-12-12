package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;

public class OperatorTriggerRButton extends Button {
    @Override
    public boolean get() {
        return RobotContainer.operatorController.getRightTriggerAxis() > 0.5;
    }
}
