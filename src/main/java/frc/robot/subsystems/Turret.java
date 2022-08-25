package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private final WPI_TalonSRX turretMotor = new WPI_TalonSRX(Constants.TURRET_MOTOR_ID);
  private final DigitalInput leftTurretLimitSwitch = new DigitalInput(0);
  private final DigitalInput rightTurretLimitSwitch = new DigitalInput(1);
  private final DigitalInput centerTurretLimitSwitch = new DigitalInput(2);

  public Turret() {
    turretMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30);
    turretMotor.setNeutralMode(NeutralMode.Brake);
    turretMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 10, 0.1));

    turretMotor.clearStickyFaults();
    //turretMotor.configForwardSoftLimitThreshold(1024);
    //turretMotor.configReverseSoftLimitThreshold(-1024);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Turret Encoder", turretMotor.getSelectedSensorPosition() );
    //SmartDashboard.putNumber("Turret Current", turretMotor.getSupplyCurrent());
  }

  public double turretPosition() {
    return turretMotor.getSelectedSensorPosition();
  }

  public void turretSpeedEncoder1(double speed) {
    if (turretPosition() <= 180 && turretPosition() >= -180) {
      turretMotor.set(ControlMode.PercentOutput, speed);
    }else if (turretPosition() >= 180){
      turretMotor.set(ControlMode.PercentOutput, -0.1);
    }else if (turretPosition() <= -180){
      turretMotor.set(ControlMode.PercentOutput, 0.1);
    }
  }

  public void turretSpeedEncoder2(double speed) {
    if (turretPosition() <= 180 && speed > 0) {
      turretMotor.set(ControlMode.PercentOutput, speed);   
    }else if (turretPosition() >= -180 && speed < 0) {
      turretMotor.set(ControlMode.PercentOutput, speed);   
    }else {
      turretMotor.stopMotor();    
    }
  }

  public void turretSpeed(double speed) {
    turretMotor.set(ControlMode.PercentOutput, speed);
  }

  public void turretSpeedLimitSwitch(double speed) {
    if (leftTurretLimitSwitch.get() && speed <= 0) {
      turretMotor.stopMotor();
    }else if (rightTurretLimitSwitch.get() && speed >=0) {
      turretMotor.stopMotor();
    }else{
      turretMotor.set(speed);
    }
  }

  public void centerTurretEncoder() {
    double speed = Math.abs(turretMotor.getSelectedSensorPosition() * -0);
    if (turretMotor.getSelectedSensorPosition() <= -12) {
      turretMotor.set(ControlMode.PercentOutput, speed);   
    }else if (turretMotor.getSelectedSensorPosition() >= 12) {
      turretMotor.set(ControlMode.PercentOutput, speed);   
    }else {
      turretMotor.stopMotor();    
    }
  }

  public void centerTurretLimitSwitch() {
    double voltage = -1;
    if (centerTurretLimitSwitch.get()) {
      turretMotor.stopMotor();
    }else if (leftTurretLimitSwitch.get()) {
      voltage = 1;
      turretMotor.setVoltage(voltage);
    }else if (rightTurretLimitSwitch.get()) {
      voltage = -1;
      turretMotor.setVoltage(voltage);
    }else{
      turretMotor.setVoltage(voltage);
    }
  }

  public void stopTurret() {
    turretMotor.stopMotor();
  }

  public void resetTurretEncoder() {
    turretMotor.setSelectedSensorPosition(0);
  }
}
