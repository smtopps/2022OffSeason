package frc.robot.commands.ClimberCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ManualClimber extends CommandBase {
  private final Climber climber;
  private DoubleSupplier leftYJoystick;

  public ManualClimber(Climber climber, DoubleSupplier leftYJoystick) {
    this.climber = climber;
    this.leftYJoystick = leftYJoystick;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.leftClimberSpeed(MathUtil.applyDeadband(leftYJoystick.getAsDouble(), 0.1)*-10);
    climber.rightClimberSpeed(MathUtil.applyDeadband(leftYJoystick.getAsDouble(), 0.1)*-10);
  }

  @Override
  public void end(boolean interrupted) {
    climber.leftClimberStop();
    climber.rightClimberStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
