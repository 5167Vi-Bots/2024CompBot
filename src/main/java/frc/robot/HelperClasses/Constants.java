package frc.robot.HelperClasses;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstantsFactory;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.ClosedLoopOutputType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants.SteerFeedbackType;

import edu.wpi.first.math.util.Units;
//import frc.robot.CommandSwerveDrivetrain;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Color;

public class Constants {
    public static final String RioCanBus = "rio";
    public static final String CanivoreCanBus = "Canivore";

    public static class ArmsSubsystemConstants {

        public static final int LeftArmMotorID = 13;
        public static final String LeftArmMotorCan = RioCanBus;
        public static int RightArmMotorID = 14;
        public static String RightArmMotorCan = RioCanBus;

    }

    public static class IntakeSubsystemConstants {
        public static final int IntakeMotor1ID = 9;
        public static final String IntakeMotor1Can = RioCanBus;
        public static final int IntakeMotor2ID = 10;
        public static final String IntakeMotor2Can = RioCanBus;
    }

    public static class DriveSubsystemConstants {
        //everybody's heard
        //about the bird
        public static final int PigeonID = 0;
        public static final String PigeonCan = CanivoreCanBus;



        // !! COPIED FROM BASEFALCONSWERVE !!

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { 
            //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 1;
            public static final int angleMotorID = 2;
            public static final int canCoderID = 1;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(356.92);
            //public static final SwerveModuleConstants constants = new SwerveModuleConstants (driveMotorID, angleMotorID, canCoderID, angleOffset);     
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 3;
            public static final int angleMotorID = 4;
            public static final int canCoderID = 2;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(19.86);
            //public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 5;
            public static final int angleMotorID = 6;
            public static final int canCoderID = 3;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(88.85);
            //public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 2;
            public static final int angleMotorID = 1;
            public static final int canCoderID = 4;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(158.20);
            //public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        // !! END COPY !!
    }

    public static class LightsSubsystemConstants {
        public static final Color Orange = new Color(255, 50, 0);
        public static final int CandleLightID = 15;
        public static final String CandleLightCan = RioCanBus;
    }

    public static class AmpSubsystemConstants {
        public static final int ampMotor1ID = 15;
        public static final String ampMotor1Can = RioCanBus;
        public static final int ampMotor2ID = 16;
        public static final String ampMotor2Can = RioCanBus;
    }

    public static class ShooterSubsystemConstants {
        public static final int ShooterIntakeMotor1ID = 17;
        public static final String ShooterIntakeMotor1Can = RioCanBus;
        public static final int ShooterIntakeMotor2ID = 18;
        public static final String ShooterIntakeMotor2Can = RioCanBus;
        public static final int ShooterMotor1ID = 11;
        public static final String ShooterMotor1CAN = RioCanBus;
        public static final int ShooterMotor2ID = 12;
        public static final String ShooterMotor2CAN = RioCanBus;
        public static final Port distanceSensorPort = Port.kOnboard;
    }

    public static class ControllerPorts {
        public static final int kDriverControllerPort = 17;
        public static final int kOperatorControllerPort = 18;
    }

    
}
