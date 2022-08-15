package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final CANSparkMax indexerMotor = new CANSparkMax(Constants.FEEDER_MOTOR_ID, MotorType.kBrushless);
  private final ColorSensorV3 indexerColorSensor = new ColorSensorV3(i2cPort);
  DriverStation.Alliance color;

  public Feeder() {
    indexerMotor.setIdleMode(IdleMode.kBrake);
    indexerMotor.setSmartCurrentLimit(80);
    indexerMotor.setClosedLoopRampRate(0.5);
    indexerMotor.setOpenLoopRampRate(0.5);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Feeder Proximity", indexerColorSensor.getProximity());
    //SmartDashboard.putNumber("Feeder Red", indexerColorSensor.getRed());
    //SmartDashboard.putNumber("Feeder Blue", indexerColorSensor.getBlue());
    //SmartDashboard.putNumber("Feeder Green", indexerColorSensor.getGreen());
    color = DriverStation.getAlliance();
  }

  public void feederSpeed(double voltage) {
    indexerMotor.setVoltage(voltage);
  }

  public void feederStop() {
    indexerMotor.stopMotor();
  }

  public boolean isBallRightColor() {
    if(color == DriverStation.Alliance.Blue) {
      if(indexerColorSensor.getBlue() > indexerColorSensor.getRed()) {
        return true;
      }else{
        return false;
      }
    }else if(color == DriverStation.Alliance.Red) {
      if(indexerColorSensor.getRed() > indexerColorSensor.getBlue()) {
        return true;
      }else{
        return false;
      }
    }else{
      return true;
    }
  }

  public boolean isBallInFeeder() {
    if (indexerColorSensor.getProximity() >=70) {
      return true;
    }else{
      return false;
    }
  }
}
