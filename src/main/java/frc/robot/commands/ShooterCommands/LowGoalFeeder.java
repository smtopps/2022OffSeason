package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class LowGoalFeeder extends CommandBase {
  private final Feeder feeder;
  private double initialTime;

  public LowGoalFeeder(Feeder feeder) {
    this.feeder = feeder;
    addRequirements(feeder);
  }

  @Override
  public void initialize() {
    initialTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    feeder.feederSpeed(-8);
  }

  @Override
  public void end(boolean interrupted) {
    feeder.feederStop();
  }

  @Override
  public boolean isFinished() {
    if(Timer.getFPGATimestamp()-initialTime >= 2.5) {
      return true;
    }else{
      return false;
    }
  }
}
