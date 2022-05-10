package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  public final Intake intake;
  public final DriveBase driveBase;

  public RunIntake(Intake intake, DriveBase driveBase) {
    this.intake = intake;
    this.driveBase = driveBase;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    driveBase.setmaxoutput(Constants.MAX_SPEED_DRIVE_INTAKE);
    intake.compressorDisable();
    intake.intakeSetPosition(true);
  }

  @Override
  public void execute() {
    intake.intakeSpeed(Constants.INTAKE_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.setmaxoutput(Constants.MAX_SPEED_DRIVE);
    intake.intakeSpeed(0.0);
    intake.compressorEnable();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
