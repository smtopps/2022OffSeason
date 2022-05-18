package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  public final Intake intake;

  public RunIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.intakeSetPosition(true);
    SmartDashboard.putBoolean("RunIntake", true);
  }

  @Override
  public void execute() {
    intake.intakeSpeed(Constants.INTAKE_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    intake.intakeSpeed(0.0);
    SmartDashboard.putBoolean("RunIntake", false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
