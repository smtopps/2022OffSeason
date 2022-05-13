// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveByDistance extends CommandBase {
  DriveBase driveBase;
  private double Setpoint;
  private double leftError;
  private double rightError;
  /** Creates a new DriveByDistance. */
  public DriveByDistance(DriveBase driveBase, double Setpoint) {
    this.driveBase = driveBase;
    this.Setpoint = Setpoint;
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
    leftError = Setpoint - driveBase.getLeftEncoderDistance();
    rightError = Setpoint - driveBase.getRightEncoderDistance();

    double leftSpeed = leftError * 0.1;
    /*if (leftError < 0.1 && leftError > -0.1) {
      leftSpeed = 0;
    }else */if(leftSpeed > 5) {
      leftSpeed = 5;
    }else if(leftSpeed < -5) {
      leftSpeed = -5;
    }/*else if(leftSpeed < 1 && leftSpeed > 0){
      leftSpeed = 1;
    }else if(leftSpeed > -1 && leftSpeed < 0) {
      leftSpeed = -1;
    }*/

    double rightSpeed = rightError * 0.1;
    /*if(rightError < 0.1 && rightError > -0.1) {
      rightSpeed = 0;
    }else */if(rightSpeed > 5) {
      rightSpeed = 5;
    }else if(rightSpeed < -5) {
      rightSpeed = -5;
    }/*else if(rightSpeed < 1 && rightSpeed > 0){
      rightSpeed = 1;
    }else if(rightSpeed > -1 && rightSpeed < 0) {
      rightSpeed = -1;
    }*/
    
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