// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ElevatorProfiledPIDPosition extends CommandBase {
  private final Climber climber;
  double setpoint;
  ProfiledPIDController leftPID;
  ProfiledPIDController rightPID;
  Constraints profiledConstraints;
  SimpleMotorFeedforward leftFeedforward;
  SimpleMotorFeedforward rightFeedforward;

  /** Creates a new ProfiledPIDPosition. */
  public ElevatorProfiledPIDPosition(Climber climber, double setpoint) {
    this.climber = climber;
    this.setpoint = setpoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    profiledConstraints = new Constraints(0.5, 0.5);
    leftPID = new ProfiledPIDController(0.6, 0, 0, profiledConstraints);
    rightPID = new ProfiledPIDController(0.6, 0, 0, profiledConstraints);
    leftPID.setGoal(setpoint);
    rightPID.setGoal(setpoint);
    leftPID.setTolerance(10);
    rightPID.setTolerance(10);
    leftFeedforward = new SimpleMotorFeedforward(0, 0);
    rightFeedforward = new SimpleMotorFeedforward(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftClimberPosition = climber.leftClimberEncoder();
    double rightClimberPosition = climber.rightClimberEncoder();
    double leftSpeed = leftPID.calculate(leftClimberPosition) + leftFeedforward.calculate(20);
    double rightSpeed = rightPID.calculate(rightClimberPosition) + rightFeedforward.calculate(20);
    climber.leftClimberSpeed(leftSpeed);
    climber.rightClimberSpeed(rightSpeed);
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
    if(leftPID.atGoal() && rightPID.atGoal()){
      return true;
    }else{
      return false;
    }
  }
}
