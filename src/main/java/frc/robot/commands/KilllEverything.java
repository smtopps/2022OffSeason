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

  public KilllEverything(Climber climber, DriveBase driveBase, Feeder feeder, Intake intake, Limelight limelight, Shooter shooter, Turret turret) {
    this.climber = climber;
    this.driveBase = driveBase;
    this.feeder = feeder;
    this.intake = intake;
    this.limelight = limelight;
    this.shooter = shooter;
    this.turret = turret;
    addRequirements(climber, driveBase, feeder, intake, limelight, shooter, turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.rightClimberStop();
    climber.leftClimberStop();
    driveBase.autoArcadeStop();
    feeder.feederStop();
    intake.intakeStop();
    shooter.stopMotors();
    turret.stopTurret();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
