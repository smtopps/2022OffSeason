package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class IdleShooter extends CommandBase {
  public final Shooter shooter;

  public IdleShooter(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    if(RobotContainer.stopShooterSystem == false){
      shooter.setRampRate(0.5);
      shooter.setFlywheelRPM(-1250);
    }else{
      shooter.stopMotors();
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    shooter.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
