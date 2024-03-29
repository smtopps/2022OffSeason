package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;

public class FeedBallsToShooter extends CommandBase {
  public final Limelight limelight;
  public final Feeder feeder;
  public static int counter = 0;
  public static boolean shooting = false;
  public static final double feederSpeed = -5;
  public static double turretOffset = 0;
  public static double shooterOffset = 0;

  public FeedBallsToShooter(Limelight limelight, Feeder feeder) {
    this.limelight = limelight;
    this.feeder = feeder;

    addRequirements(feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(RevShooter.FlywheelAtSpeed == true && AlignTurret.TurretAligned == true) {

      if(RobotContainer.enableColorSensor == true) {
        if(feeder.isBallInFeeder() && feeder.isBallRightColor() == false){
          turretOffset = Constants.turretOffestForOpponentsBall;
          shooterOffset = Constants.shooterOffsetForOpponentsBall;
          feeder.feederSpeed(feederSpeed);
          SmartDashboard.putBoolean("Shooting", false);
          shooting = false;
          counter++;
        }else{
          feeder.feederSpeed(feederSpeed);
          SmartDashboard.putBoolean("Shooting", true);
          shooting = true;
          counter ++;
        }
      }else{
        feeder.feederSpeed(feederSpeed);
        SmartDashboard.putBoolean("Shooting", true);
        shooting = true;
        counter ++;
        turretOffset = 0;
        shooterOffset = 0;
      }
    }else{
      if(RobotContainer.enableColorSensor == true) {
        if(feeder.isBallInFeeder() && feeder.isBallRightColor() == false) {
          turretOffset = Constants.turretOffestForOpponentsBall;
          shooterOffset = Constants.shooterOffsetForOpponentsBall;
        }
  
        feeder.feederStop();
        SmartDashboard.putBoolean("Shooting", false);
        shooting = false;
        counter = 0;
      }else{
        turretOffset = 0;
        shooterOffset = 0;
        feeder.feederStop();
        SmartDashboard.putBoolean("Shooting", false);
        shooting = false;
        counter = 0;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    turretOffset = 0;
    shooterOffset = 0;
    feeder.feederStop();
    SmartDashboard.putBoolean("Shooting", false);
    counter = 0;
  }

  @Override
  public boolean isFinished() {
    if(counter < 50*1.0){ //was 2.5 seconds
      return false;
    }else{
      return true;
    }
  }
}
