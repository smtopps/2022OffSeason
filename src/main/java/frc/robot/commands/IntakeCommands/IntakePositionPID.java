// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakePositionPID extends CommandBase {
  Intake intake;
  IntakingState intakingState;
  PIDController positionController = new PIDController(0.5, 0, 0);
  double setpoint =-10.8;
  /** Creates a new IntakePositionPID. */
  public IntakePositionPID(Intake intake, IntakingState intakingState) {
    this.intake = intake;
    this.intakingState = intakingState;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    positionController.setSetpoint(setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(intake.getIntakeRotationPosition() < positionController.getSetpoint()) {
      positionController.setP(8);
    }else{
      positionController.setP(0.6);
    }

    intake.intakeRotationSpeed(positionController.calculate(intake.getIntakeRotationPosition()));
    if(intakingState == IntakingState.INTAKE){
      intake.intakeSpeed(0.75);
    }else{
      intake.intakeSpeed(-0.75);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.intakeStop();
    intake.intakeRotationStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum IntakingState {
    INTAKE, OUTTAKE
  }
}
