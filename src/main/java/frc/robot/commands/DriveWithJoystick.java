package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveWithJoystick extends CommandBase {
  private final DriveBase driveBase;
  private DoubleSupplier throttle;
  private DoubleSupplier rotation;

  public DriveWithJoystick(DriveBase driveBase, DoubleSupplier throttle, DoubleSupplier rotation) {
    this.driveBase = driveBase;
    this.throttle = throttle;
    this.rotation = rotation;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {}

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
