// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class RobotInit extends CommandBase {
  Limelight limelight;
  Climber climber;
  Turret turret;
  Boolean done = false;
  /** Creates a new RobotInit. */
  public RobotInit(Limelight limelight, Climber climber, Turret turret) {
    this.limelight = limelight;
    this.climber = climber;
    this.turret = turret;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(limelight, climber, turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setLEDMode(1);
    limelight.setCameraMode(1);
    limelight.setStream(2);
    climber.compressorEnable();
    turret.resetTurretEncoder();
    done = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(done == true){
      return true;
    }else{
      return false;
    }
  }
}
