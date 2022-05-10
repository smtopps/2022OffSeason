// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendClimber extends CommandBase {
  public final Climber climber;
  /** Creates a new ExtendClimber. */
  public ExtendClimber(Climber climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ExtendClimber Initialize");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("ExtendClimber Execute");
    double leftError = 20 - climber.leftClimberEncoder();
    double rightError = -20 - climber.rightClimberEncoder();
    double leftSpeed = leftError * 0.1;
    if(leftSpeed > 0.5) {
      leftSpeed = 0.5;
    }
    double rightSpeed = rightError * 0.1;
    if(rightSpeed < -0.5) {
      rightSpeed = -0.5;
    }
    if(climber.leftClimberEncoder() < 20) {
      climber.leftClimberSpeed(leftSpeed);
    }else{
      climber.leftClimberStop();
    }
    if(climber.rightClimberEncoder() > -20) {
      climber.rightClimberSpeed(rightSpeed);
    }else{
      climber.rightClimberStop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ExtendClimber End");
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(climber.leftClimberEncoder() < 20 && climber.rightClimberEncoder() > -20) {
      return false;
    }else{
      return true;
    }
  }
}
