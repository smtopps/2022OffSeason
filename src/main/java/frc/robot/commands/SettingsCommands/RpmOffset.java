package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RpmOffset extends CommandBase {
  private final OffsetDirection offsetDirection;

  public RpmOffset(OffsetDirection offsetDirection) {
    this.offsetDirection = offsetDirection;
  }

  @Override
  public void initialize() {
    if(offsetDirection == OffsetDirection.INCREASE) {
      RobotContainer.RpmOffset = RobotContainer.RpmOffset+10;
    }else{
      RobotContainer.RpmOffset = RobotContainer.RpmOffset-10;
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

  public enum OffsetDirection{
    INCREASE, DECERASE
  }
}
