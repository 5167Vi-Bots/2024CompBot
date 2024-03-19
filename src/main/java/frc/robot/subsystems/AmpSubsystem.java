package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.AmpSubsystemConstants;
 
public class AmpSubsystem extends SubsystemBase {

    private TalonSRX ampMotor1, ampMotor2;
    public AmpSubsystem() {
        ampMotor1 = new TalonSRX(AmpSubsystemConstants.ampMotor1ID);
        ampMotor2 = new TalonSRX(AmpSubsystemConstants.ampMotor2ID);
        SetupTelemetry();
    }
  private void SetupTelemetry() {
    var SBTab = Shuffleboard.getTab("AmpSubsystem");

    //Phoenix 6 compatible motor
    // SBTab.addDouble("Shooter 1 Velocity", (() -> ampMotor1.getVelocity().getValueAsDouble()));    
    // SBTab.addDouble("Shooter 1 Temp", (() -> ampMotor1.getDeviceTemp().getValueAsDouble()));
    // SBTab.addDouble("Shooter 1 DutyCycle", (() -> ampMotor1.getDutyCycle().getValueAsDouble()));    
    // SBTab.addInteger("Shooter 1 FaultField", (() -> ampMotor1.getFaultField().getValue()));
    // SBTab.addString("Shooter 1 ControlMode", (() -> ampMotor1.getControlMode().getValue().toString()));
    // SBTab.addDouble("Shooter 1 Current", (() -> ampMotor1.getSupplyCurrent().getValueAsDouble()));
    // SBTab.addDouble("Shooter 1 Voltage", (() -> ampMotor1.getSupplyVoltage().getValueAsDouble()));
    //     SBTab.addDouble("Shooter 1 Stator Current", (() -> ampMotor1.getStatorCurrent().getValueAsDouble()));
    //         SBTab.addDouble("Shooter 1 Rotor Position", (() -> ampMotor1.getRotorPosition().getValueAsDouble()));
    //         SBTab.addDouble("Shooter 1 Rotor Velocity", (() -> ampMotor1.getRotorVelocity().getValueAsDouble()));
    //     SBTab.addDouble("Shooter 1 Processor Temp", (() -> ampMotor1.getProcessorTemp().getValueAsDouble()));    
    // SBTab.addDouble("Shooter 1 Position", (() -> ampMotor1.getPosition().getValueAsDouble()));


    


    
   }

    public void ampOut(){
        ampMotor1.set(ControlMode.PercentOutput, .5);
        ampMotor2.set(ControlMode.PercentOutput, .5);
    }
     public void ampIn(){
        ampMotor1.set(ControlMode.PercentOutput, -.25);
        ampMotor2.set(ControlMode.PercentOutput, -.25);
    }

    public void ampStop(){
        ampMotor1.set(ControlMode.PercentOutput, 0);
        ampMotor2.set(ControlMode.PercentOutput, 0);
    }

// test these later and make them accurate


}
