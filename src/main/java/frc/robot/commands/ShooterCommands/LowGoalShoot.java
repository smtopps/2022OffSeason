// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.ShooterCommands.IdleShooter.IdleShooterState;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LowGoalShoot extends ParallelRaceGroup {
  /** Creates a new LowGoalShoot. */
  public LowGoalShoot(Feeder feeder, Shooter shooter, Limelight limelight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new LowGoalFeeder(feeder),
      new IdleShooter(shooter, limelight, IdleShooterState.Running)
    );
  }
}
