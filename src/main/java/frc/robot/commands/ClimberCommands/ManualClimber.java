package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class ManualClimber extends CommandBase {
  Climber climber;
  double leftYJoystick;

  public ManualClimber(Climber climber, double leftYJoystick) {
    this.climber = climber;
    this.leftYJoystick = leftYJoystick;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.leftClimberSpeed(RobotContainer.operatorController.getLeftY()*10);
    climber.rightClimberSpeed(RobotContainer.operatorController.getLeftY()*10);
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
