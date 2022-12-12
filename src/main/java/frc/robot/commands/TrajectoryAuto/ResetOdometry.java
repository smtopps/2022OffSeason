package frc.robot.commands.TrajectoryAuto;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class ResetOdometry extends CommandBase {
  private final DriveBase driveBase;
  private final Trajectory trajectory;

  public ResetOdometry(DriveBase driveBase, Trajectory trajectory) {
    this.driveBase = driveBase;
    this.trajectory = trajectory;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    driveBase.resetOdometry(trajectory);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
