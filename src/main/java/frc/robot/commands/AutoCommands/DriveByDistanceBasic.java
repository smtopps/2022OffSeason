// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveByDistanceBasic extends CommandBase {
  DriveBase driveBase;
  double distance;
  /** Creates a new DriveByDistanceBasic. */
  public DriveByDistanceBasic(DriveBase driveBase, double distance) {
    this.driveBase = driveBase;
    this.distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("DriveByDistance", true);
    driveBase.resetEncoderPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftError = distance - driveBase.getLeftEncoderDistance();
    double speed = leftError * 0.05;

    if(speed > 0.4) {
      speed = 0.4;
    }else if(speed < -0.4) {
      speed = -0.4;
    }else if(speed < 0.1 && speed > 0){
      speed = 0.1;
    }else if(speed > -0.1 && speed < 0){
      speed = -0.1;
    }

    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("error", leftError);
    driveBase.autoArcadedrive(speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("DriveByDistance", false);
    driveBase.autoArcadedrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((Math.abs(driveBase.getLeftEncoderDistance())) > (Math.abs(distance))) {
      return true;
    }else{
      return false;
    }
  }
}
