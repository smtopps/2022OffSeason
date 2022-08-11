package frc.robot.commands.ShooterCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class ManualTurretControl extends CommandBase {
  private final Turret turret;
  private DoubleSupplier rightXJoystick;

  public ManualTurretControl(Turret turret, DoubleSupplier rightXJoystick) {
    this.turret = turret;
    this.rightXJoystick = rightXJoystick;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turret.turretSpeed(MathUtil.applyDeadband(rightXJoystick.getAsDouble(), 0.02)*0.3);
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
