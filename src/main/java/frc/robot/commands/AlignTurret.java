package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class AlignTurret extends CommandBase {
  public static boolean TurretAligned = false;
  public final Turret turret;
  public final Limelight limelight;

  public AlignTurret(Turret turret, Limelight limelight) {
    this.turret = turret;
    this.limelight = limelight;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double speed = (limelight.getTX() * Constants.TURRET_P);

    //System.out.println("SPEED---------------------------------");
    //System.out.println(limelight.getTV());
    //System.out.println(limelight.getTX());

    if(limelight.getTV() == 1) {
      if(limelight.getTX() <= 2 && limelight.getTX() >= -2) {
        turret.stopTurret();;
        TurretAligned = false;
      }else{
        turret.turretSpeed(speed);
        TurretAligned = true;
      }
    }else{
      turret.stopTurret();
      TurretAligned = false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    //turret.centerTurretEncoder();
    //turret.centerTurretLimitSwitch();
    turret.stopTurret();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
