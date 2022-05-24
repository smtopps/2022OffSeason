package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;


public class BarToBarGoup extends SequentialCommandGroup {
  public BarToBarGoup(Climber climber) {
    addCommands(
      new AngleClimberDown(climber),
      new ExtendClimber(climber, 73.3),
      new WaitCommand(0.5),
      new AngleClimberUp(climber)
    );
  }
}
