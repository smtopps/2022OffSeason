package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  CANSparkMax LEFT_SHOOTER_MOTOR;
  CANSparkMax RIGHT_SHOOTER_MOTOR;
  SparkMaxPIDController SHOOTER_PID_LEFT;
  SparkMaxPIDController SHOOTER_PID_RIGHT;
  RelativeEncoder SHOOTER_ENCODER_LEFT;
  RelativeEncoder SHOOTER_ENCODER_RIGHT;

  public Shooter() {
    LEFT_SHOOTER_MOTOR = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    RIGHT_SHOOTER_MOTOR = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);

    LEFT_SHOOTER_MOTOR.restoreFactoryDefaults();
    RIGHT_SHOOTER_MOTOR.restoreFactoryDefaults();
    LEFT_SHOOTER_MOTOR.setIdleMode(IdleMode.kCoast);
    RIGHT_SHOOTER_MOTOR.setIdleMode(IdleMode.kCoast);
    LEFT_SHOOTER_MOTOR.setSmartCurrentLimit(5);
    RIGHT_SHOOTER_MOTOR.setSmartCurrentLimit(5);
    LEFT_SHOOTER_MOTOR.setClosedLoopRampRate(2);
    RIGHT_SHOOTER_MOTOR.setClosedLoopRampRate(2);

    //RIGHT_SHOOTER_MOTOR.follow(LEFT_SHOOTER_MOTOR);
    //RIGHT_SHOOTER_MOTOR.setInverted(true);

    SHOOTER_PID_LEFT = LEFT_SHOOTER_MOTOR.getPIDController();
    SHOOTER_ENCODER_LEFT = LEFT_SHOOTER_MOTOR.getEncoder();

    SHOOTER_PID_LEFT.setP(0);
    SHOOTER_PID_LEFT.setI(0);
    SHOOTER_PID_LEFT.setD(0);
    SHOOTER_PID_LEFT.setIZone(0);
    SHOOTER_PID_LEFT.setFF(0);
    SHOOTER_PID_LEFT.setOutputRange(0, 0);
  }

  @Override
  public void periodic() {}

  public double getFlywheelVelocity() {
    return SHOOTER_ENCODER_LEFT.getVelocity();
  }

  public void flywheelSpeed(double RPM)  {
    SHOOTER_PID_LEFT.setReference(RPM, CANSparkMax.ControlType.kVelocity);
    //SHOOTER_PID_RIGHT.setReference(RPM, CANSparkMax.ControlType.kVelocity);
  }

  public void flywheelStop() {
    LEFT_SHOOTER_MOTOR.stopMotor();
    //RIGHT_SHOOTER_MOTOR.stopMotor();
  }

  public boolean isFlywheelAtSpeed1(double RPM) {
    for(int counter=0; counter<5; counter++){
      if(SHOOTER_ENCODER_LEFT.getVelocity() > RPM+10){
        counter = 0;
      }
      if(SHOOTER_ENCODER_LEFT.getVelocity() < RPM-10) {
        counter = 0;
      }
    }
    return true;
  }
   
  public boolean isFlywheelAtSpeed2(double RPM) {
    int counter = 0;
    do{
      if(SHOOTER_ENCODER_LEFT.getVelocity() < RPM+10 && SHOOTER_ENCODER_LEFT.getVelocity() > RPM-10){
        counter ++;
      }else{
        counter = 0;
      }
    }while (counter <= 5);
    return true;
  }
}
