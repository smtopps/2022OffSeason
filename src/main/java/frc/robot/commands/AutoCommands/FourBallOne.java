// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.RunIntake;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallOne extends SequentialCommandGroup {
  /** Creates a new FourBallAuto. */
  public FourBallOne(DriveBase driveBase, Intake intake, Shooter shooter, Limelight limelight, Turret turret, Feeder feeder, double waitTime) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new WaitCommand(waitTime),
      new DriveByDistanceBasic(driveBase, 10, 0.4, 0.4),
      new ShootBalls(shooter, turret, limelight, feeder),
      new ParallelRaceGroup(
        new RunIntake(intake),
        new DriveByDistanceBasic(driveBase, 6, 0.2, 0.2)
      ),
      new ShootBalls(shooter, turret, limelight, feeder),
      new TurnByAngleBasic(driveBase, 75, 0.2, 0.2),
      new ParallelDeadlineGroup(
        new SequentialCommandGroup(
          new WaitCommand(1),
          new ParallelRaceGroup(
            new WaitCommand(1),
            new RunIntake(intake)
            )
          ),
        new DriveByDistanceBasic(driveBase, 30, 0.5, 0.5)
      ),
      new TurnByAngleBasic(driveBase, 140, 0.5, 0.5),
      new DriveByDistanceBasic(driveBase, 20, 0.5, 0.5),
      new ShootBalls(shooter, turret, limelight, feeder)
    );
  }
}
