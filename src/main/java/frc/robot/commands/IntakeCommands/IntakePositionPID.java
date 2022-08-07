// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakePositionPID extends CommandBase {
  Intake intake;
  PIDController positionController = new PIDController(0.0001, 0, 0);
  double setpoint = 0;
  boolean runIntake;
  /** Creates a new IntakePositionPID. */
  public IntakePositionPID(Intake intake, boolean runIntake) {
    this.intake = intake;
    this.runIntake = runIntake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    positionController.setTolerance(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double setpoint;
    if(runIntake){
      setpoint = 100;
      intake.intakeSpeed(1);
    }else{
      setpoint = 0;
      intake.intakeStop();
    }
    positionController.setSetpoint(setpoint);
    intake.intakeRotationSpeed(positionController.calculate(intake.getIntakeRotationPosition()));
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
}
