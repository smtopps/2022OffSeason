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

  private double RPM = 1250;
  private int Counter = 0;

  double distance = 0;

  /** Creates a new RevShooterNew. */
  public RevShooter(Shooter shooter, Limelight limelight) {
    this.shooter = shooter;
    this.limelight = limelight;

    addRequirements(shooter, limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setLEDMode(3);
    limelight.setCameraMode(0);
    limelight.setStream(1);
    shooter.setRampRate(0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = limelight.getTY();

    shooter.setFlywheelRPM(RPM-25); //-40

    if(limelight.getTV() == 0){
      RPM = -1600;
    }else{
      //RPM = -(3210+(-95.8*limelight.getTY())+2.69*(Math.pow(limelight.getTY(), 2)));
      RPM = -((-30.088*limelight.getTY())+1829.5);
    }

    SmartDashboard.putNumber("Set RPM", RPM);

    if((Math.abs(shooter.getFlywheelRPM() - RPM)) < 25) {
      Counter++;
    }/*else{
      Counter = 0;
    }*/

    if(limelight.getTV() == 1 && Counter >= 3 && limelight.getTY() <= 8 && limelight.getTY() >= -12) {
      FlywheelAtSpeed = true;
    }else{
      FlywheelAtSpeed = false;
    }

    if(limelight.getTV() == 0 || limelight.getTY() <= 8 || limelight.getTY() >= -12){
      RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 1);
      RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 1);
    }else{
      RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
      RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 0);
    }

    SmartDashboard.putBoolean("FlywheelAtSpeed", FlywheelAtSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    FlywheelAtSpeed = false;
    shooter.stopMotors();
    RPM = 1250;
    Counter = 0;
    SmartDashboard.putBoolean("FlywheelAtSpeed", false);
    RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
    RobotContainer.driverController.setRumble(RumbleType.kRightRumble, 0);
    limelight.setLEDMode(1);
    limelight.setCameraMode(1);
    limelight.setStream(2);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}