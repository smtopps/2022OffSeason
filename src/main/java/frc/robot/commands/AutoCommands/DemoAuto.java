package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.IntakeCommands.RunIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.commands.ShooterCommands.IdleShooter;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class DemoAuto extends SequentialCommandGroup {
  public DemoAuto(DriveBase driveBase, Intake intake, Shooter shooter, Turret turret, Feeder feeder, Limelight limelight, double waitTime) {
    addCommands(
      new WaitCommand(waitTime),
      new ResetDriveEncoder(driveBase),
      new ParallelRaceGroup(
        new DriveByDistanceBasic(driveBase, 48, 0.3),
        new RunIntake(intake, 0.8),
        new PrepareBallsInFeeder(feeder)
      ),
      new ResetDriveEncoder(driveBase),
      new TurnByAngleBasic(driveBase, 120, 0.3),
      new ResetDriveEncoder(driveBase),
      new ParallelRaceGroup(
        new DriveByDistanceBasic(driveBase, 48, 0.3),
        new RunIntake(intake, 0.8),
        new PrepareBallsInFeeder(feeder)
      ),
      new ToggleIntake(intake, 1),
      new ResetDriveEncoder(driveBase),
      new TurnByAngleBasic(driveBase, -60, 0.3),
      new ResetDriveEncoder(driveBase),
      new DriveByDistanceBasic(driveBase, -48, 0.3),
      new ResetDriveEncoder(driveBase),
      new ParallelRaceGroup(
        new TurnByAngleBasic(driveBase, 30, 0.3),
        new IdleShooter(shooter, limelight) 
      ),
      new ShootBalls(shooter, turret, limelight, feeder)
    );
  }
}
