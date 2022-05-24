package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;

public class FeedBallsToShooter extends CommandBase {
  public final Limelight limelight;
  public final Feeder feeder;
  int counter = 0;
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
    /*if(RevShooter.FlywheelAtSpeed == true && AlignTurret.TurretAligned == true) {
      feeder.feederSpeed(-8);
      SmartDashboard.putBoolean("Shooting", true);
      shooting = true;
      counter ++;
    }else{
      feeder.feederStop();
      SmartDashboard.putBoolean("Shooting", false);
      shooting = false;
      counter = 0;
    }*/

    if(RevShooter.FlywheelAtSpeed == true && AlignTurret.TurretAligned == true) {

      if(RobotContainer.shootOpponentsBalls == false) {
        if(feeder.isBallInFeeder() && feeder.isBallRightColor() == false){
          AlignTurret.turretOffset = -2;
          feeder.feederStop();
          SmartDashboard.putBoolean("Shooting", false);
          shooting = false;
          counter++;
        }else{
          AlignTurret.turretOffset = 0;
          feeder.feederSpeed(-8);
          SmartDashboard.putBoolean("Shooting", true);
          shooting = true;
          counter ++;
        }
      }else{
        AlignTurret.turretOffset = 0;
        feeder.feederSpeed(-8);
        SmartDashboard.putBoolean("Shooting", true);
        shooting = true;
        counter ++;
      }
    }else{
      feeder.feederStop();
      SmartDashboard.putBoolean("Shooting", false);
      shooting = false;
      counter = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    AlignTurret.turretOffset = 0;
    feeder.feederStop();
    SmartDashboard.putBoolean("Shooting", false);
    counter = 0;
  }

  @Override
  public boolean isFinished() {
    if(counter < 50*2.5){
      return false;
    }else{
      return true;
    }
  }
}
