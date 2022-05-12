package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.ShooterNew;
import frc.robot.subsystems.Turret;

public class ShootBalls extends ParallelCommandGroup {

  public ShootBalls(ShooterNew shooterNew, Turret turret, Limelight limelight, Feeder feeder) {
    addCommands(
      new AlignTurret(turret, limelight),
      new RevShooterNew(shooterNew, limelight),
      new AutoFeedBallsToShooter(limelight, feeder)
    );
  }
}
