package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;

public class OperatorTriggerLButton extends Button {
    @Override
    public boolean get() {
        return RobotContainer.operatorController.getLeftTriggerAxis() > 0.5;
    }
}
