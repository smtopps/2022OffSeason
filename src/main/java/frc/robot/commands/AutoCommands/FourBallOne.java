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

public class FourBallOne extends SequentialCommandGroup {
  public FourBallOne(DriveBase driveBase, Intake intake, Shooter shooter, Limelight limelight, Turret turret, Feeder feeder, double waitTime) {
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
