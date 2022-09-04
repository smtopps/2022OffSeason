package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ZeroIntake extends CommandBase {
  private final Intake intake;
  private double initalTime;

  public ZeroIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    initalTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    intake.intakeRotationSpeed(0.25);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setIntakeRotationPosition(0);
    intake.intakeRotationStop();
  }

  @Override
  public boolean isFinished() {
    if(Timer.getFPGATimestamp()-initalTime >= 0.5) {
      return true;
    }else{
      return false;
    }
  }
}
