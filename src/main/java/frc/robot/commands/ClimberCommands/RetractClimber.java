// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class RetractClimber extends CommandBase {
  public final Climber climber;
  /** Creates a new RetractClimber. */
  public RetractClimber(Climber climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.leftClimberSpeed(-10);
    climber.rightClimberSpeed(10);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.resetEncoders();
    climber.leftClimberStop();
    climber.rightClimberStop();
    ClimbToTopGroup.ClimberBar ++;
    SmartDashboard.putNumber("Climber Bar", ClimbToTopGroup.ClimberBar);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Timer.delay(0.05);
    if (climber.leftClimberVelocity() < 1 && climber.rightClimberVelocity() < 1) {
      return true;
    }else{
      return false;
    }
  }
}
