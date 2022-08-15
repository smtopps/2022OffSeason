// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class HoldClimberPosition extends CommandBase {
  private final Climber climber;
  private double setpoint;
  private final PIDController leftPidController = new PIDController(-1, 0, 0);
  private final PIDController rightPidController = new PIDController(-1, 0, 0);
  /** Creates a new HoldClimberPosition. */
  public HoldClimberPosition(Climber climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
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

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.leftClimberSpeed(leftPidController.calculate(climber.leftClimberEncoder()));
    climber.rightClimberSpeed(rightPidController.calculate(climber.rightClimberEncoder()));
    /*if(climber.leftClimberEncoder() < leftSetpoint) {
      climber.leftClimberStop();
    }else{
      climber.leftClimberSpeed(-5);
    }

    if(climber.rightClimberEncoder() < rightSetpoint) {
      climber.rightClimberStop();
    }else{
      climber.rightClimberSpeed(-5);
    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
