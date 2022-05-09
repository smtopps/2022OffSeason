package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseTrajectory;

public class DriveWithJoystick extends CommandBase {
  private final DriveBaseTrajectory driveBaseTrajectory;
  //private final double throttle;
  //private final double rotation;

  public DriveWithJoystick(DriveBaseTrajectory driveBaseTrajectory, double throttle, double rotation) {
    this.driveBaseTrajectory = driveBaseTrajectory;
    //this.throttle = throttle;
    //this.rotation = rotation;
    addRequirements(driveBaseTrajectory);
  }

  @Override
  public void initialize() {
    driveBaseTrajectory.setmaxoutput(Constants.MAX_SPEED_DRIVE);
    driveBaseTrajectory.setdeadband(Constants.DEADBAND_DRIVE);
    driveBaseTrajectory.setneutralmode(Constants.NEUTRAL_MODE_DRIVE);
  }

  @Override
  public void execute() {
    driveBaseTrajectory.arcadedrive();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
