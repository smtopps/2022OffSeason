// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class HoldClimberPosition extends CommandBase {
  private final Climber climber;
  private double rightSetpoint;
  private double leftSetpoint;
  /** Creates a new HoldClimberPosition. */
  public HoldClimberPosition(Climber climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rightSetpoint = climber.rightClimberEncoder();
    leftSetpoint = climber.leftClimberEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climber.leftClimberEncoder() < leftSetpoint) {
      climber.leftClimberStop();
    }else{
      climber.leftClimberSpeed(-5);
    }

    if(climber.rightClimberEncoder() < rightSetpoint) {
      climber.rightClimberStop();
    }else{
      climber.rightClimberSpeed(-5);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
