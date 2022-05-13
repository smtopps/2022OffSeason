// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendClimberToMidBar extends CommandBase {
  public double Setpoint = 67.6;
  public final Climber climber;
  /** Creates a new ExtendClimber. */
  public ExtendClimberToMidBar(Climber climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
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

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(climber.leftClimberEncoder() < Setpoint && climber.rightClimberEncoder() > -Setpoint) {
      return false;
    }else{
      return true;
    }
  }
}
