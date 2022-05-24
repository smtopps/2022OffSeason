package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveTest extends CommandBase {
  DriveBase driveBase;
  double speed;
  double rampRate;
  double time;

  public DriveTest(DriveBase driveBase, double speed, double rampRate) {
    this.driveBase = driveBase;
    this.speed = speed;
    this.rampRate = rampRate;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    driveBase.setneutralmode("Brake");
    driveBase.setdeadband(0.0);
    driveBase.setRampRate(rampRate);
    time = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    driveBase.autoArcadedrive(0, speed);
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.autoArcadedrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if(time < 2){
      return false;
    }else{
      return false;
    }
  }
}
