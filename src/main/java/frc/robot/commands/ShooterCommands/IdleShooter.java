package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class IdleShooter extends CommandBase {
  public final Shooter shooter;
  public final Limelight limelight;

  public IdleShooter(Shooter shooter, Limelight limelight) {
    this.shooter = shooter;
    this.limelight = limelight;
    addRequirements(shooter);
    addRequirements(limelight);
  }

  @Override
  public void initialize() {
    limelight.setLEDMode(1);
    limelight.setCameraMode(1);
    limelight.setStream(2);
  }

  @Override
  public void execute() {
    if(RobotContainer.enableIdle == true){
      shooter.setRampRate(0.5);
      shooter.setFlywheelRPM(-1250);
    }else{
      shooter.stopMotors();
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooter.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
