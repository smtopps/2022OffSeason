package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class RetractClimberToPosition extends CommandBase {
  private final Climber climber;
  private final double setpoint;

  public RetractClimberToPosition(Climber climber, double setpoint) {
    this.climber = climber;
    this.setpoint = setpoint;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double leftError = climber.leftClimberEncoder() - climber.rightClimberEncoder();
    double rightError = climber.rightClimberEncoder() - climber.leftClimberEncoder();
    double leftOffset = leftError * Constants.CLIMBER_RETRACTION_ERROR;
    double rightOffset = rightError * Constants.CLIMBER_RETRACTION_ERROR;

    if(climber.leftClimberEncoder() < setpoint) {
      climber.leftClimberStop();
    }else{
      climber.leftClimberSpeed(-10-leftOffset);
    }

    if(climber.rightClimberEncoder() < setpoint) {
      climber.rightClimberStop();
    }else{
      climber.rightClimberSpeed(-10-rightOffset);
    }
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  @Override
  public boolean isFinished() {
    if(climber.leftClimberEncoder() < setpoint && climber.rightClimberEncoder() < setpoint) {
      return true;
    }else{
      return false;
    }
  }
}
