package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DissableFeederAndShooter extends CommandBase {
  public DissableFeederAndShooter() {}

  @Override
  public void initialize() {
    RobotContainer.stopFeederSystem = true;
    RobotContainer.stopShooterSystem = true;
    SmartDashboard.putBoolean("stopFeederSystem", RobotContainer.stopFeederSystem);
    SmartDashboard.putBoolean("stopShooterSystem", RobotContainer.stopShooterSystem);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
