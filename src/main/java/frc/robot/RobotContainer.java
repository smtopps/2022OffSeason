package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.ClimberCommands.BarToBarGoup;
import frc.robot.commands.ClimberCommands.EnableCompressor;
import frc.robot.commands.ClimberCommands.ExtendMidBarGroup;
import frc.robot.commands.ClimberCommands.ManualClimber;
import frc.robot.commands.ClimberCommands.RetractClimber;
import frc.robot.commands.ClimberCommands.RetractClimberGroup;
import frc.robot.commands.ClimberCommands.ToggleClimber;
import frc.robot.commands.IntakeCommands.RunIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake.IntakePosition;
import frc.robot.commands.SettingsCommands.EnableColorSensor;
import frc.robot.commands.SettingsCommands.EnableIdle;
import frc.robot.commands.SettingsCommands.EnableLimelight;
import frc.robot.commands.SettingsCommands.EnableColorSensor.ColorSensorState;
import frc.robot.commands.SettingsCommands.EnableIdle.IdleState;
import frc.robot.commands.SettingsCommands.EnableLimelight.LimelightState;
import frc.robot.commands.ShooterCommands.IdleShooter;
import frc.robot.commands.ShooterCommands.LowGoalShoot;
import frc.robot.commands.ShooterCommands.ManualTurretControl;
import frc.robot.commands.ShooterCommands.ResetTurretEncoder;
import frc.robot.commands.ShooterCommands.ShootBalls;
import frc.robot.commands.TrajectoryAuto.FiveBall;
import frc.robot.commands.TrajectoryAuto.FourBall;
import frc.robot.commands.TrajectoryAuto.OneBallOneSteal;
import frc.robot.commands.TrajectoryAuto.Test;
import frc.robot.commands.TrajectoryAuto.TwoBall;
import frc.robot.commands.TrajectoryAuto.TwoBallTwoSteal;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pigeon2Subsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {

  public static final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER);
  public static final XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER);
  private final DriveBase driveBase = new DriveBase();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final Limelight limelight = new Limelight();
  private final Turret turret = new Turret();
  private final Feeder feeder = new Feeder();
  private final Climber climber = new Climber();
  private final Pigeon2Subsystem pigeon2Subsystem = new Pigeon2Subsystem();
  private final FiveBall fiveBall = new FiveBall(driveBase, intake, shooter, turret, limelight, feeder, pigeon2Subsystem, waitTime);
  private final FourBall fourBall = new FourBall(driveBase, intake, shooter, turret, limelight, feeder, pigeon2Subsystem, waitTime);
  private final OneBallOneSteal oneBallOneSteal = new OneBallOneSteal(driveBase, intake, shooter, turret, limelight, feeder, pigeon2Subsystem, waitTime);
  private final TwoBall twoBall = new TwoBall(driveBase, intake, shooter, feeder, limelight, pigeon2Subsystem, turret, waitTime);
  private final TwoBallTwoSteal twoBallTwoSteal = new TwoBallTwoSteal(driveBase, intake, shooter, feeder, limelight, pigeon2Subsystem, turret, waitTime);
  private final Test test = new Test(driveBase);
  
  public static double waitTime;
  public static boolean enableColorSensor = true;
  public static boolean enableLimelight = true;
  public static boolean enableIdle = true;

  private static SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driverController.getLeftY(), () -> driverController.getLeftX()));
    turret.setDefaultCommand(new ManualTurretControl(turret, () -> operatorController.getRightX()));
    climber.setDefaultCommand(new ManualClimber(climber, () -> operatorController.getLeftY()));
    feeder.setDefaultCommand(new PrepareBallsInFeeder(feeder));
    shooter.setDefaultCommand(new IdleShooter(shooter, limelight));
    //intake.setDefaultCommand(new RetractIntakeCurrent(intake, State.RETRACTING));
    
    configureButtonBindings();

    autoChooser.setDefaultOption("2 Ball", twoBall);
    autoChooser.addOption("5 Ball", fiveBall);
    autoChooser.addOption("4 Ball", fourBall);
    autoChooser.addOption("1 Ball 1 Steal", oneBallOneSteal);
    autoChooser.addOption("2 Ball 2 Steal", twoBallTwoSteal);
    autoChooser.addOption("Test", test);

    SmartDashboard.putData(autoChooser);
    waitTime = SmartDashboard.getNumber("Auto Wait", 0);
  }

  private void configureButtonBindings() {
    new JoystickButton(driverController, 6).whileHeld(new RunIntake(intake, 0.7)); //was 0.8
    //new JoystickButton(driverController, 6).whileHeld(new RetractIntakeCurrent(intake, State.INTAKE));
    //new TriggerRButton().whileHeld(new RetractIntakeCurrent(intake, State.RIVERSING));
    new JoystickButton(driverController, 4).whenPressed(new ToggleIntake(intake, IntakePosition.TOGGLE));
    new JoystickButton(driverController, 5).whenHeld(new ShootBalls(shooter, turret, limelight, feeder));
    new JoystickButton(driverController, 2).whenHeld(new LowGoalShoot(feeder, shooter, limelight));

    new POVButton(operatorController, 0).whenPressed(new ExtendMidBarGroup(climber, intake));
    new POVButton(operatorController, 270).whenPressed(new BarToBarGoup(climber));
    new POVButton(operatorController, 180).whenPressed(new RetractClimber(climber));
    new POVButton(operatorController, 90).whenPressed(new RetractClimberGroup(climber));
    new JoystickButton(operatorController, 6).whenPressed(new ToggleClimber(climber));

    new JoystickButton(operatorController, 7).whenPressed(new ResetTurretEncoder(turret));
    new JoystickButton(operatorController, 1).whenPressed(new EnableColorSensor(ColorSensorState.TOGGLE));
    new JoystickButton(operatorController, 3).whenPressed(new EnableLimelight(LimelightState.TOGGLE));
    new JoystickButton(operatorController, 4).whenPressed(new EnableIdle(IdleState.TOGGLE));
    new JoystickButton(operatorController, 2).whenPressed(new EnableCompressor(climber));
  }
  
  public static Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
