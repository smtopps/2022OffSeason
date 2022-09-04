package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;

public class RetractClimberGroup extends SequentialCommandGroup {

  public RetractClimberGroup(Climber climber) {
    addCommands(
      /*new RetractClimberToPosition(climber, 30),
      new ParallelRaceGroup(
        new WaitCommand(1),
        new HoldClimberPosition(climber)
      ),*/
      new RetractClimber(climber),
      new WaitCommand(0.1),
      new ExtendClimber(climber, 6)
    );
  }
}
