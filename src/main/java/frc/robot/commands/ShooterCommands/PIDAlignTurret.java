// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class PIDAlignTurret extends CommandBase {
  public static boolean TurretAligned = false;
  public final Turret turret;
  public final Limelight limelight;
  PIDController turretPID;
  
  /** Creates a new PIDAlignTurret. */
  public PIDAlignTurret(Turret turret, Limelight limelight) {
    this.turret = turret;
    this.limelight = limelight;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turretPID = new PIDController(0.4, 0, 0);
    turretPID.setTolerance(1.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turretPID.setSetpoint(0 + FeedBallsToShooter.turretOffset);
    double turretPosition = limelight.getTX();
    double turretSpeed = turretPID.calculate(turretPosition);
    turret.turretSpeed(turretSpeed);
    if(turretPID.atSetpoint()) {
      TurretAligned = true;
    }else{
      TurretAligned = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.stopTurret();
    TurretAligned = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
