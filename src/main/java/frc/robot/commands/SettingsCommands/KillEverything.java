// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class KillEverything extends CommandBase {
  private final DriveBase driveBase;
  private final Climber climber;
  private final Feeder feeder;
  private final Intake intake;
  private final Shooter shooter;
  private final Turret turret;
  /** Creates a new KillEverything. */
  public KillEverything(DriveBase driveBase, Climber climber, Feeder feeder, Intake intake, Shooter shooter, Turret turret) {
    this.driveBase = driveBase;
    this.climber = climber;
    this.feeder = feeder;
    this.intake = intake;
    this.shooter = shooter;
    this.turret = turret;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase, climber, feeder, intake, shooter, turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveBase.autoArcadeStop();
    climber.leftClimberStop();
    climber.rightClimberStop();
    feeder.feederStop();
    intake.intakeStop();
    intake.intakeRotationStop();
    shooter.stopMotors();
    shooter.setHoodPosition(false);
    turret.stopTurret();
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
    return false;
  }
}
