package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ToggleClimber extends CommandBase {
  private final Climber climber;
  private final ClimberState climberState;

  public ToggleClimber(Climber climber, ClimberState climberState) {
    this.climber = climber;
    this.climberState = climberState;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    if(climberState == ClimberState.TOGGLE) {
      climber.toggleClimber();
    }else if(climberState == ClimberState.DOWN) {
      climber.angleClimberDown();
    }else if(climberState == ClimberState.UP) {
      climber.angleClimberUp();
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

  public enum ClimberState {
    UP, DOWN, TOGGLE
  }
}
