// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class TurnByAngleBasic extends CommandBase {
  DriveBase driveBase;
  double angle;
  double distance;
  /** Creates a new DriveByDistanceBasic. */
  public TurnByAngleBasic(DriveBase driveBase, double angle) {
    this.driveBase = driveBase;
    this.angle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveBase.resetEncoderPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = ((Math.PI*22) / 360) *angle;
    double leftError = distance - driveBase.getLeftEncoderDistance();
    double speed = leftError * 0.05 + 0.15;

    if(speed > 0.5) {
      speed = 0.5;
    }else if(speed < -0.5) {
      speed = -0.5;
    }

    driveBase.autoArcadedrive(0, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.autoArcadedrive(0, 0);
    driveBase.resetEncoderPosition();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(driveBase.getLeftEncoderDistance()) < Math.abs(distance)) {
      return false;
    }else{
      return true;
    }
  }
}
