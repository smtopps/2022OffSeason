package frc.robot.commands.TrajectoryAuto;

import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.IntakeCommands.IntakePositionPID;
import frc.robot.commands.IntakeCommands.RetractIntakeVelocity;
import frc.robot.commands.IntakeCommands.ZeroIntake;
import frc.robot.commands.IntakeCommands.IntakePositionPID.IntakingState;
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

public class FiveBall extends SequentialCommandGroup {
  public FiveBall(DriveBase driveBase, Intake intake, Shooter shooter, Turret turret, Limelight limelight, Feeder feeder, Pigeon2Subsystem pigeon2Subsystem, double waitTime) {
    Trajectory trajectory1 = PathPlanner.loadPath("5BallPt1", 1.5, 2, false);
    Trajectory trajectory2 = PathPlanner.loadPath("5BallPt2", 2, 2, false);
    Trajectory trajectory3 = PathPlanner.loadPath("5BallPt3", 2, 2, false);
    Trajectory trajectory4 = PathPlanner.loadPath("5BallPt4", 0.5, 2, true);
    Trajectory trajectory5 = PathPlanner.loadPath("5BallPt5", 3, 3, true);

    addCommands(
      new WaitCommand(waitTime),
      //new EnableColorSensor(ColorSensorState.DISSABLED),
      new ZeroIntake(intake),
      new ResetOdometry(driveBase, trajectory1),
      new ParallelRaceGroup(
        new IntakePositionPID(intake, IntakingState.INTAKE),
        new PrepareBallsInFeeder(feeder),
        driveBase.createCommandForTrajectory(trajectory1)
      ),
      new ShootBalls(shooter, turret, limelight, feeder, false),
      new ParallelRaceGroup(
        new IntakePositionPID(intake, IntakingState.INTAKE),
        new PrepareBallsInFeeder(feeder),
        driveBase.createCommandForTrajectory(trajectory2)
      ),
      new ShootBalls(shooter, turret, limelight, feeder, false),
      new ParallelRaceGroup(
        new IntakePositionPID(intake, IntakingState.INTAKE),
        new PrepareBallsInFeeder(feeder),
        new SequentialCommandGroup(
          driveBase.createCommandForTrajectory(trajectory3),
          driveBase.createCommandForTrajectory(trajectory4),
          new WaitCommand(0.5)
        )
      ),
      new ParallelRaceGroup(
        driveBase.createCommandForTrajectory(trajectory5),
        new RetractIntakeVelocity(intake)
      ),
      new ShootBalls(shooter, turret, limelight, feeder, false),
      new EnableColorSensor(ColorSensorState.ENABLED)
    );
  }
}
