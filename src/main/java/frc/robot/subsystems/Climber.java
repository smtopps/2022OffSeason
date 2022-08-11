package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftClimberMotor = new CANSparkMax(Constants.CLIMBER_LEFT_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax rightClimberMotor = new CANSparkMax(Constants.CLIMBER_RIGHT_MOTOR_ID, MotorType.kBrushless);
  private final RelativeEncoder leftClimberEncoder = leftClimberMotor.getEncoder();
  private final RelativeEncoder rightClimberEncoder = rightClimberMotor.getEncoder();
  private final Solenoid climberSolenoid = new Solenoid(Constants.REV_PNEUMATIC_MODULE_ID, PneumaticsModuleType.REVPH, 14);
  private final Compressor compressor = new Compressor(Constants.CTRE_PNEUMATICS_MODULE_ID, PneumaticsModuleType.CTREPCM);
  public Climber() {
    leftClimberMotor.restoreFactoryDefaults();
    rightClimberMotor.restoreFactoryDefaults();
    leftClimberMotor.setIdleMode(IdleMode.kBrake);
    rightClimberMotor.setIdleMode(IdleMode.kBrake);
    leftClimberMotor.setSmartCurrentLimit(80);
    rightClimberMotor.setSmartCurrentLimit(80);

    leftClimberMotor.setInverted(false);
    rightClimberMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ClimberLeftEncoder", leftClimberEncoder());
    SmartDashboard.putNumber("ClimberRightEncoder", rightClimberEncoder());
  }

  public void leftClimberSpeed(double voltage) {
    leftClimberMotor.setVoltage(voltage);
  }

  public void rightClimberSpeed(double voltage) {
    rightClimberMotor.setVoltage(voltage);
  }

  public double leftClimberEncoder() {
    return leftClimberEncoder.getPosition();
  }

  public double rightClimberEncoder() {
    return rightClimberEncoder.getPosition();
  }

  public double leftClimberCurrent() {
    return leftClimberMotor.getOutputCurrent();
  }

  public double rightClimberCurrent() {
    return rightClimberMotor.getOutputCurrent();
  }

  public double leftClimberVelocity() {
    return leftClimberEncoder.getVelocity();
  }

  public double rightClimberVelocity() {
    return rightClimberEncoder.getVelocity();
  }

  public void leftClimberStop() {
    leftClimberMotor.stopMotor();
  }

  public void rightClimberStop() {
    rightClimberMotor.stopMotor();
  }

  public void angleClimberDown() {
    climberSolenoid.set(true);
  }

  public void angleClimberUp() {
    climberSolenoid.set(false);
  }

  public void toggleClimber() {
    climberSolenoid.toggle();
  }

  public void resetEncoders() {
    leftClimberEncoder.setPosition(0);
    rightClimberEncoder.setPosition(0);
  }
  
  public void compressorDisable() {
    compressor.disable();
  }

  public void compressorEnable() {
    compressor.enableDigital();
  }
}
