package frc.robot.commands.TrajectoryAuto;

import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.IntakeCommands.IntakePositionPID;
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

public class TwoBall extends SequentialCommandGroup {
  public TwoBall(DriveBase driveBase, Intake intake, Shooter shooter, Feeder feeder, Limelight limelight, Pigeon2Subsystem pigeon2Subsystem, Turret turret, double waitTime) {
    Trajectory trajectory1 = PathPlanner.loadPath("2BallPt1", 1.5, 2, false);

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
      new EnableColorSensor(ColorSensorState.ENABLED)
    );
  }
}
