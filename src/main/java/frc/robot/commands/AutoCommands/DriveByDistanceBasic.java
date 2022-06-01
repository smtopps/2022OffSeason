package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveByDistanceBasic extends CommandBase {
  DriveBase driveBase;
  double distance;
  double maxSpeed;

  public DriveByDistanceBasic(DriveBase driveBase, double distance, double maxSpeed) {
    this.driveBase = driveBase;
    this.distance = distance;
    this.maxSpeed = maxSpeed;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    SmartDashboard.putBoolean("DriveByDistance", true);
    driveBase.setneutralmode("Brake");
    driveBase.setdeadband(0.0);
    driveBase.resetEncoderPosition();
  }

  @Override
  public void execute() {
    double leftError = distance - driveBase.getLeftEncoderDistance();
    double speed = leftError * 0.02;

    if(speed > maxSpeed) {
      speed = maxSpeed;
    }else if(speed < -maxSpeed) {
      speed = -maxSpeed;
    }else if(speed < 0.06 && speed > 0){
      speed = 0.06;
    }else if(speed > -0.06 && speed < 0){
      speed = -0.06;
    }

    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("error", leftError);
    driveBase.autoArcadedrive(speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("DriveByDistance", false);
    driveBase.autoArcadedrive(0, 0);
    driveBase.resetEncoderPosition();
  }

  @Override
  public boolean isFinished() {
    if((Math.abs(driveBase.getLeftEncoderDistance())) > (Math.abs(distance))) {
      return true;
    }else{
      return false;
    }
  }
}
