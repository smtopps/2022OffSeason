package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveBaseConstants {

    }

    //Controller Numbers
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;

    //Pneumatics Module ID and Information
    public static final int REV_PNEUMATIC_MODULE_ID = 14;
    public static final int CTRE_PNEUMATICS_MODULE_ID = 13;

    //DriveBase Motor ID and informaiton
    public static final int LEFT_LEADER_ID = 23;
    public static final int LEFT_FOLLOW_ID = 25;
    public static final int RIGHT_LEADER_ID = 22;
    public static final int RIGHT_FOLLOW_ID = 24;

    //Intake Motor ID and information
    public static final int INTAKE_ID = 28;
    public static final int INTAKE_ROTATION_ID = 50;
    public static final int INTAKE_CHANNEL = 8;
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
    public static final int HOOD_POSITION = 10;

    //Turret Motor ID and information
    public static final int TURRET_MOTOR_ID = 27;
    public static final double TURRET_P = 0.04;

    //Feeder Motor ID and information
    public static final int FEEDER_MOTOR_ID = 26;

    //Climber Motor ID and information
    public static final int CLIMBER_LEFT_MOTOR_ID = 31;
    public static final int CLIMBER_RIGHT_MOTOR_ID = 32;

    public static final int PIGEON2_ID = 2;
    public static final double turretOffestForOpponentsBall = -14;
    public static final double shooterOffsetForOpponentsBall = -600;
    public static final double CLIMBER_RETRACTION_ERROR = 0.1; //larger the number the quicker the climber will react to arms being out of sync when retracting. 0.2 may be a good starting point

    public static final class AutoConstants {
        public static final double kPRightController = 1;
        public static final double kPLeftController = 1; //3.7233
        public static final double kTrackwidthMeters = Units.inchesToMeters(21.8685);
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
        public static final double ks = 0.64503;
        public static final double kv = 2.2412; //2.5412
        public static final double ka = 0.55051;
    }
}
