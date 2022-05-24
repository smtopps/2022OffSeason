package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {
  public final Intake intake;
  public int OneRaiseTwoLowThreeToggle;

  public ToggleIntake(Intake intake, int OneRaiseTwoLowThreeToggle) {
    this.intake = intake;
    this.OneRaiseTwoLowThreeToggle = OneRaiseTwoLowThreeToggle;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    if(OneRaiseTwoLowThreeToggle == 3) {
      intake.intakeTogglePosition();
    }else if(OneRaiseTwoLowThreeToggle == 2) {
      intake.intakeSetPosition(true);
    }else if(OneRaiseTwoLowThreeToggle == 1) {
      intake.intakeSetPosition(false);
    }
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
