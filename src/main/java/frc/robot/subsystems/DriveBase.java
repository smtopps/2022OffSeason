package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.AutoConstants;

public class DriveBase extends SubsystemBase {
  private final WPI_TalonFX leftLeader = new WPI_TalonFX(Constants.LEFT_LEADER_ID);
  private final WPI_TalonFX leftFollower = new WPI_TalonFX(Constants.LEFT_FOLLOW_ID);
  private final WPI_TalonFX rightLeader = new WPI_TalonFX(Constants.RIGHT_LEADER_ID);
  private final WPI_TalonFX rightFollower = new WPI_TalonFX(Constants.RIGHT_FOLLOW_ID);
  private final DifferentialDrive differentialDrive = new DifferentialDrive(leftLeader, rightLeader);
  private final Field2d field2d = new Field2d();

  private final DifferentialDriveOdometry odometry;

  private final double currentLimit = 50;
  private final double currentThreshold = 60;

  //NetworkTable table = NetworkTableInstance.getDefault().getTable("troubleshooting");
  //NetworkTableEntry leftReference = table.getEntry("left_reference");
  //NetworkTableEntry leftMeasurement = table.getEntry("left_measurement");
  //NetworkTableEntry rightReference = table.getEntry("right_reference");
  //NetworkTableEntry rightMeasurement = table.getEntry("right_measurement");

  public DriveBase() {
    leftLeader.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightLeader.configFactoryDefault();
    rightFollower.configFactoryDefault();

    leftLeader.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, currentThreshold, 1));
    leftLeader.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, currentLimit, currentThreshold, 0.5));
    leftFollower.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, currentThreshold, 1));
    leftFollower.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, currentLimit, currentThreshold, 0.5));
    rightLeader.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, currentThreshold, 1));
    rightLeader.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, currentLimit, currentThreshold, 0.5));
    rightFollower.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, currentThreshold, 1));
    rightFollower.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, currentLimit, currentThreshold, 0.5));

    leftLeader.clearStickyFaults();
    leftFollower.clearStickyFaults();
    rightLeader.clearStickyFaults();
    rightFollower.clearStickyFaults();

    leftLeader.setNeutralMode(NeutralMode.Brake);
    leftFollower.setNeutralMode(NeutralMode.Brake);
    rightLeader.setNeutralMode(NeutralMode.Brake);
    rightFollower.setNeutralMode(NeutralMode.Brake);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    rightLeader.setInverted(true);
    rightFollower.setInverted(true);

    differentialDrive.setDeadband(0.02);

    odometry = new DifferentialDriveOdometry(Pigeon2Subsystem.getGyroscopeRotation());
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Left Encoder Distance", getLeftEncoderDistance());
    //SmartDashboard.putNumber("Right Encoder Distance", getRightEncoderDistance());
    //SmartDashboard.putNumber("Left Encoder Speed", getLeftEncoderSpeed());
    //SmartDashboard.putNumber("Right Encoder Speed", getRightEncoderSpeed());
    //SmartDashboard.putString("Pose", getPose().toString());
    //SmartDashboard.putNumber("leftStatorCurrent", leftLeader.getStatorCurrent());
    //SmartDashboard.putNumber("leftSupplyCurrent", leftLeader.getSupplyCurrent());
    //SmartDashboard.putNumber("rightStatorCurrent", rightLeader.getStatorCurrent());
    //SmartDashboard.putNumber("rightSupplyCurrent", rightLeader.getSupplyCurrent());
    SmartDashboard.putData("Field", field2d);
    field2d.setRobotPose(odometry.getPoseMeters());

    odometry.update(Pigeon2Subsystem.getGyroscopeRotation(), getLeftEncoderDistance(), getRightEncoderDistance());
  }

  public void arcadedrive(double throttle, double rotation, boolean squared) {
    differentialDrive.arcadeDrive(
      -throttle, 
      rotation, 
      squared);
  }

  public void autoArcadedrive(double throttle, double rotation) {
    differentialDrive.arcadeDrive(throttle, rotation, false);
  }

  public void autoArcadeStop() {
    differentialDrive.stopMotor();
  }

  public void setDeadband(double deadband) {
    differentialDrive.setDeadband(deadband);
  }

  private double nativeUnitsToDistanceMeters(double sensorCounts){
    double kCountsPerRev = 2048;
    double kGearRatio = (50.0/14.0)*(48.0/16.0);//10.71428571428571; added .0 to the end of all values to insure no integer division issues.
    double kWheelRadiusInches = 5.6/2;
		double motorRotations = (double)sensorCounts / kCountsPerRev;
		double wheelRotations = motorRotations / kGearRatio;
		double positionMeters = wheelRotations * (2 * Math.PI * Units.inchesToMeters(kWheelRadiusInches));
		return positionMeters;
	}

  private double nativeUnitsToVelocityMetersPerSecond(double sensorCountsPer100ms){
    double k100msPerSecond = 10;
    double velocityMetersPerSecond = nativeUnitsToDistanceMeters(sensorCountsPer100ms) * k100msPerSecond;
		return velocityMetersPerSecond;
	}

  public double getLeftEncoderDistance() {
    return nativeUnitsToDistanceMeters(leftLeader.getSelectedSensorPosition());
  }

  public double getRightEncoderDistance() {
    return nativeUnitsToDistanceMeters(rightLeader.getSelectedSensorPosition());
  }

  public double getLeftEncoderSpeed() {
    return nativeUnitsToVelocityMetersPerSecond(leftLeader.getSelectedSensorVelocity());
  }

  public double getRightEncoderSpeed() {
    return nativeUnitsToVelocityMetersPerSecond(rightLeader.getSelectedSensorVelocity());
  }

  public void resetEncoderPosition() {
    leftLeader.setSelectedSensorPosition(0);
    rightLeader.setSelectedSensorPosition(0);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void resetOdometry(Trajectory trajectory) { // path planning
    resetEncoderPosition();
    odometry.resetPosition(trajectory.getInitialPose(), Pigeon2Subsystem.getGyroscopeRotation());
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderSpeed(), getRightEncoderSpeed());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftLeader.setVoltage(leftVolts);
    rightLeader.setVoltage(rightVolts);
    differentialDrive.feed();
  }

  public Command createCommandForTrajectory(Trajectory trajectory) {
    RamseteController ramseteController = new RamseteController();

    PIDController leftController = new PIDController(AutoConstants.kPLeftController, 0, 0);
    PIDController rightController = new PIDController(AutoConstants.kPRightController, 0, 0);

    RamseteCommand controllerCommand = new RamseteCommand(
      trajectory, 
      this::getPose, 
      ramseteController,
      new SimpleMotorFeedforward(AutoConstants.ks, AutoConstants.kv, AutoConstants.ka), 
      AutoConstants.kDriveKinematics, 
      this::getWheelSpeeds, 
      leftController,
      rightController,
      this::tankDriveVolts,
      /*(leftVolts, rightVolts) -> {
        this.tankDriveVolts(leftVolts, rightVolts);

        leftMeasurement.setNumber(this.getWheelSpeeds().leftMetersPerSecond);
        leftReference.setNumber(leftController.getSetpoint());

        rightMeasurement.setNumber(this.getWheelSpeeds().rightMetersPerSecond);
        rightReference.setNumber(rightController.getSetpoint()
        );
      },*/
      this
    );

    return controllerCommand.andThen(() -> autoArcadeStop());
  }
}
