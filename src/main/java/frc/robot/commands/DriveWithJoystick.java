package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class DriveWithJoystick extends CommandBase {
  private final DriveBase driveBase;
  //private final double throttle;
  //private final double rotation;

  public DriveWithJoystick(DriveBase driveBase, double throttle, double rotation) {
    this.driveBase = driveBase;
    //this.throttle = throttle;
    //this.rotation = rotation;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    driveBase.setmaxoutput(Constants.MAX_SPEED_DRIVE);
    driveBase.setdeadband(Constants.DEADBAND_DRIVE);
    driveBase.setneutralmode(Constants.NEUTRAL_MODE_DRIVE);
  }

  @Override
  public void execute() {
    driveBase.arcadedrive();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
