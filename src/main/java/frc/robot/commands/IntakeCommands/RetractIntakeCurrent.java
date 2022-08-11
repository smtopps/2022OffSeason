package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RetractIntakeCurrent extends CommandBase {
  public final Intake intake;
  public final State state;
  public boolean holdIntake = false;

  public RetractIntakeCurrent(Intake intake, State state) {
    this.intake = intake;
    this.state = state;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(state == State.RETRACTING && holdIntake == false) {
      intake.intakeRotationSpeed(-1);
    }else if(state == State.RETRACTING && holdIntake == true) {
      intake.intakeRotationSpeed(-0.5);
    }else if(state == State.INTAKE && holdIntake == false) {
      intake.intakeRotationSpeed(1);
      intake.intakeSpeed(0.8);
    }else if(state == State.INTAKE && holdIntake == true) {
      intake.intakeRotationSpeed(0.5);
      intake.intakeSpeed(0.8);
    }else if(state == State.RIVERSING && holdIntake == false) {
      intake.intakeRotationSpeed(1);
      intake.intakeSpeed(-0.8);
    }else if(state == State.RIVERSING && holdIntake == true) {
      intake.intakeRotationSpeed(0.5);
      intake.intakeSpeed(-0.8);
    }

    if(intake.intakeRotationCurrent() > 20) {
      holdIntake = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    intake.intakeRotationStop();
    intake.intakeStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public enum State {
    INTAKE, RETRACTING, RIVERSING
  }
}
