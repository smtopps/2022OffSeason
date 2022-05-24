package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  CANSparkMax FEEDER_MOTOR;
  ColorSensorV3 COLOR_SENSOR;
  DriverStation.Alliance color;

  public Feeder() {
    FEEDER_MOTOR = new CANSparkMax(Constants.FEEDER_MOTOR_ID, MotorType.kBrushless);
    FEEDER_MOTOR.setIdleMode(IdleMode.kBrake);
    COLOR_SENSOR = new ColorSensorV3(i2cPort);
    
    }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Feeder Proximity", COLOR_SENSOR.getProximity());
    SmartDashboard.putNumber("Feeder Red", COLOR_SENSOR.getRed());
    SmartDashboard.putNumber("Feeder Blue", COLOR_SENSOR.getBlue());
    SmartDashboard.putNumber("Feeder Green", COLOR_SENSOR.getGreen());
    color = DriverStation.getAlliance();
    }

  public void feederSpeed(double voltage) {
    FEEDER_MOTOR.setVoltage(voltage);
  }

  public void feederStop() {
    FEEDER_MOTOR.stopMotor();
  }

  public boolean isBallRightColor() {
    if(color == DriverStation.Alliance.Blue) {
      if(COLOR_SENSOR.getBlue() > COLOR_SENSOR.getRed()) {
        return true;
      }else{
        return false;
      }
    }else if(color == DriverStation.Alliance.Red) {
      if(COLOR_SENSOR.getRed() > COLOR_SENSOR.getBlue()) {
        return true;
      }else{
        return false;
      }
    }else{
      return true;
    }
  }

  public boolean isBallInFeeder() {
    if (COLOR_SENSOR.getProximity() >=70) {
      return true;
    }else{
      return false;
    }
  }
}
