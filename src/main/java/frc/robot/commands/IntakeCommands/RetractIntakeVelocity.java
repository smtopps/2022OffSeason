package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RetractIntakeVelocity extends CommandBase {
  public final Intake intake;
  public boolean holdIntake = false;

  public RetractIntakeVelocity(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(intake.getIntakeRotationSpeed() < 0.01) {
      intake.intakeRotationSpeed(0.5);
    }else{
      intake.intakeRotationSpeed(3.5);
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
