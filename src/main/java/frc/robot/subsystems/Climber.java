package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  CANSparkMax CLIMBER_LEFT_MOTOR;
  CANSparkMax CLIMBER_RIGHT_MOTOR;
  RelativeEncoder CLIMBER_LEFT_ENCODER;
  RelativeEncoder CLIMBER_RIGHT_ENCODER;
  Solenoid CLIMBER_SOLINOID;
  public Climber() {
    CLIMBER_SOLINOID = new Solenoid(13, PneumaticsModuleType.REVPH, 12);
    CLIMBER_LEFT_MOTOR = new CANSparkMax(Constants.CLIMBER_LEFT_MOTOR_ID, MotorType.kBrushless);
    CLIMBER_RIGHT_MOTOR = new CANSparkMax(Constants.CLIMBER_RIGHT_MOTOR_ID, MotorType.kBrushless);

    CLIMBER_LEFT_MOTOR.restoreFactoryDefaults();
    CLIMBER_RIGHT_MOTOR.restoreFactoryDefaults();
    CLIMBER_LEFT_MOTOR.setIdleMode(IdleMode.kBrake);
    CLIMBER_RIGHT_MOTOR.setIdleMode(IdleMode.kBrake);
    CLIMBER_LEFT_MOTOR.setSmartCurrentLimit(5);
    CLIMBER_RIGHT_MOTOR.setSmartCurrentLimit(5);

    CLIMBER_LEFT_ENCODER = CLIMBER_LEFT_MOTOR.getEncoder();
    CLIMBER_RIGHT_ENCODER = CLIMBER_RIGHT_MOTOR.getEncoder();
  }

  @Override
  public void periodic() {}

  public void leftClimberSpeed(double voltage) {
    CLIMBER_LEFT_MOTOR.setVoltage(voltage);
  }

  public void rightClimberSpeed(double voltage) {
    CLIMBER_RIGHT_MOTOR.setVoltage(voltage);
  }

  public double leftClimberEncoder() {
    return CLIMBER_LEFT_ENCODER.getPosition();
  }

  public double rightClimberEncoder() {
    return CLIMBER_RIGHT_ENCODER.getPosition();
  }

  public double leftClimberCurrent() {
    return CLIMBER_LEFT_MOTOR.getOutputCurrent();
  }

  public double rightClimberCurrent() {
    return CLIMBER_RIGHT_MOTOR.getOutputCurrent();
  }

  public void leftClimberStop() {
    CLIMBER_LEFT_MOTOR.stopMotor();
  }

  public void rightClimberStop() {
    CLIMBER_RIGHT_MOTOR.stopMotor();
  }

  public void angleClimberDown() {
    CLIMBER_SOLINOID.set(true);
  }

  public void angleClimberUp() {
    CLIMBER_SOLINOID.set(false);
  }

  public void resetEncoders() {
    CLIMBER_LEFT_ENCODER.setPosition(0);
    CLIMBER_RIGHT_ENCODER.setPosition(0);
  }
}
