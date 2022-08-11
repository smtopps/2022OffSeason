package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pigeon2Subsystem extends SubsystemBase {
  /** Creates a new Pigeon2Subsystem */
  private final static Pigeon2 m_pigeon2 =  new Pigeon2(Constants.PIGEON2_ID);


  public Pigeon2Subsystem() {
    //m_pigeon2.configFactoryDefault();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void zeroGyroscope() {
    m_pigeon2.setYaw(0);
  }

  public static void setGyroscopeRotation(double rotation) {
    m_pigeon2.setYaw(rotation);
  }

  public static Rotation2d getGyroscopeRotation() {
    return Rotation2d.fromDegrees(m_pigeon2.getYaw());
  }

  public Rotation2d getGyroRotation() {
    return Rotation2d.fromDegrees(m_pigeon2.getYaw());
  }

  public double getYaw() {
    return m_pigeon2.getYaw();
  }

  public double getPitch() {
    return m_pigeon2.getPitch();
  }

  public double getRoll() {
    return m_pigeon2.getRoll();
  }

  public int getRobotAngle() {
    return 0;
  }
}
