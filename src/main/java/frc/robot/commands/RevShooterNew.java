// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.ShooterNew;

public class RevShooterNew extends CommandBase {
  public static boolean FlywheelAtSpeed = false;
  private final ShooterNew shooterNew;
  private final Limelight limelight;

  private double RPM = 1250;
  private int Counter = 0;

  double distance = 0;

  /** Creates a new RevShooterNew. */
  public RevShooterNew(ShooterNew shooterNew, Limelight limelight) {
    this.shooterNew = shooterNew;
    this.limelight = limelight;

    addRequirements(shooterNew, limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setLEDMode(3);
    SmartDashboard.putBoolean("RevShooterNew", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = limelight.getTY();
    SmartDashboard.putNumber("ShooterDistance", distance);

    shooterNew.setFlywheelRPM(RPM);

    if(limelight.getTV() == 0){
      RPM = -1250;
    }else{
      RPM = -(3210+(-95.8*limelight.getTY())+2.69*(Math.pow(limelight.getTY(), 2)));
    }

    //shooterNew.setFlywheelRPM(RPM);
    SmartDashboard.putNumber("Set RPM", RPM);


    if(shooterNew.getFlywheelRPM() <= RPM+50 && shooterNew.getFlywheelRPM() >= RPM-50 && Counter < 5) {
      //FlywheelAtSpeed = true;
      Counter++;
    }else if(Counter > 0){
      Counter--;
    }

    if(Counter >=3){
      FlywheelAtSpeed = true;
    }else{
      FlywheelAtSpeed = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    FlywheelAtSpeed = false;
    shooterNew.stopMotors();
    RPM = 1250;
    Counter = 0;
    SmartDashboard.putBoolean("RevShooterNew", false);
    limelight.setLEDMode(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
