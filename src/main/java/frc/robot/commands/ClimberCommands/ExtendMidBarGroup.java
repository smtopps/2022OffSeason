package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake.IntakePosition;
import frc.robot.commands.SettingsCommands.EnableIdle;
import frc.robot.commands.SettingsCommands.EnableIdle.IdleState;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;

public class ExtendMidBarGroup extends SequentialCommandGroup {
  public ExtendMidBarGroup(Climber climber, Intake intake) {
    addCommands(
      new EnableIdle(IdleState.DISSABLED),
      new DissableCompressor(climber),
      new ToggleIntake(intake, IntakePosition.RAISED),
      new RetractClimber(climber),
      new ExtendClimber(climber, 67.6)
    );
  }
}
