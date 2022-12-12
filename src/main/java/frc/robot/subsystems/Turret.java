package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private final WPI_TalonSRX turretMotor = new WPI_TalonSRX(Constants.TURRET_MOTOR_ID);

  public Turret() {
    turretMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30);
    turretMotor.setNeutralMode(NeutralMode.Brake);
    turretMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 10, 0.1));

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

  public void turretSpeed(double speed) {
    turretMotor.set(ControlMode.PercentOutput, speed);
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

  public void stopTurret() {
    turretMotor.stopMotor();
  }

  public void resetTurretEncoder() {
    turretMotor.setSelectedSensorPosition(0);
  }
}
