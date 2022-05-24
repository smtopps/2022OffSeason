package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ShooterCommands.DissableFeederAndShooter;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;

public class ExtendMidBarGroup extends SequentialCommandGroup {
  public ExtendMidBarGroup(Climber climber, Intake intake) {
    addCommands(
      new DissableFeederAndShooter(),
      new ToggleIntake(intake, 1),
      new DissableCompressor(climber),
      new RetractClimber(climber),
      new ExtendClimber(climber, 67.6)
    );
  }
}
