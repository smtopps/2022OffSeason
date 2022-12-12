package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class IdleShooter extends CommandBase {
  public final Shooter shooter;
  public final Limelight limelight;
  public final IdleShooterState idleShooterState;

  public IdleShooter(Shooter shooter, Limelight limelight, IdleShooterState idleShooterState) {
    this.shooter = shooter;
    this.limelight = limelight;
    this.idleShooterState = idleShooterState;
    addRequirements(shooter);
    addRequirements(limelight);
  }

  @Override
  public void initialize() {
    //limelight.setLEDMode(1);
    //limelight.setCameraMode(1);
    limelight.setStream(2);
  }

  @Override
  public void execute() {
    if(RobotContainer.enableIdle == true && idleShooterState == IdleShooterState.Idle){
      shooter.setFlywheelRPM(-1250);
    }else if(idleShooterState == IdleShooterState.Running) {
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

  public enum IdleShooterState{
    Running, Idle
  }
}
