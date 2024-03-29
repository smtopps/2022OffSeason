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

  public KillEverything(DriveBase driveBase, Climber climber, Feeder feeder, Intake intake, Shooter shooter, Turret turret) {
    this.driveBase = driveBase;
    this.climber = climber;
    this.feeder = feeder;
    this.intake = intake;
    this.shooter = shooter;
    this.turret = turret;
    addRequirements(driveBase, climber, feeder, intake, shooter, turret);
  }

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

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
