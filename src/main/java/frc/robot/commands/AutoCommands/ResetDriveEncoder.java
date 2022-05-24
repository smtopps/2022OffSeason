package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class ResetDriveEncoder extends CommandBase {
  DriveBase driveBase;

  public ResetDriveEncoder(DriveBase driveBase) {
    this.driveBase = driveBase;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    driveBase.resetEncoderPosition();
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
