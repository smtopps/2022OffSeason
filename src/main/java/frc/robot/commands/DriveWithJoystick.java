package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class DriveWithJoystick extends CommandBase {
  private final DriveBase driveBase;
  private final DoubleSupplier throttle;
  private final DoubleSupplier rotation;

  public DriveWithJoystick(DriveBase driveBase, DoubleSupplier throttle, DoubleSupplier rotation) {
    this.driveBase = driveBase;
    this.throttle = throttle;
    this.rotation = rotation;
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
    driveBase.arcadedrive(
      throttle.getAsDouble(), 
      rotation.getAsDouble(), 
      true);
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.autoArcadeStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
