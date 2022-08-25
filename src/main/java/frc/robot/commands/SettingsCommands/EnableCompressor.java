package frc.robot.commands.SettingsCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class EnableCompressor extends CommandBase {
  private final Climber climber;
  private final CompressorState compressorState;

  public EnableCompressor(Climber climber, CompressorState compressorState) {
    this.climber = climber;
    this.compressorState = compressorState;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    if(compressorState == CompressorState.OFF) {
      climber.compressorDisable();
    }else if(compressorState == CompressorState.ON) {
      climber.compressorEnable();
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

  public enum CompressorState {
    ON, OFF, TOGGLE
  }
}
