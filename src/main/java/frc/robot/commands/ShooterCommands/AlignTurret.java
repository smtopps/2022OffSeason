package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    if(limelight.getTV() == 1) {
      if(limelight.getTX() <= 2 && limelight.getTX() >= -2) {
        turret.stopTurret();;
        TurretAligned = true;
      }else{
        turret.turretSpeed(speed);
        TurretAligned = false;
      }
    }else{
      turret.stopTurret();
      TurretAligned = true;
    }
    SmartDashboard.putBoolean("Turret Aligned", TurretAligned);
  }

  @Override
  public void end(boolean interrupted) {
    //turret.centerTurretEncoder();
    //turret.centerTurretLimitSwitch();
    turret.stopTurret();
    SmartDashboard.putBoolean("Turret Aligned", false);
    TurretAligned = false;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
