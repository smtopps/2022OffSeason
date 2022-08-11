// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TrajectoryAuto;

import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.IntakeCommands.RunIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake.IntakePosition;
import frc.robot.commands.SettingsCommands.EnableColorSensor;
import frc.robot.commands.SettingsCommands.EnableColorSensor.ColorSensorState;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pigeon2Subsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBall extends SequentialCommandGroup {
  /** Creates a new BasicTest. */
  public FourBall(DriveBase driveBase, Intake intake, Shooter shooter, Turret turret, Limelight limelight, Feeder feeder, Pigeon2Subsystem pigeon2Subsystem, double waitTime) {
    Trajectory trajectory1 = PathPlanner.loadPath("4BallPt1", 2, 3, false);
    Trajectory trajectory2 = PathPlanner.loadPath("4BallPt2", 3, 3, false);
    Trajectory trajectory3 = PathPlanner.loadPath("4BallPt3", 3, 3, true);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new WaitCommand(waitTime),
      new EnableColorSensor(ColorSensorState.DISSABLED),
      new ResetOdometry(driveBase, trajectory1),
      new ToggleIntake(intake, IntakePosition.LOWERED),
      new WaitCommand(0.5),
      new ParallelRaceGroup(
        new RunIntake(intake, 0.7),
        new PrepareBallsInFeeder(feeder),
        driveBase.createCommandForTrajectory(trajectory1)
      ),
      new WaitCommand(0.5),
      new ShootBalls(shooter, turret, limelight, feeder),
      new ParallelRaceGroup(
        new RunIntake(intake, 0.7),
        new PrepareBallsInFeeder(feeder),
        new SequentialCommandGroup(
          driveBase.createCommandForTrajectory(trajectory2),
          new WaitCommand(2)
        )
      ),
      new ToggleIntake(intake, IntakePosition.RAISED),
      driveBase.createCommandForTrajectory(trajectory3),
      new ShootBalls(shooter, turret, limelight, feeder),
      new EnableColorSensor(ColorSensorState.ENABLED)
    );
  }
}
