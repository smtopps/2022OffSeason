package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class RetractClimber extends CommandBase {
  public final Climber climber;

  public RetractClimber(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double leftError = climber.leftClimberEncoder() - climber.rightClimberEncoder();
    double rightError = climber.rightClimberEncoder() - climber.leftClimberEncoder();
    double leftOffset = leftError * Constants.CLIMBER_RETRACTION_ERROR;
    double rightOffset = rightError * Constants.CLIMBER_RETRACTION_ERROR;

    climber.leftClimberSpeed(-10-leftOffset);
    climber.rightClimberSpeed(-10-rightOffset);
  }

  @Override
  public void end(boolean interrupted) {
    climber.resetEncoders();
    climber.leftClimberStop();
    climber.rightClimberStop();
    ClimbToTopGroup.ClimberBar ++;
    SmartDashboard.putNumber("Climber Bar", ClimbToTopGroup.ClimberBar);
  }

  @Override
  public boolean isFinished() {
    Timer.delay(0.05);
    if (Math.abs(climber.leftClimberVelocity()) < 1 && Math.abs(climber.rightClimberVelocity()) < 1) {
      return true;
    }else{
      return false;
    }
  }
}
