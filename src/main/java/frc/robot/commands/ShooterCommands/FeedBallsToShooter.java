package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;

public class FeedBallsToShooter extends CommandBase {
  public final Limelight limelight;
  public final Feeder feeder;
  int counter;
  boolean shooting = false;

  public FeedBallsToShooter(Limelight limelight, Feeder feeder) {
    this.limelight = limelight;
    this.feeder = feeder;

    addRequirements(feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(RevShooter.FlywheelAtSpeed == true && /*limelight.isTurretAligned()*/ AlignTurret.TurretAligned == true) {
      feeder.feederSpeed(-8);
      SmartDashboard.putBoolean("Shooting", true);
      shooting = true;
      counter ++;
    }else{
      feeder.feederStop();
      SmartDashboard.putBoolean("Shooting", false);
      shooting = false;
      counter = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    feeder.feederStop();
    SmartDashboard.putBoolean("Shooting", false);
    counter = 0;
  }

  @Override
  public boolean isFinished() {
    /*if(counter < 50*5){
      return false;
    }else{
      return true;
    }*/
    /*if(shooting == true){
      Timer.delay(5);
      return true;
    }else{
      return false;
    }*/
    return false;
  }
}
