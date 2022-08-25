package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class ShootBalls extends ParallelRaceGroup {

  public ShootBalls(Shooter shooter, Turret turret, Limelight limelight, Feeder feeder, boolean rumble) {
    addCommands(
      new AlignTurret(turret, limelight),
      new RevShooter(shooter, limelight, rumble),
      //new TuneShooter(shooter, limelight),
      new FeedBallsToShooter(limelight, feeder)
    );
  }
}
