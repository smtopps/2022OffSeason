package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  WPI_TalonSRX INTAKE_MOTOR;
  Solenoid INTAKE_CYLINDER;

  public Intake() {
    INTAKE_MOTOR = new WPI_TalonSRX(Constants.INTAKE_ID);

    INTAKE_MOTOR.setNeutralMode(NeutralMode.Coast);

    INTAKE_CYLINDER = new Solenoid(Constants.REV_PNEUMATIC_MODULE_ID, PneumaticsModuleType.REVPH, Constants.INTAKE_POSITION);
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
}
