package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class ShootBalls extends ParallelCommandGroup {

  public ShootBalls(Shooter shooter, Turret turret, Limelight limelight, Feeder feeder, DriveBase drivebase) {
    addCommands(
      new AlignTurret(turret, limelight),
      new RevShooter(shooter, drivebase, limelight),
      new AutoFeedBallsToShooter(limelight, feeder)
    );
  }
}
