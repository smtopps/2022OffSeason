// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class TurnByAngleBasic extends CommandBase {
  DriveBase driveBase;
  double angle;
  double distance;
  double maxSpeed;
  double rampRate;
  /** Creates a new DriveByDistanceBasic. */
  public TurnByAngleBasic(DriveBase driveBase, double angle, double maxSpeed, double rampRate) {
    this.driveBase = driveBase;
    this.angle = angle;
    this.maxSpeed = maxSpeed;
    this.rampRate = rampRate;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("TurnByAngle", true);
    driveBase.setneutralmode("Brake");
    driveBase.setdeadband(0.0);
    driveBase.setRampRate(rampRate);
    driveBase.resetEncoderPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = ((Math.PI*22) / 360) *angle;
    double leftError = distance - driveBase.getLeftEncoderDistance();
    double speed = leftError * 0.022;

    if(speed > maxSpeed) {
      speed = maxSpeed;
    }else if(speed < -maxSpeed) {
      speed = -maxSpeed;
    }else if(speed < 0.07 && speed > 0){
      speed = 0.07;
    }else if(speed > -0.07 && speed < 0){
      speed = -0.07;
    }

    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("error", leftError);
    driveBase.autoArcadedrive(0, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("TurnByAngle", false);
    driveBase.autoArcadedrive(0, 0);
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
