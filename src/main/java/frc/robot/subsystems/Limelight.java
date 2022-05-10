package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  static NetworkTableEntry tx = table.getEntry("tx");
  static NetworkTableEntry ty = table.getEntry("ty");
  static NetworkTableEntry ta = table.getEntry("ta");
  static NetworkTableEntry tv = table.getEntry("tv");

  public Limelight() {}

  @Override
  public void periodic() {}

  public void setLEDMode(int mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(mode);
  }

  public void setCameraMode(int mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(mode);
  }

  public void setPipeline(int pipeline) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
  }

  public void setStream(int stream) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(stream);
  }

  public void takeSnapshot(int snapshot) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(snapshot);
  }

  public double getTX() {
    return tx.getDouble(0.0);
  }

  public double getTY() {
    return ty.getDouble(0.0);
  }

  public double getTA() {
    return ta.getDouble(0.0);
  }

  public double getTV() {
    return tv.getDouble(0.0);
  }

  public boolean isTurretAligned() {
    if(tv.getDouble(0.0) == 1 && tx.getDouble(0.0) <= 2 && tx.getDouble(0.0) >= -2) {
      return true;
    }else{
      return false;
    }
  }
}
