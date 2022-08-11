// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableLimelight extends CommandBase {
  /** Creates a new ToggleShooterIdle. */
  private final LimelightState limelightState;
  public EnableLimelight(LimelightState limelightState) {
    this.limelightState = limelightState;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(limelightState == LimelightState.TOGGLE) {
      RobotContainer.enableLimelight = !RobotContainer.enableLimelight;
    }else if(limelightState == LimelightState.DISSABLED) {
      RobotContainer.enableLimelight = false;
    }else if(limelightState == LimelightState.ENABLED) {
      RobotContainer.enableLimelight = true;
    }
    //SmartDashboard.putBoolean("Enable Limelight", RobotContainer.enableLimelight);
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

  public enum LimelightState {
    DISSABLED, ENABLED, TOGGLE
  }
}
