// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Pigeon2Subsystem;

public class AngleClimberUpGryo extends CommandBase {
  Climber climber;
  Pigeon2Subsystem pigeon2Subsystem;
  boolean rising = false;
  double previousAngle;
  boolean done = false;
  /** Creates a new AngleClimberUpGryo. */
  public AngleClimberUpGryo(Climber climber, Pigeon2Subsystem pigeon2Subsystem) {
    this.climber = climber;
    this.pigeon2Subsystem = pigeon2Subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(pigeon2Subsystem.getRobotAngle() > 0) {
      climber.angleClimberUp();
    }

    /*if(pigeon2Subsystem.getRobotAngle() > previousAngle) {
      rising = true;
    }else{
      rising = false;
    }

    if(pigeon2Subsystem.getRobotAngle() > 0 && rising == true) {
      climber.angleClimberUp();
      done = true;
    }

    previousAngle = pigeon2Subsystem.getRobotAngle();*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*if(done = true) {
      return true;
    }else{
      return false;
    }*/
    return true;
  }
}
