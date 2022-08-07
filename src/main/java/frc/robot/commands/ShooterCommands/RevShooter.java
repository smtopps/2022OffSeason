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

  double distance = 0;

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
    shooter.setRampRate(0.5);
  }

  @Override
  public void execute() {
    distance = limelight.getTY();



    /*if(limelight.getTV() == 0){
      RPM = -1600;
    }else{
      if(limelight.getTA() < 0.5) {
        shooter.setHoodPosition(true);
        //RPM = -(3210+(-95.8*limelight.getTY())+2.69*(Math.pow(limelight.getTY(), 2)));
        RPM = -((-30.088*limelight.getTY())+1829.5)-FeedBallsToShooter.shooterOffset;
      }else{
        shooter.setHoodPosition(false);
        //RPM = -(3210+(-95.8*limelight.getTY())+2.69*(Math.pow(limelight.getTY(), 2)));
        RPM = -((-30.088*limelight.getTY())+1829.5)-FeedBallsToShooter.shooterOffset;
      }
    }*/

    if(limelight.getTV() == 0){
      hoodPosition = false;
      shooter.setHoodPosition(hoodPosition);
      Delay =15;
      //Timer.delay(0.5);
    }else if(limelight.getTV() == 1 && hoodPosition == false && limelight.getTY() < -2.5){
      hoodPosition = true;
      shooter.setHoodPosition(hoodPosition);
      //Timer.delay(0.5);
      Delay = 15;
    }else if(limelight.getTV() == 1 && hoodPosition == true && limelight.getTY() > 5){
      hoodPosition = false;
      shooter.setHoodPosition(hoodPosition);
      //Timer.delay(0.5);
      Delay = 15;
    }


    if(Delay > 0) {
      Delay--;
    }
    //shooter.setHoodPosition(hoodPosition); //if there are issues move this and the timmer into the if statements above
    //Timer.delay(0.1); //delay in case hood is slower than speed calculation

    if(limelight.getTV() == 0){
      RPM = 1900;
      System.out.println("does not see target");
    }else if(limelight.getTV() == 1 && hoodPosition == false && Delay == 0){
      RPM = (2421+(-57.7*limelight.getTY())+(1.57*(Math.pow(limelight.getTY(), 2))));
      System.out.println("hood in low position");
    }else if(limelight.getTV() == 1 && hoodPosition == true && Delay == 0){
      RPM = (2435+(-67.2*limelight.getTY())+(1.99*(Math.pow(limelight.getTY(), 2))));
      System.out.println("hood in high position");
    }else{
      System.out.println("issue");
    }

    shooter.setFlywheelRPM(-RPM);

    SmartDashboard.putNumber("Set RPM", -RPM);
    SmartDashboard.putNumber("Delay", Delay);

    if((Math.abs(shooter.getFlywheelRPM() + RPM)) < 30 && Delay == 0) { // try to get to 30
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