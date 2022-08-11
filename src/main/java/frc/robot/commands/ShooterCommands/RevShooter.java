package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class RevShooter extends CommandBase {
  public static boolean FlywheelAtSpeed = false;
  private final Shooter shooter;
  private final Limelight limelight;

  private double RPM = 1900;
  private int Counter = 0;
  private int Delay = 0;
  private boolean hoodPosition = false;

  public RevShooter(Shooter shooter, Limelight limelight) {
    this.shooter = shooter;
    this.limelight = limelight;
    addRequirements(shooter, limelight);
  }

  @Override
  public void initialize() {
    limelight.setLEDMode(3);
    limelight.setCameraMode(0);
    limelight.setStream(1);
  }

  @Override
  public void execute() {
    if(limelight.getTV() == 0 && Delay == 0){
      //hoodPosition = false;
      //shooter.setHoodPosition(hoodPosition);
      //Delay = 15;
    }else if(limelight.getTV() == 1 && hoodPosition == false && limelight.getTY() < -2.5 && Delay == 0){
      hoodPosition = true;
      shooter.setHoodPosition(hoodPosition);
      Delay = 15;
    }else if(limelight.getTV() == 1 && hoodPosition == true && limelight.getTY() > 5 && Delay == 0){
      hoodPosition = false;
      shooter.setHoodPosition(hoodPosition);
      Delay = 15;
    }


    if(Delay > 0) {
      Delay--;
    }

    if(limelight.getTV() == 0){
      RPM = 1900;
      System.out.println("does not see target");
    }else if(limelight.getTV() == 1 && hoodPosition == false && Delay == 0){
      RPM = (2421+(-57.7*limelight.getTY())+(1.57*(Math.pow(limelight.getTY(), 2))));
      System.out.println("hood in low position");
    }else if(limelight.getTV() == 1 && hoodPosition == true && Delay == 0){
      RPM = (20+2435+(-67.2*limelight.getTY())+(1.99*(Math.pow(limelight.getTY(), 2))));
      System.out.println("hood in high position");
    }else{
      System.out.println("issue");
    }

    shooter.setFlywheelRPM(-RPM);

    SmartDashboard.putNumber("Set RPM", -RPM);
    SmartDashboard.putNumber("Delay", Delay);

    if((Math.abs(shooter.getFlywheelRPM() + RPM)) < 30 && Delay == 0) {
      Counter++;
    }else if(Delay > 0){
      Counter = 0;
    }

    if(limelight.getTV() == 1 && Counter >= 3 && limelight.getTY() <= 18.5 && limelight.getTY() >= -7 && Delay == 0) { //for low goal it was 8 to -12
      FlywheelAtSpeed = true;
    }else{
      FlywheelAtSpeed = false;
    }

    if(limelight.getTV() == 0){
      RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 1);
      RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 1);
    }else if(limelight.getTY() > 18.5){ // for low goal was 8
      RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 1);
      RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 1);
    }else if(limelight.getTY() < -7){ // for low goal was -12
      RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 1);
      RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 1);
    }else{
      RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
      RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 0);
    }

    SmartDashboard.putBoolean("FlywheelAtSpeed", FlywheelAtSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    FlywheelAtSpeed = false;
    shooter.stopMotors();
    hoodPosition = false;
    shooter.setHoodPosition(false);
    RPM = 1900;
    Counter = 0;
    Delay = 0;
    SmartDashboard.putBoolean("FlywheelAtSpeed", false);
    RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
    RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 0);
    limelight.setLEDMode(1);
    limelight.setCameraMode(1);
    limelight.setStream(2);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}