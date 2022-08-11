// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class RetractClimberToPosition extends CommandBase {
  private final Climber climber;
  private final double setpoint;
  /** Creates a new RetractClimberToPosition. */
  public RetractClimberToPosition(Climber climber, double setpoint) {
    this.climber = climber;
    this.setpoint = setpoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
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

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(climber.leftClimberEncoder() < setpoint && climber.rightClimberEncoder() < setpoint) {
      return true;
    }else{
      return false;
    }
  }
}
