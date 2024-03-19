package frc.robot.HelperClasses;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class MotorTelemetry {
    public static void AddMotorTelemetry(ShuffleboardTab tab, String MotorName, TalonFX motor)
    {
        
        tab.addDouble(MotorName + " Velocity", (() -> motor.getVelocity().getValueAsDouble()));    
        tab.addDouble(MotorName + " Temp", (() -> motor.getDeviceTemp().getValueAsDouble()));
        tab.addDouble(MotorName + " DutyCycle", (() -> motor.getDutyCycle().getValueAsDouble()));    
        tab.addInteger(MotorName + " FaultField", (() -> motor.getFaultField().getValue()));
        tab.addString(MotorName + " ControlMode", (() -> motor.getControlMode().getValue().toString()));
        tab.addDouble(MotorName + " Current", (() -> motor.getSupplyCurrent().getValueAsDouble()));
        tab.addDouble(MotorName + " Voltage", (() -> motor.getSupplyVoltage().getValueAsDouble()));
        tab.addDouble(MotorName + " Stator Current", (() -> motor.getStatorCurrent().getValueAsDouble()));
        tab.addDouble(MotorName + " Rotor Position", (() -> motor.getRotorPosition().getValueAsDouble()));
        tab.addDouble(MotorName + " Rotor Velocity", (() -> motor.getRotorVelocity().getValueAsDouble()));
        tab.addDouble(MotorName + " Processor Temp", (() -> motor.getProcessorTemp().getValueAsDouble()));    
        tab.addDouble(MotorName + " Position", (() -> motor.getPosition().getValueAsDouble()));
        tab.addBoolean(MotorName + " Pro Licensed", (() -> motor.getIsProLicensed().getValue()));
    }    

        public static void AddMotorTelemetry(ShuffleboardTab tab, String MotorName, SwerveModule motor)
    {
        AddMotorTelemetry(tab, MotorName + "Drive", motor.getDriveMotor());        
        AddMotorTelemetry(tab, MotorName + "Steer", motor.getSteerMotor());

    }   
}
