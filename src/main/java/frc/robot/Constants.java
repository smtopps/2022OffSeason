package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    //Controller Numbers
    public static final int DRIVER_CONTROLLER = 0;

    //Pneumatics Module ID and Information
    public static final int REV_PNEUMATIC_MODULE_ID = 14;
    public static final PneumaticsModuleType REV_PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.REVPH;
    public static final int CTRE_PNEUMATICS_MODULE_ID = 13;
    public static final PneumaticsModuleType CTRE_PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;

    //DriveBase Motor ID and informaiton
    public static final int LEFT_LEADER_ID = 23;
    public static final int LEFT_FOLLOW_ID = 25;
    public static final int RIGHT_LEADER_ID = 22;
    public static final int RIGHT_FOLLOW_ID = 24;
    public static final double MAX_SPEED_DRIVE = 1;
    public static final double DEADBAND_DRIVE = 0.02;
    public static final String NEUTRAL_MODE_DRIVE = "Brake";

    //Intake Motor ID and information
    public static final int INTAKE_ID = 28;
    public static final int INTAKE_POSITION = 8;
    public static final double MAX_SPEED_DRIVE_INTAKE = 0.2;
    public static final double INTAKE_SPEED = 0.8;

    //Shooter Motor ID and information
    public static final int LEFT_SHOOTER_MOTOR_ID = 11;
    public static final int RIGHT_SHOOTER_MOTOR_ID = 12;
    public static final double SHOOTER_FF = 0;
    public static final double SHOOTER_KP = 0;
    public static final double SHOOTER_KI = 0;
    public static final double SHOOTER_KD = 0;
    public static final double MAX_SPEED_DRIVE_SHOOT = 0.1;

    //Turret Motor ID and information
    public static final int TURRET_MOTOR_ID = 27;
    public static final double TURRET_P = 0.04;

    //Feeder Motor ID and information
    public static final int FEEDER_MOTOR_ID = 26;

    //Climber Motor ID and information
    public static final int CLIMBER_LEFT_MOTOR_ID = 31;
    public static final int CLIMBER_RIGHT_MOTOR_ID = 32;

    public static final int PIGEON2_ID = 4;
}
