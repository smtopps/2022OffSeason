package frc.robot.commands.ClimberCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendClimber extends CommandBase {
  public final double setpoint;
  public final Climber climber;
  private final PIDController leftPidController = new PIDController(-1, 0, 0);
  private final PIDController rightPidController = new PIDController(-1, 0, 0);

  public ExtendClimber(Climber climber, double setpoint) {
    this.climber = climber;
    this.setpoint = setpoint;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    leftPidController.setSetpoint(setpoint);
    rightPidController.setSetpoint(setpoint);
    leftPidController.setTolerance(1);
    rightPidController.setTolerance(1);
  }

  @Override
  public void execute() {
    /*double leftError = setpoint - climber.leftClimberEncoder();
    double rightError = setpoint - climber.rightClimberEncoder();
    double leftSpeed = leftError * 0.6;
    if(leftSpeed < 1){
      leftSpeed = 1;
    }
    double rightSpeed = rightError * 0.6;
    if(rightSpeed < 1){
      rightSpeed = 1;
    }

    SmartDashboard.putNumber("leftSpeed", leftSpeed);
    SmartDashboard.putNumber("rightSpeed", rightSpeed);
    
    if(climber.leftClimberEncoder() < setpoint) {
      climber.leftClimberSpeed(leftSpeed);
    }else{
      climber.leftClimberStop();
    }
    if(climber.rightClimberEncoder() < setpoint) {
      climber.rightClimberSpeed(rightSpeed);
    }else{
      climber.rightClimberStop();
    }*/

    climber.leftClimberSpeed(leftPidController.calculate(climber.leftClimberEncoder()));
    climber.rightClimberSpeed(rightPidController.calculate(climber.rightClimberEncoder()));
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  @Override
  public boolean isFinished() {
    /*if(climber.leftClimberEncoder() < setpoint && climber.rightClimberEncoder() < setpoint) {
      return false;
    }else{
      return true;
    }*/

    if(leftPidController.atSetpoint() && rightPidController.atSetpoint()) {
      return true;
    }else{
      return false;
    }
  }
}
