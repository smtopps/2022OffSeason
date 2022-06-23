package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final WPI_TalonSRX INTAKE_MOTOR;
  private final CANSparkMax INTAKE_ROTATION_MOTOR;
  private final Solenoid INTAKE_CYLINDER;

  public Intake() {
    INTAKE_MOTOR = new WPI_TalonSRX(Constants.INTAKE_ID);
    INTAKE_ROTATION_MOTOR = new CANSparkMax(Constants.INTAKE_ROTATION_ID, MotorType.kBrushless);
    INTAKE_MOTOR.setNeutralMode(NeutralMode.Coast);
    INTAKE_ROTATION_MOTOR.setIdleMode(IdleMode.kCoast);
    INTAKE_CYLINDER = new Solenoid(Constants.REV_PNEUMATIC_MODULE_ID, PneumaticsModuleType.REVPH, Constants.INTAKE_POSITION);
    INTAKE_ROTATION_MOTOR.setSmartCurrentLimit(40, 40);
  }

  @Override
  public void periodic() {}

  public void intakeSpeed(double speed) {
    INTAKE_MOTOR.set(ControlMode.PercentOutput, speed);
  }

  public void intakeSetPosition(boolean position) {
    INTAKE_CYLINDER.set(position);
  }

  public void intakeTogglePosition() {
    INTAKE_CYLINDER.toggle();
  }

  public void intakeStop() {
    INTAKE_MOTOR.stopMotor();
  }

  public void intakeRotationSpeed(double voltage) {
    INTAKE_ROTATION_MOTOR.setVoltage(voltage);
  }

  public void intakeRotationStop() {
    INTAKE_ROTATION_MOTOR.stopMotor();
  }

  public double intakeRotationCurrent() {
    return INTAKE_ROTATION_MOTOR.getOutputCurrent();
  }
}
