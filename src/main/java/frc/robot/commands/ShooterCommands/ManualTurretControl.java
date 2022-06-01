package frc.robot.commands.ShooterCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class ManualTurretControl extends CommandBase {
  Turret turret;
  DoubleSupplier rightXJoystick;

  public ManualTurretControl(Turret turret, DoubleSupplier rightXJoystick) {
    this.turret = turret;
    this.rightXJoystick = rightXJoystick;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turret.turretSpeed((rightXJoystick.getAsDouble())*0.3);
  }

  @Override
  public void end(boolean interrupted) {
    turret.stopTurret();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
