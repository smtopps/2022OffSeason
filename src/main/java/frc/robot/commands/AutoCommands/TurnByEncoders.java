// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class TurnByEncoders extends CommandBase {
  DriveBase driveBase;
  private double Angle;
  private double leftError;
  private double rightError;
  /** Creates a new DriveByDistance. */
  public TurnByEncoders(DriveBase driveBase, double Angle) {
    this.driveBase = driveBase;
    this.Angle = Angle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double Setpoint = (Math.PI*28) / Angle;
    leftError = Setpoint - driveBase.getLeftEncoderDistance();
    rightError = Setpoint - driveBase.getRightEncoderDistance();

    double leftSpeed = leftError * 0.1;
    if (leftError < 0.1 && leftError > -0.1) {
      leftSpeed = 0;
    }else if(leftSpeed > 5) {
      leftSpeed = 5;
    }else if(leftSpeed < -5) {
      leftSpeed = -5;
    }

    double rightSpeed = rightError * 0.1;
    if(rightError < 0.1 && rightError > -0.1) {
      rightSpeed = 0;
    }else if(rightSpeed > 5) {
      rightSpeed = 5;
    }else if(rightSpeed < -5) {
      rightSpeed = 5;
    }
    
    driveBase.autoManualDrive(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.autoManualDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(leftError < 0.1 && leftError > -0.1 && rightError < 0.1 && rightError > -0.1){
      return true;
    }else{
      return false;
    }
  }
}
