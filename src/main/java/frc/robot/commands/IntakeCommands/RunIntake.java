package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  public final Intake intake;
  public double speed;
  //public boolean holdIntake = false;

  public RunIntake(Intake intake, double speed) {
    this.intake = intake;
    this.speed = speed;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.intakeSetPosition(true);
    SmartDashboard.putBoolean("RunIntake", true);
  }

  @Override
  public void execute() {
    intake.intakeSpeed(speed);
    /*if(holdIntake == false) {
      intake.intakeRotationSpeed(1);
    }else{
      intake.intakeRotationSpeed(0.5);
    }*/

    /*if(intake.intakeRotationCurrent() > 20) {
      holdIntake = true;
    }*/

    /*if(Math.abs(intake.getIntakeRotationSpeed()) < 1) {
      holdIntake = true;
    }*/
  }

  @Override
  public void end(boolean interrupted) {
    intake.intakeStop();
    //intake.intakeRotationStop();
    SmartDashboard.putBoolean("RunIntake", false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
