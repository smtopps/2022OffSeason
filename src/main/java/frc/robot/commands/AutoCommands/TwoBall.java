package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.RunIntake;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ShooterCommands.IdleShooter;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;


public class TwoBall extends SequentialCommandGroup {
  public TwoBall(DriveBase driveBase, Intake intake, Shooter shooter, Turret turret, Limelight limelight, Feeder feeder, double waitTime) {
    addCommands(
      new WaitCommand(waitTime),
      new ToggleIntake(intake, 2),
      new WaitCommand(0.3),
      new ResetDriveEncoder(driveBase),
      new ParallelRaceGroup(
        new DriveByDistanceBasic(driveBase, 32, 0.2, 0.2),
        new RunIntake(intake),
        new PrepareBallsInFeeder(feeder),
        new IdleShooter(shooter)
      ),
      new ShootBalls(shooter, turret, limelight, feeder),
      new ToggleIntake(intake, 1),
      new WaitCommand(0.5),
      new ResetDriveEncoder(driveBase),
      new DriveByDistanceBasic(driveBase, 12, 0.3, 0.3)
    );
  }
}
