// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class KilllEverything extends CommandBase {
  Climber climber;
  DriveBase driveBase;
  Feeder feeder;
  Intake intake;
  Limelight limelight;
  Shooter shooter;
  Turret turret;
  /** Creates a new KilllEverything. */
  public KilllEverything(Climber climber, DriveBase driveBase, Feeder feeder, Intake intake, Limelight limelight, Shooter shooter, Turret turret) {
    this.climber = climber;
    this.driveBase = driveBase;
    this.feeder = feeder;
    this.intake = intake;
    this.limelight = limelight;
    this.shooter = shooter;
    this.turret = turret;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber, driveBase, feeder, intake, limelight, shooter, turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
