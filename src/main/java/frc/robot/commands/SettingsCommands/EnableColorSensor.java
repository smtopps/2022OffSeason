// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableColorSensor extends CommandBase {
  /** Creates a new ToggleGrabOponentBalls. */
  int Toggle;
  public EnableColorSensor(int OneFalseTwoTrueThreeToggle) {
    this.Toggle = OneFalseTwoTrueThreeToggle;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(Toggle == 3) {
      RobotContainer.enableColorSensor = !RobotContainer.enableColorSensor;
    }else if(Toggle == 1) {
      RobotContainer.enableColorSensor = false;
    }else if(Toggle == 2) {
      RobotContainer.enableColorSensor = true;
    }
    //SmartDashboard.putBoolean("Enable Color Sensor", RobotContainer.enableColorSensor);
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
}
