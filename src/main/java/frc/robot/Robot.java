package frc.robot;

import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;



public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private final PowerDistribution PDH = new PowerDistribution(1, ModuleType.kRev);
  private final PneumaticHub pneumaticHub = new PneumaticHub(Constants.REV_PNEUMATIC_MODULE_ID);
  private final PneumaticsControlModule pneumaticsControlModule = new PneumaticsControlModule(Constants.CTRE_PNEUMATICS_MODULE_ID);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    new RobotContainer();
    PortForwarder.add(5800, "limelight.local", 5800);
    PortForwarder.add(5801, "limelight.local", 5801);
    PortForwarder.add(5802, "limelight.local", 5802);
    PortForwarder.add(5803, "limelight.local", 5803);
    PortForwarder.add(5804, "limelight.local", 5804);
    PortForwarder.add(5805, "limelight.local", 5805);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putBoolean("Enable Color Sensor", RobotContainer.enableColorSensor);
    SmartDashboard.putBoolean("Enable Limelight", RobotContainer.enableLimelight);
    SmartDashboard.putBoolean("Enable Idle", RobotContainer.enableIdle);
    SmartDashboard.putNumber("RPM Offsest", RobotContainer.RpmOffset);
    //RobotContainer.waitTime = SmartDashboard.getNumber("Auto Wait", 0);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    PDH.clearStickyFaults();
    pneumaticHub.clearStickyFaults();
    pneumaticsControlModule.clearAllStickyFaults();
    RobotContainer.getAutonomousCommand().schedule();
    LiveWindow.disableAllTelemetry();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    LiveWindow.disableAllTelemetry();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
