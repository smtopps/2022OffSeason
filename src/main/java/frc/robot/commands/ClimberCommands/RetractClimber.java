package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class RetractClimber extends CommandBase {
  public final Climber climber;

  public RetractClimber(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.leftClimberSpeed(-10);
    climber.rightClimberSpeed(-10);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    climber.resetEncoders();
    climber.leftClimberStop();
    climber.rightClimberStop();
    ClimbToTopGroup.ClimberBar ++;
    SmartDashboard.putNumber("Climber Bar", ClimbToTopGroup.ClimberBar);
  }

  @Override
  public boolean isFinished() {
    Timer.delay(0.05);
    if (climber.leftClimberVelocity() < 1 && climber.rightClimberVelocity() < 1) {
      return true;
    }else{
      return false;
    }
  }
}
