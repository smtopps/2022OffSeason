package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableColorSensor extends CommandBase {
  private final ColorSensorState colorSensorState;

  public EnableColorSensor(ColorSensorState colorSensorState) {
    this.colorSensorState = colorSensorState;
  }

  @Override
  public void initialize() {
    if(colorSensorState == ColorSensorState.TOGGLE) {
      RobotContainer.enableColorSensor = !RobotContainer.enableColorSensor;
    }else if(colorSensorState == ColorSensorState.DISSABLED) {
      RobotContainer.enableColorSensor = false;
    }else if(colorSensorState == ColorSensorState.ENABLED) {
      RobotContainer.enableColorSensor = true;
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }

  public enum ColorSensorState {
    ENABLED, DISSABLED, TOGGLE
  }
}
