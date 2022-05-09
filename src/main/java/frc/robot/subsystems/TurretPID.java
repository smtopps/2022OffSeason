package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class TurretPID extends PIDSubsystem {
  WPI_TalonSRX TURRET_MOTOR = new WPI_TalonSRX(Constants.TURRET_MOTOR_ID);
  double TURRET_ENCODER = TURRET_MOTOR.getSelectedSensorPosition();
  SimpleMotorFeedforward TURRET_MOTOR_FEEDFORWARD = new SimpleMotorFeedforward(0, 0);
  /** Creates a new TurretPID. */
  public TurretPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
        getController().setTolerance(1.5);
        setSetpoint(0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    if(TURRET_ENCODER <= 180 && TURRET_ENCODER >= -180) {
      TURRET_MOTOR.setVoltage(output + TURRET_MOTOR_FEEDFORWARD.calculate(setpoint));
    }else if(TURRET_ENCODER >= 180){
      TURRET_MOTOR.setVoltage(-1);
    }else if(TURRET_ENCODER <= -180) {
      TURRET_MOTOR.setVoltage(1);
    }else{
      TURRET_MOTOR.stopMotor();
    }
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void setTurretMotorBrake() {
    TURRET_MOTOR.setNeutralMode(NeutralMode.Brake);
  }
}
