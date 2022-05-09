package frc.robot;

import java.util.Arrays;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignTurret;
import frc.robot.commands.AutoFeedBallsToShooter;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.IdleShooter;
import frc.robot.commands.PrepareBallsInFeeder;
import frc.robot.commands.RevShooter;
import frc.robot.commands.RunIntake;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ClimberCommands.BarToBarGoup;
import frc.robot.commands.ClimberCommands.ExtendClimber;
import frc.robot.commands.ClimberCommands.RetractClimber;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.DriveBaseTrajectory;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER);
  public static final DriveBaseTrajectory driveBaseTrajectory = new DriveBaseTrajectory();
  private static final DriveBase driveBase = new DriveBase();
  private static final Intake intake = new Intake();
  private static final Shooter shooter = new Shooter();
  private static final Limelight limelight = new Limelight();
  private static final Turret turret = new Turret();
  private static final Feeder feeder = new Feeder();
  private static final Climber climber = new Climber();

  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveBaseTrajectory.setDefaultCommand(new DriveWithJoystick(driveBaseTrajectory, driverController.getLeftY(), driverController.getLeftX()));
    //driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, driverController.getLeftY(), driverController.getLeftX()));
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    new JoystickButton(driverController, 6).whileHeld(new RunIntake(intake, driveBaseTrajectory));
    new JoystickButton(driverController, 4).whenPressed(new ToggleIntake(intake));
    new JoystickButton(driverController, 5).whenHeld(new RevShooter(shooter, driveBase, limelight));
    //new JoystickButton(driverController, 5).whenHeld(new AlignTurret(turret, limelight));
    //new JoystickButton(driverController, 2).whileHeld(new AutoFeedBallsToShooter(limelight, feeder));
    //new JoystickButton(driverController, 5).whenInactive(new PrepareBallsInFeeder(feeder));
    //new JoystickButton(driverController, 5).whenInactive(new IdleShooter(shooter));

    //new JoystickButton(driverController, driverController.getPOV(0)).whenPressed(new ExtendClimber(climber));
    //new JoystickButton(driverController, driverController.getPOV(180)).whenPressed(new RetractClimber(climber));
    //new JoystickButton(driverController, driverController.getPOV(270)).whenPressed(new BarToBarGoup(climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
    /*TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(2), Units.feetToMeters(2));
    config.setKinematics(driveBaseTrajectory.getKinematics());

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);

    RamseteCommand command = new RamseteCommand(
      trajectory, 
      driveBaseTrajectory::getPose, 
      new RamseteController(2.0, 0.7), 
      driveBaseTrajectory.getFeedForward(), 
      driveBaseTrajectory.getKinematics(), 
      driveBaseTrajectory::getSpeeds, 
      driveBaseTrajectory.getLeftPIDController(), 
      driveBaseTrajectory.getRightPIDController(), 
      driveBaseTrajectory::setOutput, 
      driveBaseTrajectory);

      return command;*/
  }
}