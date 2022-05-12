package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterNew;

public class IdleShooter extends CommandBase {
  public final ShooterNew shooterNew;

  public IdleShooter(ShooterNew shooterNew) {
    this.shooterNew = shooterNew;
    addRequirements(shooterNew);
  }

  @Override
  public void initialize() {
    shooterNew.flywheelSpeed(1250);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    shooterNew.flywheelStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
