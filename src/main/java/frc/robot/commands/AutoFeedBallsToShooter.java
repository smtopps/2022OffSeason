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
    addRequirements(limelight);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    limelight.getTV();
    limelight.getTX();
    limelight.getTY();
    if(RevShooter.isShooterAtSpeed == true && limelight.isTurretAligned() == true) {
      feeder.feederSpeed(12);
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
