package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.RobotInit;
import frc.robot.commands.RunIntake;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.AutoCommands.DemoAuto;
import frc.robot.commands.AutoCommands.FourBallOne;
import frc.robot.commands.AutoCommands.TwoBall;
import frc.robot.commands.ClimberCommands.BarToBarGoup;
import frc.robot.commands.ClimberCommands.ExtendMidBarGroup;
import frc.robot.commands.ClimberCommands.ManualClimber;
import frc.robot.commands.ClimberCommands.RetractClimber;
import frc.robot.commands.ClimberCommands.ToggleClimber;
import frc.robot.commands.SettingsCommands.ToggleAlignTurret;
import frc.robot.commands.SettingsCommands.ToggleFeederSystem;
import frc.robot.commands.SettingsCommands.ToggleGrabOponentBalls;
import frc.robot.commands.SettingsCommands.ToggleShootOponentBalls;
import frc.robot.commands.SettingsCommands.ToggleShooterIdle;
import frc.robot.commands.ShooterCommands.IdleShooter;
import frc.robot.commands.ShooterCommands.LowGoalShoot;
import frc.robot.commands.ShooterCommands.ManualTurretControl;
import frc.robot.commands.ShooterCommands.ResetTurretEncoder;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {

  public static final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER);
  public static final XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER);
  private static final DriveBase driveBase = new DriveBase();
  private static final Intake intake = new Intake();
  private static final Shooter shooter = new Shooter();
  private static final Limelight limelight = new Limelight();
  private static final Turret turret = new Turret();
  private static final Feeder feeder = new Feeder();
  private static final Climber climber = new Climber();
  private final FourBallOne fourBallOne = new FourBallOne(driveBase, intake, shooter, limelight, turret, feeder, waitTime);
  private final TwoBall twoBall = new TwoBall(driveBase, intake, shooter, turret, limelight, feeder, waitTime);
  private final DemoAuto demoAuto = new DemoAuto(driveBase, intake, shooter, turret, feeder, limelight, 0);
  static double waitTime;
  public static boolean shootOpponentsBalls = false;
  public static boolean toggleAlignTurret = true;
  public static boolean grabOponentBalls = false;
  public static boolean stopFeederSystem = false;
  public static boolean stopShooterSystem = false;
  static SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driverController.getLeftY(), () -> driverController.getLeftX()));
    turret.setDefaultCommand(new ManualTurretControl(turret, () -> operatorController.getRightX()));
    climber.setDefaultCommand(new ManualClimber(climber, () -> operatorController.getLeftY()));
    feeder.setDefaultCommand(new PrepareBallsInFeeder(feeder));
    shooter.setDefaultCommand(new IdleShooter(shooter));
    
    configureButtonBindings();

    SmartDashboard.putBoolean("shootOpponentsBalls", shootOpponentsBalls);
    SmartDashboard.putBoolean("toggleAlignTurret", toggleAlignTurret);
    SmartDashboard.putBoolean("grabOpponentBalls", grabOponentBalls);
    SmartDashboard.putBoolean("stopFeederSystem", stopFeederSystem);
    SmartDashboard.putBoolean("stopShooterSystem", stopShooterSystem);

    autoChooser.setDefaultOption("4 Ball 1", fourBallOne);
    autoChooser.addOption("2 Ball", twoBall);
    autoChooser.addOption("Demo Auot", demoAuto);
    SmartDashboard.putData(autoChooser);
    waitTime = SmartDashboard.getNumber("Auto Wait", 0);
  }

  private void configureButtonBindings() {
    new JoystickButton(driverController, 6).whileHeld(new RunIntake(intake));
    new JoystickButton(driverController, 4).whenPressed(new ToggleIntake(intake, 3));
    new JoystickButton(operatorController, 7).whenPressed(new ResetTurretEncoder(turret));
    new JoystickButton(driverController, 5).whenHeld(new ShootBalls(shooter, turret, limelight, feeder));
    new JoystickButton(driverController, 2).whenHeld(new LowGoalShoot(feeder));
    //new JoystickButton(driverController, 3).whenPressed(new KilllEverything(climber, driveBase, feeder, intake, limelight, shooter, turret));

    new POVButton(operatorController, 0).whenPressed(new ExtendMidBarGroup(climber, intake));
    //new POVButton(driverController, 180).whenPressed(new ClimbToTopGroup(climber));
    new POVButton(operatorController, 270).whenPressed(new BarToBarGoup(climber));
    new POVButton(operatorController, 180).whenPressed(new RetractClimber(climber));
    new JoystickButton(operatorController, 6).whenPressed(new ToggleClimber(climber));

    new JoystickButton(operatorController, 2).whenPressed(new ToggleGrabOponentBalls());
    new JoystickButton(operatorController, 4).whenPressed(new ToggleShootOponentBalls());
    new JoystickButton(operatorController, 1).whenPressed(new ToggleAlignTurret());
    new JoystickButton(operatorController, 3).whenPressed(new ToggleFeederSystem());
    new JoystickButton(operatorController, 8).whenPressed(new ToggleShooterIdle());
  }

  public static void atuoInit() {
    new RobotInit(limelight, climber, turret, 1);
  }

  public static void teleopInit() {
    new RobotInit(limelight, climber, turret, 2);
  }
  
  public static Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
