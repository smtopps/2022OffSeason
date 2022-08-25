package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final WPI_TalonSRX intakeRollerMotor = new WPI_TalonSRX(Constants.INTAKE_ID);
  private final CANSparkMax intakeRotationMotor = new CANSparkMax(Constants.INTAKE_ROTATION_ID, MotorType.kBrushless);
  private final Solenoid intakeRotationSolenoid = new Solenoid(Constants.REV_PNEUMATIC_MODULE_ID, PneumaticsModuleType.REVPH, Constants.INTAKE_CHANNEL);
  private final RelativeEncoder intakeRotationEncoder = intakeRotationMotor.getEncoder();

  public Intake() {
    intakeRollerMotor.setNeutralMode(NeutralMode.Coast);
    intakeRollerMotor.configOpenloopRamp(0.5);
    intakeRollerMotor.configClosedloopRamp(0.5);
    intakeRollerMotor.enableCurrentLimit(true);
    intakeRollerMotor.configContinuousCurrentLimit(35);
    intakeRollerMotor.clearStickyFaults();
    intakeRotationMotor.setIdleMode(IdleMode.kBrake);
    intakeRotationMotor.setSmartCurrentLimit(25);
    intakeRotationMotor.enableVoltageCompensation(10);
    intakeRotationMotor.clearFaults();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake Encoder", getIntakeRotationPosition());
    //SmartDashboard.putNumber("Intake Roller Supply Current", intakeRollerMotor.getSupplyCurrent());
    SmartDashboard.putNumber("Intake Rotation Current", intakeRotationMotor.getOutputCurrent());
    SmartDashboard.putNumber("Intake Rotation Velocity", intakeRotationEncoder.getVelocity());
  }

  public void intakeSpeed(double speed) {
    intakeRollerMotor.set(ControlMode.PercentOutput, speed);
  }

  public void intakeSetPosition(boolean position) {
    intakeRotationSolenoid.set(position);
  }

  public void intakeTogglePosition() {
    intakeRotationSolenoid.toggle();
  }

  public void intakeStop() {
    intakeRollerMotor.stopMotor();
  }

  public void intakeRotationSpeed(double voltage) {
    intakeRotationMotor.setVoltage(voltage);
  }

  public void intakeRotationStop() {
    intakeRotationMotor.stopMotor();
  }

  public double intakeRotationCurrent() {
    return intakeRotationMotor.getOutputCurrent();
  }

  public double getIntakeRotationSpeed() {
    return intakeRotationEncoder.getVelocity();
  }

  public double getIntakeRotationPosition() {
    return intakeRotationEncoder.getPosition();
  }

  public void setIntakeRotationPosition(double position) {
    intakeRotationEncoder.setPosition(position);
  }
}
