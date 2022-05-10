package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  WPI_TalonSRX TURRET_MOTOR;
  double TURRET_ENCODER;
  DigitalInput TURRET_LEFT_LIMIT_SWITCH;
  DigitalInput TURRET_RIGHT_LIMIT_SWITCH;
  DigitalInput TURRET_CENTER_LIMIT_SWITCH;

  public Turret() {
    TURRET_MOTOR = new WPI_TalonSRX(Constants.TURRET_MOTOR_ID);
    TURRET_LEFT_LIMIT_SWITCH = new DigitalInput(0);
    TURRET_RIGHT_LIMIT_SWITCH = new DigitalInput(1);
    TURRET_CENTER_LIMIT_SWITCH = new DigitalInput(2);
    TURRET_MOTOR.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 1, 0);
    TURRET_ENCODER = TURRET_MOTOR.getSelectedSensorPosition();
    TURRET_MOTOR.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {}

  public void turretSpeedEncoder1(double speed) {
    if (TURRET_ENCODER <= 180 && TURRET_ENCODER >= -180) {
      TURRET_MOTOR.set(ControlMode.PercentOutput, speed);
    }else if (TURRET_ENCODER >= 180){
      TURRET_MOTOR.set(ControlMode.PercentOutput, -0.1);
    }else if (TURRET_ENCODER <= -180){
      TURRET_MOTOR.set(ControlMode.PercentOutput, 0.1);
    }
  }

  public void turretSpeedEncoder2(double speed) {
    if (TURRET_ENCODER <= 180 && speed > 0) {
      TURRET_MOTOR.set(ControlMode.PercentOutput, speed);   
    }else if (TURRET_ENCODER >= -180 && speed < 0) {
      TURRET_MOTOR.set(ControlMode.PercentOutput, speed);   
    }else {
      TURRET_MOTOR.stopMotor();    
    }
  }

  public void turretSpeed(double speed) {
    TURRET_MOTOR.set(ControlMode.PercentOutput, speed);
  }

  public void turretSpeedLimitSwitch(double speed) {
    if (TURRET_LEFT_LIMIT_SWITCH.get() && speed <= 0) {
      TURRET_MOTOR.stopMotor();
    }else if (TURRET_RIGHT_LIMIT_SWITCH.get() && speed >=0) {
      TURRET_MOTOR.stopMotor();
    }else{
      TURRET_MOTOR.set(speed);
    }
  }

  public void centerTurretEncoder() {
    double speed = Math.abs(TURRET_ENCODER * 0);
    if (TURRET_ENCODER <= -1.5) {
      TURRET_MOTOR.set(ControlMode.PercentOutput, speed);   
    }else if (TURRET_ENCODER >= 1.5) {
      TURRET_MOTOR.set(ControlMode.PercentOutput, speed * -1);   
    }else {
      TURRET_MOTOR.stopMotor();    
    }
  }

  public void centerTurretLimitSwitch() {
    double voltage = -1;
    if (TURRET_CENTER_LIMIT_SWITCH.get()) {
      TURRET_MOTOR.stopMotor();
    }else if (TURRET_LEFT_LIMIT_SWITCH.get()) {
      voltage = 1;
      TURRET_MOTOR.setVoltage(voltage);
    }else if (TURRET_RIGHT_LIMIT_SWITCH.get()) {
      voltage = -1;
      TURRET_MOTOR.setVoltage(voltage);
    }else{
      TURRET_MOTOR.setVoltage(voltage);
    }
  }

  public void stopTurret() {
    TURRET_MOTOR.stopMotor();
  }
}
