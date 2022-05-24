package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;

public class ClimbToTopGroup extends SequentialCommandGroup {
  public static double ClimberBar = 1;
  public ClimbToTopGroup(Climber climber) {
    addCommands(
      new DissableCompressor(climber),
      new RetractClimber(climber),
      new WaitCommand(0.5),
      new BarToBarGoup(climber),
      new WaitCommand(1),
      new RetractClimber(climber),
      new WaitCommand(0.5),
      new BarToBarGoup(climber),
      new WaitCommand(1),
      new RetractClimber(climber),
      new EnableCompressor(climber)
    );
  }
}
