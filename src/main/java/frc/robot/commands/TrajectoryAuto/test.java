// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TrajectoryAuto;

import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveBase;

public class Test extends SequentialCommandGroup {
  public Test(DriveBase driveBase) {
    Trajectory trajectory1 = PathPlanner.loadPath("Test", 1.5, 2, false);

    addCommands(
      new ResetOdometry(driveBase, trajectory1),
      driveBase.createCommandForTrajectory(trajectory1)
    );
  }
}
