package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class RobotInit extends CommandBase {
  Limelight limelight;
  Climber climber;
  Turret turret;
  Boolean done = false;

  public RobotInit(Limelight limelight, Climber climber, Turret turret) {
    this.limelight = limelight;
    this.climber = climber;
    this.turret = turret;
    addRequirements(limelight, climber, turret);
  }

  @Override
  public void initialize() {
    limelight.setLEDMode(1);
    limelight.setCameraMode(1);
    limelight.setStream(2);
    climber.compressorEnable();
    turret.resetTurretEncoder();
    done = true;
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if(done == true){
      return true;
    }else{
      return false;
    }
  }
}
