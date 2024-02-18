package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.AmpSubsystemConstants;
 
public class AmpSubsystem extends SubsystemBase {

    private TalonSRX ampMotor1, ampMotor2;
    public AmpSubsystem() {
        ampMotor1 = new TalonSRX(AmpSubsystemConstants.ampMotor1ID);
        ampMotor2 = new TalonSRX(AmpSubsystemConstants.ampMotor2ID);
    }

    public void ampOut(){
        ampMotor1.set(1);
        ampMotor2.set(1);
    }
     public void ampIn(){
        ampMotor1.set(-.75);
        ampMotor1.set(-.75);
    }

// test these later and make them accurate


}
