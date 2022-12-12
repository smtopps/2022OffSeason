package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.SettingsCommands.EnableCompressor;
import frc.robot.commands.SettingsCommands.EnableIdle;
import frc.robot.commands.SettingsCommands.EnableCompressor.CompressorState;
import frc.robot.commands.SettingsCommands.EnableIdle.IdleState;
import frc.robot.subsystems.Climber;

public class ExtendMidBarGroup extends SequentialCommandGroup {
  public ExtendMidBarGroup(Climber climber) {
    addCommands(
      new EnableIdle(IdleState.DISSABLED),
      new EnableCompressor(climber, CompressorState.OFF),
      new RetractClimber(climber),
      new ExtendClimber(climber, 67.6)
    );
  }
}
