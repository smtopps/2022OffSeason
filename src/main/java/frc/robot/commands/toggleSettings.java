package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SettingsSubsystem;

public class toggleSettings extends CommandBase {
  SettingsSubsystem settingsSubsystem;

  public toggleSettings(SettingsSubsystem settingsSubsystem) {
    this.settingsSubsystem = settingsSubsystem;
    addRequirements(settingsSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(RobotContainer.operatorController.getAButtonPressed()) {
      RobotContainer.toggleAlignTurret = !RobotContainer.toggleAlignTurret;
    }

    if(RobotContainer.operatorController.getYButtonPressed()) {
      RobotContainer.shootOpponentsBalls = !RobotContainer.shootOpponentsBalls;
    }

    if(RobotContainer.operatorController.getBButtonPressed()){
      RobotContainer.grabOponentBalls = !RobotContainer.grabOponentBalls;
    }

    if(RobotContainer.operatorController.getXButtonPressed()) {
      RobotContainer.stopFeederSystem = !RobotContainer.stopFeederSystem;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
