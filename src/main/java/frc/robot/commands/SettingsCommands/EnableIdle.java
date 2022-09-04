package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableIdle extends CommandBase {
  private final IdleState idleState;

  public EnableIdle(IdleState idleState) {
    this.idleState = idleState;
  }

  @Override
  public void initialize() {
    if(idleState == IdleState.TOGGLE) {
      RobotContainer.enableIdle = !RobotContainer.enableIdle;
    }else if(idleState == IdleState.DISSABLED) {
      RobotContainer.enableIdle = false;
    }else if(idleState == IdleState.ENABLED) {
      RobotContainer.enableIdle = true;
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

  public enum IdleState {
    DISSABLED, ENABLED, TOGGLE
  }
}
