package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class LowGoalShoot extends CommandBase {
  Feeder feeder;
  int counter = 0;

  public LowGoalShoot(Feeder feeder) {
    this.feeder = feeder;
    addRequirements(feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    feeder.feederSpeed(-8);
    counter++;
  }

  @Override
  public void end(boolean interrupted) {
    feeder.feederStop();
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
