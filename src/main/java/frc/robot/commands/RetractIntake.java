package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RetractIntake extends CommandBase {
  public final Intake intake;
  public boolean holdIntake = false;

  public RetractIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(holdIntake == false) {
      intake.intakeRotationSpeed(-1);
    }else{
      intake.intakeRotationSpeed(-0.5);
    }

    if(intake.intakeRotationCurrent() >= 60) {
      holdIntake = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    intake.intakeRotationStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
