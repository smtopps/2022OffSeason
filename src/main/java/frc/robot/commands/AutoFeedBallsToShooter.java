package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;

public class AutoFeedBallsToShooter extends CommandBase {
  public final Limelight limelight;
  public final Feeder feeder;

  public AutoFeedBallsToShooter(Limelight limelight, Feeder feeder) {
    this.limelight = limelight;
    this.feeder = feeder;

    addRequirements(feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(RevShooterNew.FlywheelAtSpeed == true && limelight.isTurretAligned()) {
      feeder.feederSpeed(-8);
    }else{
      feeder.feederStop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    feeder.feederStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
