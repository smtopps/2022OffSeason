package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class TurnByAngleBasic extends CommandBase {
  DriveBase driveBase;
  double angle;
  double distance;
  double maxSpeed;

  public TurnByAngleBasic(DriveBase driveBase, double angle, double maxSpeed) {
    this.driveBase = driveBase;
    this.angle = angle;
    this.maxSpeed = maxSpeed;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    SmartDashboard.putBoolean("TurnByAngle", true);
    driveBase.setDeadband(0.0);
    driveBase.resetEncoderPosition();
  }

  @Override
  public void execute() {
    distance = ((Math.PI*22) / 360) *angle;
    double leftError = distance - driveBase.getLeftEncoderDistance();
    double speed = leftError * 0.022;

    if(speed > maxSpeed) {
      speed = maxSpeed;
    }else if(speed < -maxSpeed) {
      speed = -maxSpeed;
    }else if(speed < 0.07 && speed > 0){
      speed = 0.07;
    }else if(speed > -0.07 && speed < 0){
      speed = -0.07;
    }

    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("error", leftError);
    driveBase.autoArcadedrive(0, speed);
  }

  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("TurnByAngle", false);
    driveBase.autoArcadedrive(0, 0);
    driveBase.resetEncoderPosition();
  }

  @Override
  public boolean isFinished() {
    if(Math.abs(driveBase.getLeftEncoderDistance()) < Math.abs(distance)) {
      return false;
    }else{
      return true;
    }
  }
}
