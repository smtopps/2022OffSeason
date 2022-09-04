// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.ShooterCommands.IdleShooter.IdleShooterState;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class LowGoalShoot extends ParallelRaceGroup {
  public LowGoalShoot(Feeder feeder, Shooter shooter, Limelight limelight) {
    addCommands(
      new LowGoalFeeder(feeder),
      new IdleShooter(shooter, limelight, IdleShooterState.Running)
    );
  }
}
