package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class RevShooter extends CommandBase {
  public static boolean isShooterAtSpeed;
  public final Shooter shooter;
  public final DriveBase driveBase;
  public final Limelight limelight;
  public final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER);
  double distance = 0;
  double target = 0;
  double RPM = 1250;
  boolean shoot = false;

  public RevShooter(Shooter shooter, DriveBase driveBase, Limelight limelight) {
    this.shooter = shooter;
    this.driveBase = driveBase;
    this.limelight = limelight;
    addRequirements(shooter);
  }
  
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    driveBase.setmaxoutput(Constants.MAX_SPEED_DRIVE_SHOOT);

    /*if(limelight.getTV() == 0) {
      RPM = 500;
    }else{
      RPM = 3210+(-95.8*limelight.getTY())+2.69*(Math.pow(limelight.getTY(), 2));
    }
    shooter.flywheelSpeed(RPM);
    System.out.println("RPM = " + RPM);

    /*if(shooter.isFlywheelAtSpeed2(RPM) == true) {
      isShooterAtSpeed = true;
    }else{
      isShooterAtSpeed = false;
    }*/
    shooter.flywheelSpeed(500);
    System.out.println("Speed = " + shooter.getFlywheelVelocity());
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.setmaxoutput(Constants.MAX_SPEED_DRIVE);
    isShooterAtSpeed = false;
    shooter.flywheelStop();
    System.out.println("RevShootEnd");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
