package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveBase extends SubsystemBase {
  WPI_TalonFX LEFT_LEADER;
  WPI_TalonFX LEFT_FOLLOW;
  WPI_TalonFX RIGHT_LEADER;
  WPI_TalonFX RIGHT_FOLLOW;
  MotorControllerGroup LEFT_MOTORS;
  MotorControllerGroup RIGHT_MOTORS;
  DifferentialDrive DRIVE;
  double throttle;
  double rotation;

  public DriveBase() {
    LEFT_LEADER = new WPI_TalonFX(Constants.LEFT_LEADER_ID);
    LEFT_FOLLOW = new WPI_TalonFX(Constants.LEFT_FOLLOW_ID);
    RIGHT_LEADER = new WPI_TalonFX(Constants.RIGHT_LEADER_ID);
    RIGHT_FOLLOW = new WPI_TalonFX(Constants.RIGHT_FOLLOW_ID);

    LEFT_FOLLOW.follow(LEFT_LEADER);
    RIGHT_FOLLOW.follow(RIGHT_LEADER);

    RIGHT_LEADER.setInverted(true);
    RIGHT_FOLLOW.setInverted(true);

    LEFT_MOTORS = new MotorControllerGroup(LEFT_LEADER, LEFT_FOLLOW);
    RIGHT_MOTORS = new MotorControllerGroup(RIGHT_LEADER, RIGHT_FOLLOW);

    DRIVE = new DifferentialDrive(LEFT_MOTORS, RIGHT_MOTORS);
  }

  @Override
  public void periodic() {}

  public void arcadedrive() {
    throttle = RobotContainer.driverController.getLeftY();
    rotation = RobotContainer.driverController.getLeftX();
    DRIVE.arcadeDrive(-throttle, rotation, true);
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
