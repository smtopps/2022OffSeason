package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class PrepareBallsInFeeder extends CommandBase {
  public final Feeder feeder;

  public PrepareBallsInFeeder(Feeder feeder) {
    this.feeder = feeder;
    addRequirements(feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    feeder.getBallInFeeder();
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
