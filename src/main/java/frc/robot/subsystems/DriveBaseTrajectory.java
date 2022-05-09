package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveBaseTrajectory extends SubsystemBase {
  WPI_TalonFX LEFT_LEADER = new WPI_TalonFX(Constants.LEFT_LEADER_ID);
  WPI_TalonFX RIGHT_LEADER = new WPI_TalonFX(Constants.RIGHT_LEADER_ID);

  WPI_TalonFX LEFT_FOLLOW = new WPI_TalonFX(Constants.LEFT_FOLLOW_ID);
  WPI_TalonFX RIGHT_FOLLOW = new WPI_TalonFX(Constants.RIGHT_FOLLOW_ID);

  DifferentialDrive DRIVE;

  double throttle;
  double rotation;

  Pigeon2 gyro = new Pigeon2(Constants.PIGEON2_ID);

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(26));
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0, 0);

  PIDController leftPidController = new PIDController(0, 0, 0);
  PIDController rightPIDController = new PIDController(0, 0, 0);

  Pose2d pose;
  /** Creates a new DriveBaseTrajectory. */
  public DriveBaseTrajectory() {
    LEFT_FOLLOW.follow(LEFT_LEADER);
    RIGHT_FOLLOW.follow(RIGHT_LEADER);

    LEFT_LEADER.setInverted(false);
    RIGHT_LEADER.setInverted(true);

    DRIVE = new DifferentialDrive(LEFT_LEADER, RIGHT_LEADER);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pose = odometry.update(getHeading(), getLeftWheelDistances(), getRightWheelDistances());
  }

  public Rotation2d getHeading() {
    return Rotation2d.fromDegrees(-gyro.getYaw());
  }

  public DifferentialDriveWheelSpeeds getSpeeds() {
    return new DifferentialDriveWheelSpeeds(
      LEFT_LEADER.getSelectedSensorVelocity() / 10.7142857142857 * (Math.PI*Units.inchesToMeters(6.0)), 
      RIGHT_LEADER.getSelectedSensorVelocity() / 10.7142857142857 * (Math.PI*Units.inchesToMeters(6.0)));
  }

  public double getLeftWheelDistances() {
    return LEFT_LEADER.getSelectedSensorPosition() / 2048 / 10.7142857142857 * (Math.PI*Units.inchesToMeters(6.0));
  }

  public double getRightWheelDistances() {
    return RIGHT_LEADER.getSelectedSensorPosition()/ 2048 / 10.7142857142857 * (Math.PI*Units.inchesToMeters(6.0));
  }

  public SimpleMotorFeedforward getFeedForward() {
    return feedforward;
  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public Pose2d getPose() {
    return pose;
  }

  public void setOutput(double leftVolts, double rightVolts) {
    LEFT_LEADER.setVoltage(leftVolts / 12);
    RIGHT_LEADER.setVoltage(rightVolts / 12);
  }

  public PIDController getLeftPIDController() {
    return leftPidController;
  }

  public PIDController getRightPIDController() {
    return rightPIDController;
  }

  public void arcadedrive() {
    throttle = RobotContainer.driverController.getLeftY();
    rotation = RobotContainer.driverController.getLeftX();
    DRIVE.arcadeDrive(throttle, rotation);
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
}
