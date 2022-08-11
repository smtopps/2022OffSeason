// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonVision extends SubsystemBase {
  PhotonCamera camera = new PhotonCamera("photonvision");
  /** Creates a new PhotonVision. */
  public PhotonVision() {}

  @Override
  public void periodic() {
    if(DriverStation.getAlliance() == Alliance.Red) {
      camera.setPipelineIndex(1);
    }else if(DriverStation.getAlliance() == Alliance.Blue) {
      camera.setPipelineIndex(2);
    }
    // This method will be called once per scheduler run
  }

  public double AngleToBall() {
    return camera.getLatestResult().getBestTarget().getYaw();
  }

  public boolean targetAvailable() {
    return camera.getLatestResult().hasTargets();
  }
}
