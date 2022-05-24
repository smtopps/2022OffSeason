package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendClimber extends CommandBase {
  public double Setpoint;
  public final Climber climber;

  public ExtendClimber(Climber climber, double Setpoint) {
    this.climber = climber;
    this.Setpoint = Setpoint;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double leftError = Setpoint - climber.leftClimberEncoder();
    double rightError = -Setpoint - climber.rightClimberEncoder();
    double leftSpeed = leftError * 0.6;
    if(leftSpeed < 1){
      leftSpeed = 1;
    }
    double rightSpeed = rightError * 0.6;
    if(rightSpeed > -1){
      rightSpeed = -1;
    }
    
    if(climber.leftClimberEncoder() < Setpoint) {
      climber.leftClimberSpeed(leftSpeed);
    }else{
      climber.leftClimberStop();
    }
    if(climber.rightClimberEncoder() > -Setpoint) {
      climber.rightClimberSpeed(rightSpeed);
    }else{
      climber.rightClimberStop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  @Override
  public boolean isFinished() {
    if(climber.leftClimberEncoder() < Setpoint && climber.rightClimberEncoder() > -Setpoint) {
      return false;
    }else{
      return true;
    }
  }
}
