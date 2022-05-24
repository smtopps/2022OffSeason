package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;

public class PrepareBallsInFeeder extends CommandBase {
  public final Feeder feeder;

  public PrepareBallsInFeeder(Feeder feeder) {
    this.feeder = feeder;
    addRequirements(feeder);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    /*
     * if(feeder.isBallInFeeder() == true) {
     * if(feeder.isBallRightColor()){
     * feeder.feederStop();
     * }else{
     * feeder.feederSpeed(-2.5);
     * }
     * }else{
     * feeder.feederSpeed(-2.5);;
     * }
     */
    if (RobotContainer.stopFeederSystem == false) {
      SmartDashboard.putBoolean("Feeder System Activated", true);
      if (RobotContainer.grabOponentBalls == false) {
        SmartDashboard.putBoolean("Feeder Color Activated", true);
        if (feeder.isBallInFeeder() && feeder.isBallRightColor()) {
          feeder.feederStop();
          SmartDashboard.putBoolean("Ball In Feeder", true);
        } else {
          feeder.feederSpeed(-2.5);
          SmartDashboard.putBoolean("Ball In Feeder", false);
        }
      } else {
        SmartDashboard.putBoolean("Feeder Color Activated", false);
        if (feeder.isBallInFeeder()) {
          feeder.feederStop();
          SmartDashboard.putBoolean("Ball In Feeder", true);
        } else {
          feeder.feederSpeed(-2.5);
          SmartDashboard.putBoolean("Ball In Feeder", false);
        }
      }
    } else {
      SmartDashboard.putBoolean("Feeder System Activated", false);
      feeder.feederStop();
      SmartDashboard.putBoolean("Ball In Feeder", false);
    }
  }

  @Override
  public void end(boolean interrupted) {
    feeder.feederStop();
    SmartDashboard.putBoolean("Ball In Feeder", false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
