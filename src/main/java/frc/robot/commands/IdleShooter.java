package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class IdleShooter extends CommandBase {
  public final Shooter shooter;

  public IdleShooter(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.flywheelSpeed(1250);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    shooter.flywheelStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
