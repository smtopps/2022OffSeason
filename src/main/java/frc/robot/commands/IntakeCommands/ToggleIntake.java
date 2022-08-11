package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {
  private final Intake intake;
  private final IntakePosition intakePosition;

  public ToggleIntake(Intake intake, IntakePosition intakePosition) {
    this.intake = intake;
    this.intakePosition = intakePosition;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    if(intakePosition == IntakePosition.TOGGLE) {
      intake.intakeTogglePosition();
    }else if(intakePosition == IntakePosition.LOWERED) {
      intake.intakeSetPosition(true);
    }else if(intakePosition == IntakePosition.RAISED) {
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

  public enum IntakePosition {
    RAISED, LOWERED, TOGGLE
  }
}
