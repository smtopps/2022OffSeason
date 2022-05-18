package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  CANSparkMax FEEDER_MOTOR;
  ColorSensorV3 COLOR_SENSOR;
  ColorMatch COLOR_MATCHER;
  String colorString;
  NetworkTableEntry ALLIANCE_COLOR;

  public Feeder() {
    FEEDER_MOTOR = new CANSparkMax(Constants.FEEDER_MOTOR_ID, MotorType.kBrushless);
    FEEDER_MOTOR.setIdleMode(IdleMode.kBrake);
    COLOR_SENSOR = new ColorSensorV3(i2cPort);
    COLOR_MATCHER = new ColorMatch();
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("FMSInfo");
    ALLIANCE_COLOR = table.getEntry("isRedAlliance");
    
    final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    COLOR_MATCHER.addColorMatch(kBlueTarget);
    COLOR_MATCHER.addColorMatch(kRedTarget);
    Color detectedColor = COLOR_SENSOR.getColor();
    ColorMatchResult match = COLOR_MATCHER.matchClosestColor(detectedColor);
    if(match.color == kBlueTarget) {
      colorString = "Blue";
    }else if(match.color == kRedTarget) {
      colorString = "Red";
    }
    }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Feeder Proximity", COLOR_SENSOR.getProximity());
    SmartDashboard.putNumber("Feeder Red", COLOR_SENSOR.getRed());
    SmartDashboard.putNumber("Feeder Blue", COLOR_SENSOR.getBlue());
    SmartDashboard.putNumber("Feeder Green", COLOR_SENSOR.getGreen());
    SmartDashboard.putString("Color", colorString);
    }

  public void feederSpeed(double voltage) {
    FEEDER_MOTOR.setVoltage(voltage);
  }

  public void feederStop() {
    FEEDER_MOTOR.stopMotor();
  }

  public void getBallInFeederWithColor() {
    if(COLOR_SENSOR.getProximity() >= 70 && COLOR_SENSOR.getRed() > COLOR_SENSOR.getGreen() && COLOR_SENSOR.getRed() > COLOR_SENSOR.getBlue())  {
      FEEDER_MOTOR.stopMotor();
    }else{
      FEEDER_MOTOR.setVoltage(-3.5);
    }
  }

  public void getBallInFeeder() {
    if(COLOR_SENSOR.getProximity() >=70) {
      FEEDER_MOTOR.stopMotor();
    }else{
      FEEDER_MOTOR.setVoltage(-3.5);
    }
  }

  public void colorRejector() {
    if (COLOR_SENSOR.getRed() < COLOR_SENSOR.getGreen() && COLOR_SENSOR.getRed() < COLOR_SENSOR.getBlue()) {
      FEEDER_MOTOR.setVoltage(-4);
    }else{
      FEEDER_MOTOR.stopMotor();
    }
  }

  public boolean isBallRightColor(int color1IsBlue2IsRed) {
    if(color1IsBlue2IsRed == 1) {
      if(COLOR_SENSOR.getGreen() > COLOR_SENSOR.getBlue() && COLOR_SENSOR.getGreen() > COLOR_SENSOR.getRed()) {
        return true;
      }else{
        return false;
      }
    }else if{color1IsBlue2IsRed}
    if(COLOR_SENSOR.getRed() > COLOR_SENSOR.getGreen() && COLOR_SENSOR.getRed() > COLOR_SENSOR.getBlue()) {
      return true;
    }else{
      return false;
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
