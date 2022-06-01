package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {
  WPI_TalonFX LEFT_LEADER;
  WPI_TalonFX LEFT_FOLLOW;
  WPI_TalonFX RIGHT_LEADER;
  WPI_TalonFX RIGHT_FOLLOW;
  DifferentialDrive DRIVE;
  double throttle;
  double rotation;

  public DriveBase() {
    LEFT_LEADER = new WPI_TalonFX(Constants.LEFT_LEADER_ID);
    LEFT_FOLLOW = new WPI_TalonFX(Constants.LEFT_FOLLOW_ID);
    RIGHT_LEADER = new WPI_TalonFX(Constants.RIGHT_LEADER_ID);
    RIGHT_FOLLOW = new WPI_TalonFX(Constants.RIGHT_FOLLOW_ID);

    LEFT_LEADER.configFactoryDefault();
    LEFT_FOLLOW.configFactoryDefault();
    RIGHT_LEADER.configFactoryDefault();
    RIGHT_FOLLOW.configFactoryDefault();

    LEFT_FOLLOW.follow(LEFT_LEADER);
    RIGHT_FOLLOW.follow(RIGHT_LEADER);

    RIGHT_LEADER.setInverted(true);
    RIGHT_FOLLOW.setInverted(true);

    DRIVE = new DifferentialDrive(LEFT_LEADER, RIGHT_LEADER);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Encoder Distance", getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder Distance", getRightEncoderDistance());
  }

  public void arcadedrive(double throttle, double rotation, boolean squared) {
    DRIVE.arcadeDrive(
      -throttle, 
      rotation, 
      squared);
  }

  public void autoArcadedrive(double throttle, double rotation) {
    DRIVE.arcadeDrive(throttle, rotation, false);
  }

  public void autoArcadeStop() {
    DRIVE.stopMotor();
  }

  public void setmaxoutput(double maxOutput) {
    DRIVE.setMaxOutput(maxOutput);
  }

  public void setdeadband(double deadband) {
    DRIVE.setDeadband(deadband);
  }

  public void setneutralmode(String mode) {
    if (mode == "Break") {
    LEFT_LEADER.setNeutralMode(NeutralMode.Brake);
    LEFT_FOLLOW.setNeutralMode(NeutralMode.Brake);
    RIGHT_LEADER.setNeutralMode(NeutralMode.Brake);
    RIGHT_FOLLOW.setNeutralMode(NeutralMode.Brake);
    }
    
    else if (mode == "Coast") {
    LEFT_LEADER.setNeutralMode(NeutralMode.Coast);
    LEFT_FOLLOW.setNeutralMode(NeutralMode.Coast);
    RIGHT_LEADER.setNeutralMode(NeutralMode.Coast);
    RIGHT_FOLLOW.setNeutralMode(NeutralMode.Coast);
    }
  }

  public double getLeftEncoderDistance() {
    return (LEFT_LEADER.getSelectedSensorPosition() / 2048 / 10.7142857142857 * (Math.PI*5.6));
  }

  public double getRightEncoderDistance() {
    return (RIGHT_LEADER.getSelectedSensorPosition() / 2040 / 10.7142857142857 * (Math.PI*5.6));
  }

  public void resetEncoderPosition() {
    LEFT_LEADER.setSelectedSensorPosition(0);
    RIGHT_LEADER.setSelectedSensorPosition(0);
  }

  public void setRampRate(double rampRate) {
    LEFT_LEADER.configOpenloopRamp(rampRate);
    RIGHT_LEADER.configOpenloopRamp(rampRate);
    LEFT_LEADER.configClosedloopRamp(rampRate);
    RIGHT_LEADER.configClosedloopRamp(rampRate);
  }
}
