package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class TuneShooter extends CommandBase {
  public static boolean FlywheelAtSpeed = false;
  private final Shooter shooter;
  private final Limelight limelight;

  private double RPM = 2300;
  private int Counter = 0;

  public TuneShooter(Shooter shooter, Limelight limelight) {
    this.shooter = shooter;
    this.limelight = limelight;
    addRequirements(shooter, limelight);
    SmartDashboard.putNumber("Tune RPM", 2300);
  }

  @Override
  public void initialize() {
    limelight.setLEDMode(3);
    limelight.setCameraMode(0);
    limelight.setStream(1);
  }

  @Override
  public void execute() {
    RPM = SmartDashboard.getNumber("Tune RPM", 2300);
    shooter.setFlywheelRPM(-RPM);
    SmartDashboard.putNumber("Set RPM", -RPM);
    shooter.setHoodPosition(true);

    if((Math.abs(shooter.getFlywheelRPM() + RPM)) < 30) { // try to get to 30
      Counter++;
    }

    if(limelight.getTV() == 1 && Counter >= 3) {
      FlywheelAtSpeed = true;
    }else{
      FlywheelAtSpeed = false;
    }

    SmartDashboard.putBoolean("FlywheelAtSpeed", FlywheelAtSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    FlywheelAtSpeed = false;
    shooter.stopMotors();
    //shooter.setHoodPosition(false);
    RPM = 1250;
    Counter = 0;
    SmartDashboard.putBoolean("FlywheelAtSpeed", false);
    RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
    RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 0);
    //limelight.setLEDMode(1);
    //limelight.setCameraMode(1);
    //limelight.setStream(2);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}