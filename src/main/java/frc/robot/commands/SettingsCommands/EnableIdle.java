// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableIdle extends CommandBase {
  /** Creates a new ToggleShootOponentBalls. */
  private final IdleState idleState;
  public EnableIdle(IdleState idleState) {
    this.idleState = idleState;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(idleState == IdleState.TOGGLE) {
      RobotContainer.enableIdle = !RobotContainer.enableIdle;
    }else if(idleState == IdleState.DISSABLED) {
      RobotContainer.enableIdle = false;
    }else if(idleState == IdleState.ENABLED) {
      RobotContainer.enableIdle = true;
    }
    //SmartDashboard.putBoolean("Enable Idle", RobotContainer.enableIdle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }

  public enum IdleState {
    DISSABLED, ENABLED, TOGGLE
  }
}
