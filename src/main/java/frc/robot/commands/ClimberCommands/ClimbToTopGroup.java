// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ClimbToTopGroup extends SequentialCommandGroup {
  /** Creates a new ClimbToTop. */
  public ClimbToTopGroup(Climber climber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DissableCompressor(climber),
      new RetractClimber(climber),
      new BarToBarGoup(climber),
      new WaitCommand(0.5),
      new RetractClimber(climber),
      new BarToBarGoup(climber),
      new WaitCommand(0.5),
      new RetractClimber(climber),
      new EnableCompressor(climber)
    );
  }
}
