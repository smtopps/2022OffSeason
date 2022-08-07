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
  private final CANSparkMax LEFT_SHOOTER_MOTOR = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax RIGHT_SHOOTER_MOTOR = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);

  private final SparkMaxPIDController SHOOTER_PID_RIGHT = RIGHT_SHOOTER_MOTOR.getPIDController();
  private final RelativeEncoder SHOOTER_ENCODER_RIGHT = RIGHT_SHOOTER_MOTOR.getEncoder();
  private final Solenoid HOOD_CYLINDER;

  double RPMOffset;
  
  public Shooter() {
    HOOD_CYLINDER = new Solenoid(Constants.REV_PNEUMATIC_MODULE_ID, PneumaticsModuleType.REVPH, Constants.HOOD_POSITION);

    LEFT_SHOOTER_MOTOR.restoreFactoryDefaults();
    RIGHT_SHOOTER_MOTOR.restoreFactoryDefaults();
    LEFT_SHOOTER_MOTOR.setIdleMode(IdleMode.kCoast);
    RIGHT_SHOOTER_MOTOR.setIdleMode(IdleMode.kCoast);
    LEFT_SHOOTER_MOTOR.enableVoltageCompensation(11);
    RIGHT_SHOOTER_MOTOR.enableVoltageCompensation(11);

    //RIGHT_SHOOTER_MOTOR.setOpenLoopRampRate(0.5);
    //RIGHT_SHOOTER_MOTOR.setClosedLoopRampRate(0.5);

    SHOOTER_PID_RIGHT.setFeedbackDevice(SHOOTER_ENCODER_RIGHT);
    SHOOTER_PID_RIGHT.setFF(0.00021); //was 0.000195
    SHOOTER_PID_RIGHT.setP(0.00003); //was 0.000015
    SHOOTER_PID_RIGHT.setI(0);
    SHOOTER_PID_RIGHT.setD(0);

    LEFT_SHOOTER_MOTOR.setInverted(true);
    LEFT_SHOOTER_MOTOR.follow(RIGHT_SHOOTER_MOTOR, true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("flywheleRPM", SHOOTER_ENCODER_RIGHT.getVelocity());
    SmartDashboard.putNumber("RPM Offset", RPMOffset);
    SmartDashboard.getNumber("Tune RPM", -1250);
  }

  public void setFlywheelRPM(double RPM){
    SHOOTER_PID_RIGHT.setReference(RPM - RPMOffset, ControlType.kVelocity);
  }

  public void setHoodPosition(boolean position) {
    HOOD_CYLINDER.set(position);
  }

  public void stopMotors(){
    RIGHT_SHOOTER_MOTOR.stopMotor();
  }

  public double getFlywheelRPM(){
    return SHOOTER_ENCODER_RIGHT.getVelocity();
  }

  public double TuningRPM(){
    return SmartDashboard.getNumber("Tune RPM", -1250);
  }

  public boolean isFlywheelAtSpeed1(double RPM) {
    for(int counter=0; counter<5; counter++){
      if(SHOOTER_ENCODER_RIGHT.getVelocity() > RPM+25){
        counter = 0;
      }
      if(SHOOTER_ENCODER_RIGHT.getVelocity() < RPM-25) {
        counter = 0;
      }
    }
    return true;
  }
   
  public boolean isFlywheelAtSpeed2(double RPM) {
    int counter = 0;
    do{
      if(SHOOTER_ENCODER_RIGHT.getVelocity() < RPM+50 && SHOOTER_ENCODER_RIGHT.getVelocity() > RPM-50){
        counter ++;
      }else{
        counter = 0;
      }
    }while (counter <= 2);
    return true;
  }

  public void setRampRate(double rampRate) {
    RIGHT_SHOOTER_MOTOR.setOpenLoopRampRate(rampRate);
    RIGHT_SHOOTER_MOTOR.setClosedLoopRampRate(rampRate);
  }
}
