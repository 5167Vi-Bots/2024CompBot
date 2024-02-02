package frc.robot.HelperClasses;

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

    }

    public static class LightsSubsystemConstants {
        public static final Color Orange = new Color(255, 50, 0);
        public static final int CandleLightID = 15;
        public static final String CandleLightCan = RioCanBus;
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
