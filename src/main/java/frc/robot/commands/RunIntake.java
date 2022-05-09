package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBaseTrajectory;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  public final Intake intake;
  public final DriveBaseTrajectory driveBaseTrajectory;

  public RunIntake(Intake intake, DriveBaseTrajectory driveBaseTrajectory) {
    this.intake = intake;
    this.driveBaseTrajectory = driveBaseTrajectory;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    driveBaseTrajectory.setmaxoutput(Constants.MAX_SPEED_DRIVE_INTAKE);
    intake.compressorDisable();
    intake.intakeSetPosition(true);
  }

  @Override
  public void execute() {
    intake.intakeSpeed(Constants.INTAKE_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    driveBaseTrajectory.setmaxoutput(Constants.MAX_SPEED_DRIVE);
    intake.intakeSpeed(0.0);
    intake.compressorEnable();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
