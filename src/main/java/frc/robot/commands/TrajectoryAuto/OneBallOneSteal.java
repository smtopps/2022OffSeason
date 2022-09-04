package frc.robot.commands.TrajectoryAuto;

import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeCommands.IntakePositionPID;
import frc.robot.commands.IntakeCommands.ZeroIntake;
import frc.robot.commands.IntakeCommands.IntakePositionPID.IntakingState;
import frc.robot.commands.SettingsCommands.EnableColorSensor;
import frc.robot.commands.SettingsCommands.EnableColorSensor.ColorSensorState;
import frc.robot.commands.ShooterCommands.LowGoalShoot;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pigeon2Subsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class OneBallOneSteal extends SequentialCommandGroup {
  public OneBallOneSteal(DriveBase driveBase, Intake intake, Shooter shooter, Turret turret, Limelight limelight, Feeder feeder, Pigeon2Subsystem pigeon2Subsystem, double waitTime) {
    Trajectory trajectory1 = PathPlanner.loadPath("1Ball1StealPt1", 1.5, 2, false);
    Trajectory trajectory2 = PathPlanner.loadPath("1Ball1StealPt2", 1.5, 2, false);

    addCommands(
      new WaitCommand(waitTime),
      //new EnableColorSensor(ColorSensorState.DISSABLED),
      new ZeroIntake(intake),
      new ResetOdometry(driveBase, trajectory1),
      driveBase.createCommandForTrajectory(trajectory1),
      new ShootBalls(shooter, turret, limelight, feeder, false),
      new ParallelRaceGroup(
        new IntakePositionPID(intake, IntakingState.INTAKE),
        driveBase.createCommandForTrajectory(trajectory2)
      ),
      new LowGoalShoot(feeder, shooter, limelight),
      new EnableColorSensor(ColorSensorState.ENABLED)
    );
  }
}
