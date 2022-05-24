package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DissableFeederAndShooter extends CommandBase {
  public DissableFeederAndShooter() {}

  @Override
  public void initialize() {
    RobotContainer.stopFeederSystem = true;
    RobotContainer.stopShooterSystem = true;
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
