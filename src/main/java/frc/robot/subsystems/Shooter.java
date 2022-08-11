package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax leftShooterMotor = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax rightShooterMotor = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);

  private final SparkMaxPIDController rightShooterPIDController = rightShooterMotor.getPIDController();
  private final RelativeEncoder rightShooterEncoder = rightShooterMotor.getEncoder();
  private final Solenoid hoodPositionCylinder = new Solenoid(Constants.REV_PNEUMATIC_MODULE_ID, PneumaticsModuleType.REVPH, Constants.HOOD_POSITION);

  double RPMOffset;
  
  public Shooter() {
    leftShooterMotor.restoreFactoryDefaults();
    rightShooterMotor.restoreFactoryDefaults();
    leftShooterMotor.setIdleMode(IdleMode.kCoast);
    rightShooterMotor.setIdleMode(IdleMode.kCoast);
    leftShooterMotor.enableVoltageCompensation(11);
    rightShooterMotor.enableVoltageCompensation(11);

    rightShooterMotor.setOpenLoopRampRate(0.5);
    rightShooterMotor.setClosedLoopRampRate(0.5);

    rightShooterPIDController.setFeedbackDevice(rightShooterEncoder);
    rightShooterPIDController.setFF(0.00021);
    rightShooterPIDController.setP(0.00003);
    rightShooterPIDController.setI(0);
    rightShooterPIDController.setD(0);

    leftShooterMotor.setInverted(true); //consider removing since line bellow should do this
    leftShooterMotor.follow(rightShooterMotor, true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("flywheleRPM", rightShooterEncoder.getVelocity());
    SmartDashboard.putNumber("RPM Offset", RPMOffset);
    SmartDashboard.getNumber("Tune RPM", -1250);
  }

  public void setFlywheelRPM(double RPM){
    rightShooterPIDController.setReference(RPM - RPMOffset, ControlType.kVelocity);
  }

  public void setHoodPosition(boolean position) {
    hoodPositionCylinder.set(position);
  }

  public void stopMotors(){
    rightShooterMotor.stopMotor();
  }

  public double getFlywheelRPM(){
    return rightShooterEncoder.getVelocity();
  }

  public double TuningRPM(){
    return SmartDashboard.getNumber("Tune RPM", -1250);
  }

  public void setRampRate(double rampRate) {
    rightShooterMotor.setOpenLoopRampRate(rampRate);
    rightShooterMotor.setClosedLoopRampRate(rampRate);
  }
}
