package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableLimelight extends CommandBase {

  private final LimelightState limelightState;
  public EnableLimelight(LimelightState limelightState) {
    this.limelightState = limelightState;
  }

  @Override
  public void initialize() {
    if(limelightState == LimelightState.TOGGLE) {
      RobotContainer.enableLimelight = !RobotContainer.enableLimelight;
    }else if(limelightState == LimelightState.DISSABLED) {
      RobotContainer.enableLimelight = false;
    }else if(limelightState == LimelightState.ENABLED) {
      RobotContainer.enableLimelight = true;
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

  public enum LimelightState {
    DISSABLED, ENABLED, TOGGLE
  }
}
