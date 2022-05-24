package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Turret;

public class ManualTurretControl extends CommandBase {
  Turret turret;
  double rightXJoystick;

  public ManualTurretControl(Turret turret, double rightXJoystick) {
    this.turret = turret;
    this.rightXJoystick = rightXJoystick;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turret.turretSpeed((RobotContainer.operatorController.getRightX())*0.3);
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
