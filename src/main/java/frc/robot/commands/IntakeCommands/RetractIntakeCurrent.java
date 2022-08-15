package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RetractIntakeCurrent extends CommandBase {
  public final Intake intake;
  public final State state;
  public boolean holdIntake = false;
  public int counter = 5;
  public boolean waitForCurrent = true;

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
      intake.intakeRotationSpeed(3);
    }else if(state == State.RETRACTING && holdIntake == true) {
      intake.intakeRotationSpeed(0.5);

    }else if(state == State.INTAKE && holdIntake == false) {
      intake.intakeRotationSpeed(-2);
      intake.intakeSpeed(0.8);
    }else if(state == State.INTAKE && holdIntake == true) {
      intake.intakeRotationSpeed(-0.5);
      intake.intakeSpeed(0.8);
    }else if(state == State.RIVERSING && holdIntake == false) {
      intake.intakeRotationSpeed(-2);
      intake.intakeSpeed(-0.8);
    }else if(state == State.RIVERSING && holdIntake == true) {
      intake.intakeRotationSpeed(-0.5);
      intake.intakeSpeed(-0.8);
    }

    SmartDashboard.putNumber("RotationCurrent", intake.intakeRotationCurrent());
    if(intake.intakeRotationCurrent() > 30 && counter <= 0) {
      holdIntake = true;
      intake.setIntakeRotationPosition(0);
    }

    SmartDashboard.putBoolean("Hold Intake", holdIntake);

    counter--;
  }

  @Override
  public void end(boolean interrupted) {
    intake.intakeRotationStop();
    intake.intakeStop();
    holdIntake = false;
    counter = 30;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public enum State {
    INTAKE, RETRACTING, RIVERSING
  }
}
