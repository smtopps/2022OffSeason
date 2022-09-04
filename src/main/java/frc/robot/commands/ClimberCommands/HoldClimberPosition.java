package frc.robot.commands.ClimberCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class HoldClimberPosition extends CommandBase {
  private final Climber climber;
  private double setpoint;
  private final PIDController leftPidController = new PIDController(0.75, 0, 0);
  private final PIDController rightPidController = new PIDController(0.75, 0, 0);

  public HoldClimberPosition(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    if(climber.leftClimberEncoder() < climber.rightClimberEncoder()) {
      setpoint = climber.leftClimberEncoder();
    }else{
      setpoint = climber.rightClimberEncoder();
    }

    leftPidController.setSetpoint(setpoint);
    rightPidController.setSetpoint(setpoint);
    leftPidController.setTolerance(1);
    rightPidController.setTolerance(1);
  }

  @Override
  public void execute() {
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
    return false;
  }
}
