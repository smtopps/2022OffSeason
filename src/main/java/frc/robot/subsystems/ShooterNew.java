package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterNew extends SubsystemBase {
  /** Creates a new ShooterNew. */
  private final CANSparkMax LEFT_SHOOTER_MOTOR = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax RIGHT_SHOOTER_MOTOR = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);

  private final SparkMaxPIDController SHOOTER_PID_RIGHT = RIGHT_SHOOTER_MOTOR.getPIDController();
  private final RelativeEncoder SHOOTER_ENCODER_RIGHT = RIGHT_SHOOTER_MOTOR.getEncoder();

  double RPMOffset;
  
  public ShooterNew() {
    System.out.println("Shooter constructor called");
    LEFT_SHOOTER_MOTOR.restoreFactoryDefaults();
    RIGHT_SHOOTER_MOTOR.restoreFactoryDefaults();
    LEFT_SHOOTER_MOTOR.setIdleMode(IdleMode.kCoast);
    RIGHT_SHOOTER_MOTOR.setIdleMode(IdleMode.kCoast);

    RIGHT_SHOOTER_MOTOR.setOpenLoopRampRate(0.5);
    RIGHT_SHOOTER_MOTOR.setClosedLoopRampRate(0.5);

    SHOOTER_PID_RIGHT.setFeedbackDevice(SHOOTER_ENCODER_RIGHT);
    SHOOTER_PID_RIGHT.setFF(0.00019); //was 0.0002
    SHOOTER_PID_RIGHT.setP(0.00001); //was 0.00015
    SHOOTER_PID_RIGHT.setI(0);
    SHOOTER_PID_RIGHT.setD(0.000); //was 0.0002

    LEFT_SHOOTER_MOTOR.setInverted(true);
    LEFT_SHOOTER_MOTOR.follow(RIGHT_SHOOTER_MOTOR, true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("flywheleRPM", SHOOTER_ENCODER_RIGHT.getVelocity());
    SmartDashboard.putNumber("RPM Offset", RPMOffset);
  }

  public void setFlywheelRPM(double RPM){
    SHOOTER_PID_RIGHT.setReference(RPM - RPMOffset, ControlType.kVelocity);
  }

  public void stopMotors(){
    RIGHT_SHOOTER_MOTOR.stopMotor();
  }

  public double getFlywheelRPM(){
    return SHOOTER_ENCODER_RIGHT.getVelocity();
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

public void flywheelSpeed(int i) {
}

public void flywheelStop() {
}
}
