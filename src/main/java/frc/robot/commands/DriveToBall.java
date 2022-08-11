// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.PhotonVision;

public class DriveToBall extends CommandBase {
  private final DriveBase driveBase;
  private final PhotonVision photonVision;
  private final DoubleSupplier throttle;
  private final DoubleSupplier rotation;
  private final PIDController rotatPidController = new PIDController(0, 0, 0);
  double rotateSpeed;
  /** Creates a new DriveToBall. */
  public DriveToBall(DriveBase driveBase, PhotonVision photonVision, DoubleSupplier throttle, DoubleSupplier rotation) {
    this.driveBase = driveBase;
    this.photonVision = photonVision;
    this.throttle = throttle;
    this.rotation = rotation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotatPidController.setSetpoint(0);
    rotatPidController.setTolerance(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(photonVision.targetAvailable()){
      rotateSpeed = rotatPidController.calculate(photonVision.AngleToBall());
    }else{
      rotateSpeed = rotation.getAsDouble();
    }
    driveBase.arcadedrive(
      throttle.getAsDouble(), 
      rotateSpeed, 
      true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.autoArcadeStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
