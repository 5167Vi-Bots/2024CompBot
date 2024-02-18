package frc.robot.HelperClasses;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Color;

public class Constants {
    public static final String RioCanBus = "rio";
    public static final String CanivoreCanBus = "Canivore";

    public static final int ShooterMotor1ID = 1;
    public static final String ShooterMotor1Can = RioCanBus;

    public static class ArmsSubsystemConstants {

        public static final int LeftArmMotorID = 9;
        public static final String LeftArmMotorCan = RioCanBus;
        public static int RightArmMotorID = 10;
        public static String RightArmMotorCan = RioCanBus;

    }

    public static class BottomIntakeSubsystemConstants {
        public static final int BottomIntakeMotor1ID = 1;
        public static final String BottomIntakeMotor1Can = RioCanBus;
        public static final int BottomIntakeMotor2ID = 2;
        public static final String BottomIntakeMotor2Can = RioCanBus;
    }

    public static class DriveSubsystemConstants {
        //everybody's heard
        //about the bird
        public static final int PigeonID = 0;
        public static final String PigeonCan = CanivoreCanBus;

        // !! COPIED FROM BASEFALCONSWERVE !!

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 6;
            public static final int angleMotorID = 5;
            public static final int canCoderID = 4;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(356.92);
            /*public static final SwerveModuleConstants constants =                                 This is found in java/frc.lib.util.SwerveModuleConstants.java on the Base
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);     Falcon Swerve, I'm  not adding it right now because I don't feel like it*/
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 8;
            public static final int angleMotorID = 7;
            public static final int canCoderID = 1;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(19.86);
            /*public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);*/
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 4;
            public static final int angleMotorID = 3;
            public static final int canCoderID = 3;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(88.85);
            /*public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);*/
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 2;
            public static final int angleMotorID = 1;
            public static final int canCoderID = 2;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(158.20);
            /*public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);*/
        }

        // !! END COPY !!
    }

    public static class LightsSubsystemConstants {
        public static final Color Orange = new Color(255, 50, 0);
        public static final int CandleLightID = 15;
        public static final String CandleLightCan = RioCanBus;
    }

    public static class AmpSubsystemConstants {
        public static final int ampMotorID = 16;
        public static final String ampMotorCan = RioCanBus;
    }

    public static class ShooterSubsystemConstants {
        public static final int ShooterIntakeMotor1ID = 5;
        public static final String ShooterIntakeMotor1Can = RioCanBus;
        public static final int ShooterIntakeMotor2ID = 6;
        public static final String ShooterIntakeMotor2Can = RioCanBus;
        public static final int ShooterMotor1ID = 7;
        public static final String ShooterMotor1CAN = RioCanBus;
        public static final int ShooterMotor2ID = 8;
        public static final String ShooterMotor2CAN = RioCanBus;
    }

    public static class TopIntakeSubsystemConstants {
            public static final int TopIntakeMotor1ID = 3;
            public static final String TopIntakeMotor1Can = RioCanBus;
            public static final int TopIntakeMotor2ID = 4;
            public static final String TopIntakeMotor2Can = RioCanBus;
    }

}
